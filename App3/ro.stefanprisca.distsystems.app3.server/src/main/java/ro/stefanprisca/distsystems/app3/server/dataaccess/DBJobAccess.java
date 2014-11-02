package ro.stefanprisca.distsystems.app3.server.dataaccess;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.Persistence;
import javax.persistence.Query;

import ro.stefanprisca.distsystems.app3.common.IJob;
import ro.stefanprisca.distsystems.app3.server.core.Constants;

@SuppressWarnings("unchecked")
public class DBJobAccess {

	private final EntityManager em;

	public DBJobAccess() {
		em = Persistence.createEntityManagerFactory(
				Constants.PERSISTENCE_UNIT_NAME).createEntityManager();
	}

	public List<IJob> getJobs() {
		return em.createQuery("select j from Job j").getResultList();
	}

	public List<IJob> getJobs(String categoryName) {
		List<IJob> jobs = new ArrayList<IJob>();
		Query q = em
				.createQuery("select j from Job j JOIN j.categories c WHERE c.name = :name");
		q.setParameter("name", categoryName);

		jobs.addAll(q.getResultList());
		return jobs;
	}
}
