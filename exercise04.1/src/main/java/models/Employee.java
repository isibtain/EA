package models;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;


@Entity
public class Employee {
	
	@Id
	@GeneratedValue
	private Long id;
	private String firstname;
	private String lastname;
	@OneToMany(mappedBy = "employee" ,  cascade = CascadeType.PERSIST)
	private Set<Laptop> laptops = new HashSet();
	
	public Employee(String firstname, String lastname, Set<Laptop> laptops) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.laptops = laptops;
	}

	public Employee() {	}
	
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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

	public Set<Laptop> getLaptops() {
		return laptops;
	}

	public void setLaptops(Set<Laptop> laptops) {
		this.laptops = laptops;
	}
	
	
	
	

}
