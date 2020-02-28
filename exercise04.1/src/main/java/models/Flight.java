package models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@Entity
public class Flight {
	
	@Id
	@GeneratedValue
	private long id;
	private int flightnumber;
	@Column(name = "Tooo")
	private String to;
	@Column(name = "Frrom")
	private String from;
	private Date date;
	
	
	public Flight() {	}

	public Flight(int flightnumber, String to, String from, Date date) {
		super();
		this.flightnumber = flightnumber;
		this.to = to;
		this.from = from;
		this.date = date;
	}

	public int getFlightnumber() {
		return flightnumber;
	}
	
	public void setFlightnumber(int flightnumber) {
		this.flightnumber = flightnumber;
	}
	public String getTo() {
		return to;
	}
	public void setTo(String to) {
		this.to = to;
	}
	public String getFrom() {
		return from;
	}
	public void setFrom(String from) {
		this.from = from;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	

}
