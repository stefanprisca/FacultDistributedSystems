package ro.stefanprisca.distsystems.app4.ejb.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app4.ejb.beans.BookProviderRemote;
import ro.stefanprisca.distsystems.app4.ejb.client.utils.ClientUtility;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;
import ro.stefanprisca.distsystems.app4.ejb.models.Publisher;

@RunWith(JUnit4.class)
public class BookProviderBeanTest {

	private static final BookProviderRemote bp = ClientUtility.doLookup();;

	@Test
	public void testGetBooks() {
		List<Book> books = bp.getBooks();
		assertNotNull(books);
		assertFalse(books.isEmpty());
	}

	@Test
	public void testUpdateBook() {
		String authorName = "New Test Author";
		Book b = bp.getBooks().get(0);
		b.setAuthor(authorName);
		bp.updateBook(b);
		assertTrue(bp.getBook(b.getId()).getAuthor().equals(authorName));
	}

	// @Test
	public void testAddNewBook() {
		Book b = new Book();
		b.setAuthor("Stefan Test 2");
		b.setName("Test Time");
		b.setPrice("120$");
		b.setQuantity(100);
		Publisher pub = new Publisher();
		pub.setName("Test Publisher");
		pub.setId(-1l);
		b.setId(-1l);
		b.setPub(pub);

		bp.updateBook(b);

	}
}
