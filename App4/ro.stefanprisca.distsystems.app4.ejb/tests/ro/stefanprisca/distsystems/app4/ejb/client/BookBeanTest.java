package ro.stefanprisca.distsystems.app4.ejb.client;

import static org.junit.Assert.assertTrue;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app4.ejb.client.beans.BookManagerBean;
import ro.stefanprisca.distsystems.app4.ejb.client.beans.NavigationBean;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;
import ro.stefanprisca.distsystems.app4.ejb.models.Publisher;

@RunWith(JUnit4.class)
public class BookBeanTest {

	private final static BookManagerBean bmb = new BookManagerBean();

	@Test
	public void testEditBook() {
		assertTrue(bmb.editBook(1l).equals(
				NavigationBean.admin_ToEditBookPage()));
		assertTrue(bmb.editBook().equals(NavigationBean.admin_ToEditBookPage()));
	}

	// @Test
	public void testConfEdit_AddBook() {
		Book b = new Book();
		b.setAuthor("User");
		b.setName("UI Created");
		b.setPrice("1000");

		b.setQuantity(100);
		b.setId(-1l);

		Publisher pub = new Publisher();
		pub.setId(-1l);
		pub.setName("Stefan");
		b.setPub(pub);

		bmb.setBook(b);
		bmb.confEditBook();
		assertTrue(bmb.getBooks().contains(b));
	}

	@Test
	public void testConfEdit_EditBook() {
		Book b = bmb.getBooks().get(0);
		bmb.editBook(b.getId());
		bmb.getBook().setName("Some User Edited book");
		Publisher pub = new Publisher();
		pub.setName("New Publisher 144");
		bmb.getBook().setPub(pub);
		bmb.confEditBook();
		assertTrue(bmb.getBooks().get(0).getName()
				.equals("Some User Edited book"));
	}
}
