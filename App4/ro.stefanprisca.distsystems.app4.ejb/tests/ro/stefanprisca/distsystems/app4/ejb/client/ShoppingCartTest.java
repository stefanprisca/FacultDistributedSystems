package ro.stefanprisca.distsystems.app4.ejb.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app4.ejb.client.beans.ShoppingCartBean;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;

@RunWith(JUnit4.class)
public class ShoppingCartTest {

	private static final ShoppingCartBean shcart = new ShoppingCartBean();

	@Test
	public void testAddNewBook() {
		Book b = generateBook();
		shcart.addBook(b);
		assertTrue(shcart.getBooks().get(0).getId().equals(b.getId()));

		b = shcart.getBooks().get(0);
		int quant = b.getQuantity();
		shcart.addBook(b);
		assertTrue(shcart.getBooks().get(0).getQuantity() == quant + 1);
	}

	private Book generateBook() {
		Book b = new Book();
		b.setAuthor("Stefan");
		b.setName("Book");
		b.setId(100l);
		return b;
	}
}
