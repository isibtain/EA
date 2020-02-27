package edu.mum.cs.cs544.exercises;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Owner {
	
	@Id
	@GeneratedValue
	private long id;
	private String name;
	private String address;
	
	public Owner() {}
	
	public Owner(String name, String address) {
		this.name = name;
		this.address = address;
	}

	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
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
		return "Owner [id=" + id + ", name=" + name + ", address=" + address + "]";
	}
	
	

}
