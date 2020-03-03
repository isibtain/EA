package edu.mum.cs.cs544.exercises;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.PrimaryKeyJoinColumn;

import org.hibernate.annotations.Parameter;
@Entity
public class Address {
	
	@Id
	@GeneratedValue(generator = "myGenerator")
	@org.hibernate.annotations.GenericGenerator(
			name = "myGenerator",
			strategy = "foreign",
			parameters = @Parameter(name="property", value = "customer")
			)
	private int id;
	private String city;
	private String state;
	@OneToOne
	@PrimaryKeyJoinColumn	
	private Customer customer;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
	public String getState() {
		return state;
	}
	public void setState(String state) {
		this.state = state;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	

}
