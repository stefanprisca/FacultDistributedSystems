package ro.stefanprisca.distsystems.app4.ejb.client.beans;

import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.stefanprisca.distsystems.app4.ejb.beans.BookProviderRemote;
import ro.stefanprisca.distsystems.app4.ejb.client.utils.ClientUtility;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;
import ro.stefanprisca.distsystems.app4.ejb.models.Publisher;

@ManagedBean(name = "bookManager", eager = true)
@SessionScoped
public class BookManagerBean {

	private List<Book> books;
	private Book book;
	private final BookProviderRemote bkProvider;

	public BookManagerBean() {
		bkProvider = ClientUtility.doLookup();
		books = bkProvider.getBooks();
		this.book = new Book();
		this.book.setPub(new Publisher());
	}

	public String editBook(Long bookId) {
		this.book = bkProvider.getBook(bookId);
		return NavigationBean.admin_ToEditBookPage();
	}

	public String editBook() {
		this.book = new Book();
		this.book.setId(-1l);
		this.book.setPub(new Publisher());
		return NavigationBean.admin_ToEditBookPage();
	}

	public String confEditBook() {
		bkProvider.updateBook(book);
		books = bkProvider.getBooks();

		return NavigationBean.editB_ToAdminPage();
	}

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

}
