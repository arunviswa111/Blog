package com.niit.model;

import java.util.UUID;

import javax.persistence.*;

import org.springframework.stereotype.Component;

@Entity
@Component
@Table(name="Blog")
public class Blog extends BaseDomain{
	@Id
	private String id;
	@Column
	private String title;
	@Column
	private String description;
	@Column
	private String userid;
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	
	public Blog()
	{
		this.id="BLOG" + UUID.randomUUID().toString().substring(24).toUpperCase();
	}
		

}
