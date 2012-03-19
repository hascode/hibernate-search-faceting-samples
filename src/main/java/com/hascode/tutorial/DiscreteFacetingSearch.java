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

public class DiscreteFacetingSearch {
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

		FacetingRequest categoryFacetingRequest = builder.facet()
				.name("categoryFaceting").onField("category").discrete()
				.orderedBy(FacetSortOrder.COUNT_DESC).includeZeroCounts(false)
				.createFacetingRequest();

		Query luceneQuery = builder.all().createQuery();
		org.hibernate.search.jpa.FullTextQuery fullTextQuery = fullTextEntityManager
				.createFullTextQuery(luceneQuery);
		FacetManager facetManager = fullTextQuery.getFacetManager();
		facetManager.enableFaceting(categoryFacetingRequest);

		List<Facet> facets = facetManager.getFacets("categoryFaceting");
		for (Facet f : facets) {
			System.out.println(f.getValue() + " (" + f.getCount() + ")");
			List<Book> books = fullTextEntityManager.createFullTextQuery(
					f.getFacetQuery()).getResultList();
			for (final Book b : books) {
				System.out.println("\t" + b.getTitle() + " (" + b.getAuthor()
						+ ")");
			}
		}
		em.close();
		emf.close();
	}
}
