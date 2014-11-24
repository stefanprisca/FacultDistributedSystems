package ro.stefanprisca.distsystems.app4.ejb.dbaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.stefanprisca.distsystems.app4.ejb.common.Constants;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;

public class DBBookProvider {
	private final EntityManagerFactory factory;
	protected List<Book> books;

	public DBBookProvider() {
		factory = Persistence
				.createEntityManagerFactory(Constants.DB_PERSIST_UNIT_NAME);

		EntityManager em = factory.createEntityManager();
		// read the existing entries and write to console
		Query q = em.createQuery("select b from Book b");
		this.books = q.getResultList();
		em.close();
	}

	public List<Book> getBooks() {
		if (this.books == null) {
			EntityManager em = factory.createEntityManager();
			Query q = em.createQuery("select b from Book b");
			this.books = q.getResultList();
			em.close();
		}
		return this.books;
	}
}
