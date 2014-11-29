package ro.stefanprisca.distsystems.app4.ejb.beans;

import java.util.List;

import javax.ejb.Remote;

import ro.stefanprisca.distsystems.app4.ejb.models.Book;

@Remote
public interface BookProviderRemote {
	public String getServerConfirmation();

	public List<Book> getBooks();

	public void updateBook(Book newBook);

	public Book getBook(Long id);

}
