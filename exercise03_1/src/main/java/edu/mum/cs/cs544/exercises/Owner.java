package edu.mum.cs.cs544.exercises;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;

@Entity
public class Owner {
	
	@Column(nullable = true)
	@Id
	@GeneratedValue
	private long idwala;
	private String name;
	private String address;
	@OneToMany(cascade = CascadeType.PERSIST)
	private List<Car> cars = new ArrayList();
	
	public List<Car> getCars() {
		return cars;
	}

	public void setCars(List<Car> cars) {
		this.cars = cars;
	}

	public Owner() {}
	
	public Owner(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public long getId() {
		return idwala;
	}
	public void setId(long id) {
		this.idwala = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}

	@Override
	public String toString() {
		return "Owner [id=" + idwala + ", name=" + name + ", address=" + address + "]";
	}
	
	

}
