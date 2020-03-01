package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.SecondaryTables;
import javax.persistence.Table;
import javax.persistence.*;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@SecondaryTables(
        @SecondaryTable(name = "ADDRESS", pkJoinColumns = {
                @PrimaryKeyJoinColumn(name = "PATIENT_ID", referencedColumnName = "id")
        }))
@Table(name = "PATIENT")
public class Patient {

    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", nullable = false)
    private String name;

    @Column(table = "ADDRESS", name = "STREET", nullable = false)
    private String street;
    @Column(table = "ADDRESS", name = "ZIP", nullable = false)
    private String zip;
    @Column(table = "ADDRESS", name = "CITY", nullable = false)
    private String city;
    
    
    
	public Patient() {
	}
	
	public Patient(String name, String street, String zip, String city) {
		this.name = name;
		this.street = street;
		this.zip = zip;
		this.city = city;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStreet() {
		return street;
	}
	public void setStreet(String street) {
		this.street = street;
	}
	public String getZip() {
		return zip;
	}
	public void setZip(String zip) {
		this.zip = zip;
	}
	public String getCity() {
		return city;
	}
	public void setCity(String city) {
		this.city = city;
	}
    
    
}