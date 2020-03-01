package models;

import java.time.LocalDate;
import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;


@Entity
@Table(name = "APPOINTMENT")
public class Appointment {
	
	@Id
    @GeneratedValue
    @Column(name = "ID")
    private Long id;

	@Temporal(TemporalType.DATE)
    @Column(name = "APPDATE", nullable = false)
    private Date date;

    @Embedded
    private Payment payment;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "PATIENT")
    private Patient patient;

    @OneToOne
    @JoinColumn(name = "DOCTOR")
    private Doctor doctor;

	public Date getDate() {
		return date;
	}

	public Appointment() {
	}

	public Appointment(Date date, Payment payment, Patient patient, Doctor doctor) {
		this.date = date;
		this.payment = payment;
		this.patient = patient;
		this.doctor = doctor;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	public Payment getPayment() {
		return payment;
	}

	public void setPayment(Payment payment) {
		this.payment = payment;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Doctor getDoctor() {
		return doctor;
	}

	public void setDoctor(Doctor doctor) {
		this.doctor = doctor;
	}
    
    

}
