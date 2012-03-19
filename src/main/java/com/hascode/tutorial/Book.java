package com.hascode.tutorial;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

import org.hibernate.search.annotations.Analyze;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;
import org.hibernate.search.annotations.Store;

@Entity
@Indexed(index = "index/books")
public class Book {
	private Long id;
	private String title;
	private String author;
	private String category;
	private int price;

	@Id
	@GeneratedValue
	public Long getId() {
		return id;
	}

	@Field(name = "title", analyze = Analyze.YES, store = Store.YES)
	public String getTitle() {
		return title;
	}

	public void setTitle(final String title) {
		this.title = title;
	}

	public void setId(final Long id) {
		this.id = id;
	}

	public void setAuthor(final String author) {
		this.author = author;
	}

	@Field(analyze = Analyze.NO, store = Store.YES)
	public String getAuthor() {
		return author;
	}

	@Field(analyze = Analyze.NO, store = Store.YES)
	public String getCategory() {
		return category;
	}

	public void setCategory(final String category) {
		this.category = category;
	}

	@Field(analyze = Analyze.NO, store = Store.YES)
	public int getPrice() {
		return price;
	}

	public void setPrice(final int price) {
		this.price = price;
	}

}