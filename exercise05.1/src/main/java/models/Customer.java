package models;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

@Entity
public class Customer {

	@Id
	@GeneratedValue
	private long id;
	private String firstname;
	private String lastname;
	@OneToMany (mappedBy = "owner", cascade = CascadeType.ALL)
	private List<Order> orders = new ArrayList();
	
	public Customer() {
	}
	
	public Customer(String firstname, String lastname, List<Order> orders) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.orders = orders;
	}
	
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public List<Order> getOrders() {
		return orders;
	}
	public void setOrders(List<Order> orders) {
		this.orders = orders;
	}
	
	
	
}
