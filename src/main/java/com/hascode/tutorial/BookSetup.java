package com.hascode.tutorial;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

public class BookSetup {
	public static void createBooks(final EntityManager em,
			final EntityTransaction tx) {
		tx.begin();
		Book book1 = new Book();
		book1.setTitle("The big book of nothing");
		book1.setCategory("Adventure");
		book1.setAuthor("fred");
		book1.setPrice(12);

		Book book2 = new Book();
		book2.setTitle("Exciting stories I");
		book2.setCategory("Horror");
		book2.setAuthor("selma");
		book2.setPrice(22);

		Book book3 = new Book();
		book3.setTitle("My life");
		book3.setCategory("Horror");
		book3.setAuthor("fred");
		book3.setPrice(10);

		Book book4 = new Book();
		book4.setTitle("Some science book");
		book4.setCategory("Science");
		book4.setAuthor("tim");
		book4.setPrice(35);

		Book book5 = new Book();
		book5.setTitle("The universe and stuff");
		book5.setCategory("Science");
		book5.setAuthor("charlize");
		book5.setPrice(65);

		Book book6 = new Book();
		book6.setTitle("Indiana Bones XII");
		book6.setCategory("Adventure");
		book6.setAuthor("charles");
		book6.setPrice(14);

		Book book7 = new Book();
		book7.setTitle("A day at work without coffee");
		book7.setCategory("Horror");
		book7.setAuthor("me");
		book7.setPrice(25);

		Book book8 = new Book();
		book8.setTitle("Horror Pirate Cyber Ninjas from Hell III");
		book8.setCategory("Cartoon");
		book8.setAuthor("peter");
		book8.setPrice(51);

		em.persist(book1);
		em.persist(book2);
		em.persist(book3);
		em.persist(book4);
		em.persist(book5);
		em.persist(book6);
		em.persist(book7);
		em.persist(book8);
		tx.commit();
	}
}
