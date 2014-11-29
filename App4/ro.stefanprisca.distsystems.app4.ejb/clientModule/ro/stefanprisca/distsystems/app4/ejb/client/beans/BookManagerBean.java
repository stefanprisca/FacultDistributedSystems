package ro.stefanprisca.distsystems.app4.ejb.client.beans;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import ro.stefanprisca.distsystems.app4.ejb.beans.BookProviderRemote;
import ro.stefanprisca.distsystems.app4.ejb.client.utils.ClientUtility;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;
import ro.stefanprisca.distsystems.app4.ejb.models.Publisher;

@ManagedBean(name = "bookManager", eager = true)
@SessionScoped
public class BookManagerBean implements Serializable {
	private static final long serialVersionUID = -5192688042447978994L;

	private static ShoppingCartBean shoppingCart;

	public static void cleanCart() {
		shoppingCart = null;
	}

	private final BookProviderRemote bkProvider;

	private List<Book> books;
	private Book book;

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

	public String takeBook(Book b) {
		if (shoppingCart == null) {
			shoppingCart = new ShoppingCartBean();
		}

		// System.out.println("\n\n\n Adding boook in shopping cart "
		// + b.getName() + " ============================= \n\n");

		shoppingCart.addBook(b);

		// for (Book b2 : shoppingCart.getBooks()) {
		// System.out.println("User page Book from shopping cart: "
		// + b2.getName());
		// }
		return "";
	}

	public String toShoppingCart() {

		if (shoppingCart == null) {
			shoppingCart = new ShoppingCartBean();
		}

		// System.out
		// .println("\n\n\n going to shop cart \n ============================= \n\n");
		// for (Book b : shoppingCart.getBooks()) {
		// System.out.println("User page Book from shopping cart: "
		// + b.getName());
		// }
		return NavigationBean.user_ToShoppingCart();
	}

	// getters and setters ------------------------

	public List<Book> getBooks() {
		return books;
	}

	public void setBooks(List<Book> books) {

		for (Book b : books) {
			bkProvider.updateBook(b);
		}

		this.books = bkProvider.getBooks();
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public ShoppingCartBean getShoppingCart() {
		return shoppingCart;
	}

	public void setShoppingCart(ShoppingCartBean shoppingCart) {
		this.shoppingCart = shoppingCart;
	}

}
