package ro.stefanprisca.distsystems.app4.ejb.client.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.stefanprisca.distsystems.app4.ejb.models.Book;

@SessionScoped
@ManagedBean(name = "validationBean", eager = true)
public class ValidationWrapperBean implements Serializable {

	private static final long serialVersionUID = 4963934917166059941L;

	private static final String ERROR_BOOK_QUANTITY = "There are less books than selected!";
	private static final String WARNING_BOOK_QUANTITY = "One of the selected books is in low quantity. Please check your cart to ensure you do not exceed it";
	private final BookManagerBean bookMan;
	private String errorMessage;

	public ValidationWrapperBean() {
		this.bookMan = new BookManagerBean();
	}

	public String wrapTakeBook(Long bookId) {
		Book book = bookMan.getBooks().parallelStream()
				.filter(b -> b.getId().equals(bookId)).findAny().get();

		if (book.getQuantity() < 3) {
			this.errorMessage = WARNING_BOOK_QUANTITY;
		}
		if (book.getQuantity() < 1) {
			this.errorMessage = ERROR_BOOK_QUANTITY;
			return "";
		}
		this.errorMessage = "";
		return bookMan.takeBook(book);
	}

	public String wrapConfirmCheckout(List<Book> books) {

		List<Book> bkManBooks = bookMan.getBooks();
		for (Book b : books) {
			int index = bkManBooks.indexOf(b);
			if (index >= 0) {
				Book toUpdate = bkManBooks.get(index);
				if (toUpdate.getQuantity() - b.getQuantity() < 0) {
					errorMessage = ERROR_BOOK_QUANTITY;
					return NavigationBean.shoppingCart_ToUserPage();
				}
				toUpdate.setQuantity(toUpdate.getQuantity() - b.getQuantity());

			}
		}
		bookMan.setBooks(bkManBooks);
		return NavigationBean.shoppingCart_ToUserPage();
	}

	public BookManagerBean getBookMan() {
		return bookMan;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
