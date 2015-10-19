package org.mperezcastell.ImPatient.service.repository;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

import com.fasterxml.jackson.annotation.JsonManagedReference;


@Entity
public class Appointment implements Comparable<Appointment> {
	
	@Id
	@GeneratedValue
	private int id;
	
	@OneToOne
	@JsonManagedReference
	private Patient patient;
	
	private Date appointmentTime;
	private Boolean arrivalCheck;
	private Boolean treatmentCheck;
	private Boolean treatedCheck;
	
	public Appointment() {
		super();
	}
	
	public Appointment(Patient patient, Date time) {
		this.patient = patient;
		this.appointmentTime = time;
		this.arrivalCheck = false;
		this.treatmentCheck = false;
		this.treatedCheck = false;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Patient getPatient() {
		return patient;
	}

	public void setPatient(Patient patient) {
		this.patient = patient;
	}

	public Date getAppointmentTime() {
		return appointmentTime;
	}

	public void setAppointmentTime(Date time) {
		this.appointmentTime = time;
	}

	public Boolean getArrivalCheck() {
		return arrivalCheck;
	}

	public void setArrivalCheck(Boolean arrivalCheck) {
		this.arrivalCheck = arrivalCheck;
	}

	public Boolean getTreatmentCheck() {
		return treatmentCheck;
	}

	public void setTreatmentCheck(Boolean treatmentCheck) {
		this.treatmentCheck = treatmentCheck;
	}

	public Boolean getTreatedCheck() {
		return treatedCheck;
	}

	public void setTreatedCheck(Boolean treatedCheck) {
		this.treatedCheck = treatedCheck;
	}

	@Override
	public int compareTo(Appointment o) {
		return this.getAppointmentTime().compareTo(o.getAppointmentTime());
	}
	
	
}
