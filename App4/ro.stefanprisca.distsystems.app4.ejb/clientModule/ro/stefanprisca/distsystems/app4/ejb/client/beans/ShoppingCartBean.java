package ro.stefanprisca.distsystems.app4.ejb.client.beans;

import java.util.ArrayList;
import java.util.List;

import ro.stefanprisca.distsystems.app4.ejb.models.Book;

public class ShoppingCartBean {

	private List<Book> books;

	public ShoppingCartBean() {
		this.books = new ArrayList<Book>();
	}

	public void addBook(Book b) {
		int index = books.indexOf(b);
		if (index != -1) {
			Book bk = books.get(index);
			bk.setQuantity(bk.getQuantity() + 1);

		} else {
			Book bk = cloneBook(b);

			// System.out.println("\n\n\n Adding boook in shopping cart "
			// + b.getName() + " ============================= \n\n");

			books.add(bk);
		}
	}

	public String removeBook(Long bookId) {
		books.removeIf(b -> b.getId().equals(bookId));
		return null;
	}

	public String back() {
		return NavigationBean.shoppingCart_ToUserPage();
	}

	private Book cloneBook(Book b) {
		Book clone = new Book();
		clone.setAuthor(b.getAuthor());
		clone.setId(b.getId());
		clone.setName(b.getName());
		clone.setPrice(b.getPrice());
		clone.setPub(b.getPub());
		clone.setQuantity(1);
		return clone;
	}

	public List<Book> getBooks() {
		// for (Book b : this.books) {
		// System.out.println("Book from shopping cart: " + b.getName());
		// }
		return books;
	}

	public void setBooks(List<Book> books) {
		this.books = books;
	}

}
