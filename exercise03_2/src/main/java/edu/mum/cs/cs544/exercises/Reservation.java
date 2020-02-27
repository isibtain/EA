package edu.mum.cs.cs544.exercises;

import java.util.Date;

public class Reservation {
	
	private long id;
	private Date date;
	
	public Reservation() {
	}
	
	public Reservation(long id, Date date) {
		this.date = date;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
}
