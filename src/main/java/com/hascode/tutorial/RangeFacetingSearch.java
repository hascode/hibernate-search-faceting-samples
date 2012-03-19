package com.hascode.tutorial;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import org.apache.lucene.search.Query;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;
import org.hibernate.search.query.engine.spi.FacetManager;
import org.hibernate.search.query.facet.Facet;
import org.hibernate.search.query.facet.FacetSortOrder;
import org.hibernate.search.query.facet.FacetingRequest;

public class RangeFacetingSearch {
	public static void main(final String[] args) {
		final EntityManagerFactory emf = Persistence
				.createEntityManagerFactory("hascode-local");
		final EntityManager em = emf.createEntityManager();
		final EntityTransaction tx = em.getTransaction();

		BookSetup.createBooks(em, tx);

		FullTextEntityManager fullTextEntityManager = org.hibernate.search.jpa.Search
				.getFullTextEntityManager(em);
		QueryBuilder builder = fullTextEntityManager.getSearchFactory()
				.buildQueryBuilder().forEntity(Book.class).get();

		FacetingRequest priceFacetingRequest = builder.facet()
				.name("priceFaceting").onField("price").range().below(20)
				.from(30).to(40).from(40).to(50).above(50).excludeLimit()
				.orderedBy(FacetSortOrder.RANGE_DEFINITION_ODER)
				.createFacetingRequest();
		Query luceneQuery = builder.all().createQuery();
		org.hibernate.search.jpa.FullTextQuery fullTextQuery = fullTextEntityManager
				.createFullTextQuery(luceneQuery);
		FacetManager facetManager = fullTextQuery.getFacetManager();
		facetManager.enableFaceting(priceFacetingRequest);

		List<Facet> facets = facetManager.getFacets("priceFaceting");
		for (Facet f : facets) {
			System.out.println("Price range: " + f.getValue() + " ("
					+ f.getCount() + ")");
		}
		em.close();
		emf.close();
	}
}
