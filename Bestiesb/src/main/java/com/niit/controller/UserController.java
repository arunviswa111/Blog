package com.niit.controller;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.niit.dao.FriendDAO;
import com.niit.dao.UserdetailsDAO;
import com.niit.model.Userdetails;
@RestController
public class UserController {
	
	private static final Logger log=LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserdetailsDAO userdetailsDAO;
	@Autowired
	Userdetails userdetails;
	@Autowired
	FriendDAO friendDAO;
	
	//for list
	@RequestMapping(value="/users", method=RequestMethod.GET)
	public ResponseEntity<List<Userdetails>> listAllUsers(){
		log.debug("-->Calling method listAllUsers");
		List<Userdetails> user=userdetailsDAO.list();
		if(user.isEmpty()){
			return new ResponseEntity<List<Userdetails>>(HttpStatus.NO_CONTENT);
		}
		return new ResponseEntity<List<Userdetails>>(user,HttpStatus.OK);
		
	}
	//to create users
	@RequestMapping(value="/createusers/", method=RequestMethod.POST)
	public ResponseEntity<Userdetails> createusers(@RequestBody Userdetails userdetails){
		log.debug("-->Calling method createUsers");
		if(userdetailsDAO.get(userdetails.getUserid())==null){
			userdetailsDAO.save(userdetails);
			return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
		}
		log.debug("-->User already exist"+userdetails.getUserid());
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
		}

	//to get user by user id
	@RequestMapping(value="/user/{userid}",method=RequestMethod.GET)
	public ResponseEntity<Userdetails> getuser(@PathVariable("userid")String id)
	{
	log.debug("-->calling get method");
	Userdetails userdetails=userdetailsDAO.get(id);
	if(userdetails==null)
	{
		log.debug("-->User does not exist");
		userdetails = new Userdetails();
		userdetails.setErrorcode("404");
		userdetails.setErrormessage("User not found");
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.NOT_FOUND);
	}
	log.debug("-->User exist");
	return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
	}
	
	//update user by user id
	@RequestMapping(value="/user/{userid}",method=RequestMethod.PUT)
	public ResponseEntity<Userdetails> updateuser(@PathVariable("userid")String id)
	{
	log.debug("-->calling update method");
	if(userdetailsDAO.get(id)==null)
	{
		log.debug("-->User does not exist");
		userdetails = new Userdetails();
		userdetails.setErrorcode("404");
		userdetails.setErrormessage("User not found");
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.NOT_FOUND);
	}
	userdetailsDAO.update(userdetails);
	log.debug("-->User updated successfully");
	return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
	
}
	//delete user
	@RequestMapping(value="/user/{userid}",method=RequestMethod.DELETE)
	public ResponseEntity<Userdetails> deleteuser(@PathVariable("userid")String id)
	{
		log.debug("-->calling delete method");
		Userdetails userdetails=userdetailsDAO.get(id);
		if(userdetails==null)
		{
			log.debug("-->User does not exist");
			userdetails = new Userdetails();
			userdetails.setErrorcode("404");
			userdetails.setErrormessage("Blog not found");
			return new ResponseEntity<Userdetails>(userdetails,HttpStatus.NOT_FOUND);
		}
		userdetailsDAO.delete(id);
		log.debug("-->User deleted successfully");
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
		}
	
	
	//authentication
	@RequestMapping(value="/user/authenticate",method=RequestMethod.POST)
	public ResponseEntity<Userdetails> authenticateuser(@RequestBody Userdetails userdetails,HttpSession session)
	{
		log.debug("-->calling authenticate method");
		userdetails=userdetailsDAO.authenticate(userdetails.getUserid(), userdetails.getPassword());
		if(userdetails==null)
		{
			log.debug("-->User does not exist");
			userdetails = new Userdetails();
			System.out.println("User does not exist");
			userdetails.setErrorcode("404");
			userdetails.setErrormessage("User does not exist");
	}
		else
		{
			userdetails.setErrorcode("200");
			log.debug("-->User exist with above credentials");
			session.setAttribute("loggegInUser",userdetails);
			session.setAttribute("loggedInUserId", userdetails.getUserid());
			friendDAO.setOnLine(userdetails.getUserid());
			userdetailsDAO.setOnLine(userdetails.getUserid());
		}
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
	}
	
	@RequestMapping(value="/user/logout/",method=RequestMethod.POST)
	public ResponseEntity<Userdetails> logout(HttpSession session)
	{
		userdetails= userdetailsDAO.authenticate(userdetails.getUserid(), userdetails.getPassword());
		friendDAO.setOffLine(userdetails.getUserid());
		userdetailsDAO.setOffLine(userdetails.getUserid());
		session.invalidate();
		return new ResponseEntity<Userdetails>(userdetails,HttpStatus.OK);
	}
}