package models;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

@Entity
public class Laptop {
	
	@Id
	@GeneratedValue
	private long id;
	private String brand;
	private String type;
	@ManyToOne
	private Employee employee;
	
	public Laptop() {}
	
	public Laptop(String brand, String type, Employee employee) {
		super();
		this.brand = brand;
		this.type = type;
		this.employee = employee;
	}


	public String getBrand() {
		return brand;
	}
	public void setBrand(String brand) {
		this.brand = brand;
	}
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	public Employee getEmployee() {
		return employee;
	}

	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	@Override
	public String toString() {
		return "Laptop [id=" + id + ", brand=" + brand + ", type=" + type + ", employee=" + employee + "]";
	}
	
	
	
	
}
