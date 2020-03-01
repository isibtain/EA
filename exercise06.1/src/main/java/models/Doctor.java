package models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@NoArgsConstructor
@Table(name = "DOCTOR")
public class Doctor {
	
    @Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

    @Column(name = "TYPE", nullable = false)
    private String doctorType;

    @Column(name = "FIRSTNAME", nullable = false)
    private String firstname;

    @Column(name = "LASTNAME", nullable = false)
    private String lastname;
    
    
    
    

	public Doctor(String doctorType, String firstname, String lastname) {
		this.doctorType = doctorType;
		this.firstname = firstname;
		this.lastname = lastname;
	}

	public Doctor() {
	}

	public String getDoctorType() {
		return doctorType;
	}

	public void setDoctorType(String doctorType) {
		this.doctorType = doctorType;
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
    
    

}
