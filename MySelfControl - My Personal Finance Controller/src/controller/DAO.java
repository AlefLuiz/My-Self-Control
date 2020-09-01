package controller;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

public class DAO<E> {
	private static EntityManagerFactory emf;
	private EntityManager em;
	private Class<E> entity;

	public DAO(Class<E> entity) {
		this.entity = entity;
		em = emf.createEntityManager();
	}

	public DAO() {
		this(null);
	}

	public static void openConnection() {
		emf = Persistence.createEntityManagerFactory("MySelfControl - My Personal Finance Controller");
	}

	public DAO<E> openTransaction() {
		em.getTransaction().begin();
		return this;
	}

	public DAO<E> insertObject(E object) {
		em.persist(object);
		return this;
	}

	public DAO<E> mergeObject(E object) {
		em.merge(object);
		return this;
	}

	public DAO<E> closeTransaction() {
		em.getTransaction().commit();
		return this;
	}

	public DAO<E> insertAttomic(E object) {
		return this.openTransaction().insertObject(object).closeTransaction();
	}

	public DAO<E> mergeAttomic(E object) {
		return this.openTransaction().mergeObject(object).closeTransaction();
	}

	public void close() {
		em.close();
	}

	public List<E> getAllList() {
		if (entity == null) {
			throw new UnsupportedOperationException("Entity null.");
		}
		String jpql = "select e from " + entity.getName() + " e";
		TypedQuery<E> query = em.createQuery(jpql, entity);
		return query.getResultList();
	}
	
	public List<E> getAllListByFilter(String filter) {
		if (entity == null) {
			throw new UnsupportedOperationException("Entity null.");
		}
		String jpql = "select e from " + entity.getName() + " e where " + filter;
		TypedQuery<E> query = em.createQuery(jpql, entity);
		return query.getResultList();
	}

	public void removeById(String id) {
		this.openTransaction();
		em.remove(em.find(entity, id));
		this.closeTransaction();
	}
	public void removeById(Long id) {
		this.openTransaction();
		em.remove(em.find(entity, id));
		this.closeTransaction();
	}

	public E getById(String id) {
		return em.find(entity, id);
	}
}
