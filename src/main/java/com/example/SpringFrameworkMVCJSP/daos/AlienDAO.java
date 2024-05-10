package com.example.SpringFrameworkMVCJSP.daos;

import java.util.List;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.example.SpringFrameworkMVCJSP.models.Alien;

import jakarta.transaction.Transactional;

@Component
public class AlienDAO {
	@Autowired
	private SessionFactory sessionFactory;
	
	// org.hibernate.HibernateException:
	//   Could not obtain transaction-synchronized Session for current thread
	// When working with databases we need to handle transactions, so we need to
	// initialize and commit the transaction. In Hibernate that must be done manually.
	// Using SpringORM we can use Transactional annotation, so we do not need to do that.
	@Transactional
	public List<Alien> getAll() {
		Session session = sessionFactory.getCurrentSession();
		List<Alien> aliens = session.createQuery("from Alien", Alien.class).list();
		return aliens;
	}
	
	@Transactional
	public void addAlien(Alien alien) {
		Session session = sessionFactory.getCurrentSession();
		session.persist(alien);
	}
	
	@Transactional
	public Alien getById(int id) {
		Session session = sessionFactory.getCurrentSession();
		Alien alien = session.get(Alien.class, id);
		return alien;
	}
}
