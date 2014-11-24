package ro.stefanprisca.distsystems.app4.ejb.client;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app4.ejb.beans.BookProviderRemote;
import ro.stefanprisca.distsystems.app4.ejb.client.utils.ClientUtility;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;

@RunWith(JUnit4.class)
public class BookProviderBeanTest {

	private static final BookProviderRemote bp = ClientUtility.doLookup();;

	@Test
	public void testGetBooks() {
		List<Book> books = bp.getBooks();
		assertNotNull(books);
		assertFalse(books.isEmpty());
	}
}
