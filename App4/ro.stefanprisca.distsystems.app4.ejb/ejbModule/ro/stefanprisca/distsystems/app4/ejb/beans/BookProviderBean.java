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
	public void saveBooks(List<Book> newBooks) {
		// TODO Auto-generated method stub

	}

}
