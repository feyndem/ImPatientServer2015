package org.mperezcastell.ImPatient.service.repository;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PrePersist;

import com.fasterxml.jackson.annotation.JsonIgnore;

@Entity
public class Patient {
	
	@PrePersist
	private void ensureUserName(){
	    this.setUserName(this.getName());
	}

	@Id
	private String recordNumber;
	private String firstName;
	private String lastName;
	private String userName;
	
	public Patient() {
		super();
	}

	public Patient(String recordNumber, String firstName, String lastName) {
		super();
		this.recordNumber = recordNumber;
		this.firstName = firstName;
		this.lastName = lastName;
	}
		
	public String getRecordNumber() {
		return recordNumber;
	}

	public void setRecordNumber(String recordNumber) {
		this.recordNumber = recordNumber;
	}
	
	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	
	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	
	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}
	
	@JsonIgnore
    public String getName() {
        String name = "";
        if (firstName != null && !firstName.isEmpty()) name += firstName;
        if (!name.isEmpty()) name += " ";
        if (lastName != null  && !lastName.isEmpty()) name+= lastName;
        return name;
    }


	

}
