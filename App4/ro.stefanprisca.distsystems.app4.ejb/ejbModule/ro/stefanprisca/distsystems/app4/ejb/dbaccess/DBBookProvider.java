package ro.stefanprisca.distsystems.app4.ejb.dbaccess;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.stefanprisca.distsystems.app4.ejb.common.Constants;
import ro.stefanprisca.distsystems.app4.ejb.models.Book;
import ro.stefanprisca.distsystems.app4.ejb.models.Publisher;

public class DBBookProvider {
	private final EntityManagerFactory factory;
	private final EntityManager em;
	protected List<Book> books;

	public DBBookProvider() {
		factory = Persistence
				.createEntityManagerFactory(Constants.DB_PERSIST_UNIT_NAME);

		em = factory.createEntityManager();
		// read the existing entries and write to console
		Query q = em.createQuery("select b from Book b");
		this.books = q.getResultList();
	}

	public List<Book> getBooks() {
		Query q = em.createQuery("select b from Book b");
		this.books = q.getResultList();
		return this.books;
	}

	public Book getBook(Long id) {
		Book b = em.find(Book.class, id);
		return b;
	}

	public void beginTransaction() {
		em.getTransaction().begin();
	}

	public void addBook(Book newBook) {
		Publisher pb = newBook.getPub();
		if (em.find(Publisher.class, pb.getId()) == null) {
			em.persist(pb);
		}
		em.persist(newBook);
	}

	public void endTransaction() {
		em.getTransaction().commit();
	}

	@Override
	protected void finalize() throws Throwable {
		this.books = null;
		em.close();
		super.finalize();
	}

	public Publisher getPublisher(String name) {
		Query q = em.createQuery(
				"SELECT p FROM Publisher p WHERE p.name = :name").setParameter(
				"name", name);

		List<Publisher> pubs = q.getResultList();
		if (pubs.isEmpty()) {
			Publisher pub = new Publisher();
			pub.setName(name);
			em.persist(pub);
			return (Publisher) em
					.createQuery(
							"SELECT p FROM Publisher p WHERE p.name = :name")
					.setParameter("name", name).getSingleResult();
		}
		return pubs.get(0);
	}
}
