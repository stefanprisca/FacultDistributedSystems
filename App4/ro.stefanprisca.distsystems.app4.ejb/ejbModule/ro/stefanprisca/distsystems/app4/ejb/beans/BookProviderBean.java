package ro.stefanprisca.distsystems.app4.ejb.beans;

import java.util.List;

import javax.ejb.Stateless;

import ro.stefanprisca.distsystems.app4.ejb.common.Messages;
import ro.stefanprisca.distsystems.app4.ejb.dbaccess.DBBookProvider;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;

/**
 * Session Bean implementation class BookProviderBean
 */
@Stateless
public class BookProviderBean implements BookProviderRemote {

	private final DBBookProvider dbBookProvider;

	public BookProviderBean() {
		dbBookProvider = new DBBookProvider();
	}

	@Override
	public String getServerConfirmation() {
		return Messages.EJB_CONNECTION_TEST;
	}

	@Override
	public List<Book> getBooks() {
		return dbBookProvider.getBooks();
	}

	@Override
	public void updateBook(Book newBook) {
		Book oldBook = dbBookProvider.getBook(newBook.getId());
		dbBookProvider.beginTransaction();
		newBook.setPub(dbBookProvider.getPublisher(newBook.getPub().getName()));
		if (oldBook == null) {
			dbBookProvider.addBook(newBook);
		} else {
			oldBook.setAuthor(newBook.getAuthor());
			oldBook.setName(newBook.getName());
			oldBook.setQuantity(newBook.getQuantity());
			oldBook.setPrice(newBook.getPrice());
			oldBook.setPub(newBook.getPub());
		}
		dbBookProvider.endTransaction();

	}

	@Override
	public Book getBook(Long id) {
		return dbBookProvider.getBook(id);
	}

}
