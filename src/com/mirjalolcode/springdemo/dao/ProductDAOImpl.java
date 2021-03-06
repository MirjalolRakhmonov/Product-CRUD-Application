package com.mirjalolcode.springdemo.dao;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.mirjalolcode.springdemo.entity.Product;

@Repository
public class ProductDAOImpl implements ProductDAO {
	
	// Session factory needs to be injected
	@Autowired
	private SessionFactory sessionFactory;

	@Override
	public List<Product> getProducts() {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// create a query
		Query<Product> theQuery=currentSession.createQuery("from Product", Product.class);
		
		// execute query and get result
		List<Product> products=theQuery.getResultList();
		
		return products;
	}
 
	@Override
	public void saveProduct(Product theProduct) {
		// get current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// save the product
		currentSession.saveOrUpdate(theProduct);
	}

	@Override
	public Product getProduct(int theId) {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// now retrieve/read from database using primary key
		Product theProduct=currentSession.get(Product.class, theId);
		
		return theProduct;
	}

	@Override
	public void deleteProduct(int theId) {
		// get the current hibernate session
		Session currentSession=sessionFactory.getCurrentSession();
		
		// delete object with primary key
		Query theQuery =
				currentSession.createQuery("delete from Product where id=:productId");
		
		theQuery.setParameter("productId", theId);
		
		theQuery.executeUpdate();
	}

}
