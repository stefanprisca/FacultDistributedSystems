package ro.stefanprisca.distsystems.app4.ejb.client;

import static org.junit.Assert.assertTrue;

import java.util.List;
import java.util.Vector;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import ro.stefanprisca.distsystems.app4.ejb.client.beans.BookManagerBean;
import ro.stefanprisca.distsystems.app4.ejb.client.beans.NavigationBean;
import ro.stefanprisca.distsystems.app4.ejb.client.beans.ValidationWrapperBean;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;

@RunWith(JUnit4.class)
public class UserInteractionsTest {

	private static final ValidationWrapperBean validBean = new ValidationWrapperBean();

	@Test
	public void takeBookTest() {
		Book b = validBean.getBookMan().getBooks().get(0);
		int quant = b.getQuantity();
		assertTrue(validBean.wrapTakeBook(b.getId()).equals(""));
		// assertTrue(validBean.getBookMan().getBooks().get(0).getQuantity() ==
		// quant - 1);
	}

	@Test
	public void toShoppingCartTest() {
		BookManagerBean bm = validBean.getBookMan();
		Book b = validBean.getBookMan().getBooks().get(0);
		validBean.wrapTakeBook(b.getId());
		assertTrue(bm.toShoppingCart().equals(
				NavigationBean.user_ToShoppingCart()));
	}

	// @Test
	public void checkoutShoppingCartTest() {

		final List<Book> initialBooks = (List<Book>) ((Vector<Book>) validBean
				.getBookMan().getBooks()).clone();
		int delta = 1;
		validBean.getBookMan().getBooks()
				.forEach(b -> validBean.wrapTakeBook(b.getId()));

		final List<Book> books = validBean.getBookMan().getShoppingCart()
				.getBooks();
		books.forEach(b -> {
			if (b.getQuantity() > delta) {
				b.setQuantity(delta);
			}
		});
		books.removeIf(b -> b.getQuantity() != 1);
		validBean.wrapConfirmCheckout(books);

		// final List<Book> finalBooks = validBean.getBookMan().getBooks();
		// books.forEach(b -> {
		// int i = initialBooks.indexOf(b);
		// int j = finalBooks.indexOf(b);
		// if (i >= 0 && j >= 0) {
		// System.out.println(initialBooks.get(i).getQuantity() + " : "
		// + initialBooks.get(i).getName() + " vs "
		// + finalBooks.get(j).getQuantity() + " : "
		// + finalBooks.get(j).getName());
		//
		// assertTrue(initialBooks.get(i).getQuantity() - delta == finalBooks
		// .get(j).getQuantity());
		// }
		// });

	}
}
