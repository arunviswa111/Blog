package com.niit.dao;

import java.util.List;

import org.hibernate.Query;

import org.hibernate.SessionFactory;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.Transactional;

import com.niit.model.Userdetails;

@EnableTransactionManagement
@Repository("userdetailsDAO")
public class UserdetailsDAOImpl implements UserdetailsDAO {
	
	@Autowired
	private SessionFactory sessionFactory;
	
	
	public UserdetailsDAOImpl(SessionFactory sessionFactory)
	{
	this.sessionFactory = sessionFactory;
	}
	
@Transactional
	public boolean save(Userdetails userdetails)
	{
	try {
		// Session session = sessionFactory.getCurrentSession();
	
		sessionFactory.getCurrentSession().save(userdetails);
		return true;
	}
	catch(Exception e)
	{
		e.printStackTrace();
		return false;
	}
	}


@Transactional
public boolean update(Userdetails userdetails)
{
try {

	sessionFactory.getCurrentSession().update(userdetails);
	return true;
}
catch(Exception e)
{
	e.printStackTrace();
	return false;
}
}



@Transactional
	public boolean delete(String id)
	{
	try {
		
		
			Userdetails CategoryToDelete = new Userdetails();
			CategoryToDelete.setUserid(id);
			sessionFactory.getCurrentSession().delete(CategoryToDelete);
		
		return true;
	}
	catch(Exception e)
	{
		
		e.printStackTrace();
		return false;
	}
	}

@Transactional
public Userdetails get(String userid)
{
	String hql = "from Userdetails where userid= "+" '" +userid+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	@SuppressWarnings("unchecked")
	List<Userdetails> list = query.list();
	if(list == null || list.isEmpty())
	{
		return null;
	}
	else
	{
		return list.get(0);
	}
}

@Transactional
public Userdetails authenticate(String userid, String password) {
	System.out.println("DAO IMPLEMENTATION..");
	String hql = "from Userdetails where userid= '" + userid + "' and " + " password ='" + password + "'";
	Query query = sessionFactory.getCurrentSession().createQuery(hql);

	
	List<Userdetails> list = (List<Userdetails>) query.list();

	if (list != null && !list.isEmpty()) {
		return list.get(0);
	}

	return null ;
}

@SuppressWarnings("unchecked")
@Transactional
public List<Userdetails> list()
{
	String hql = "from Userdetails";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	return query.list();
}

@Transactional
public void setOnLine(String userid)
{
	String hql ="update Userdetails SET is_online='Y' where userid= "+" '" +userid+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.executeUpdate();
}

@Transactional
public void setOffLine(String userid)
{
	String hql ="update Userdetails SET is_online='N' where userid= "+" '" +userid+ "'";
	Query query =sessionFactory.getCurrentSession().createQuery(hql);
	query.executeUpdate();
	
}
}








