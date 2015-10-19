package org.mperezcastell.ImPatient.service.controller;

import java.security.Principal;
import java.util.Calendar;
import java.util.List;

import org.mperezcastell.ImPatient.service.QueueManager;
import org.mperezcastell.ImPatient.service.repository.Appointment;
import org.mperezcastell.ImPatient.service.repository.AppointmentRepository;
import org.mperezcastell.ImPatient.service.repository.Patient;
import org.mperezcastell.ImPatient.service.repository.PatientRepository;
import org.mperezcastell.ImPatient.service.repository.UserCredential;
import org.mperezcastell.ImPatient.service.repository.UserCredentialRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class PatientController {
	
	static final Logger LOG = LoggerFactory.getLogger(PatientController.class);
	
	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private UserCredentialRepository userCredentials;
	
	@Autowired
	private AppointmentRepository appointments;
	
	@Autowired
	private QueueManager mQManager;
	
//	APIs for Patients
	
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/patient", method = RequestMethod.POST)
	public @ResponseBody Patient addPatient(@RequestBody Patient patient) {
		Patient savedPatient =  patients.save(patient);
		if (savedPatient != null) {
			// whenever we create a new patient then we add their credentials too
			addCredentials(savedPatient);
		}
		return savedPatient;
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@RequestMapping(value="/patient/delay", method=RequestMethod.GET)
	public @ResponseBody List<Appointment> getDelay(Principal principal) {
		Patient p = patients.findByUserName(principal.getName());
		mQManager.initQueue();
		mQManager.patientDelay(p);
		return mQManager.getmQueue(); 		
	}
	
	@PreAuthorize("hasRole('ROLE_PATIENT')")
	@RequestMapping(value="/patient/appointment", method=RequestMethod.GET)
	public @ResponseBody Appointment getAppointment(Principal principal) {
		Patient p = patients.findByUserName(principal.getName());
		Appointment a = appointments.findByPatient(p);
		//Calendar c = Calendar.getInstance();
		//c.setTime(a.getAppointmentTime());
		//return "Appointment Time: " + c.get(Calendar.HOUR_OF_DAY) + ":" + c.get(Calendar.MINUTE);
		return a;
	}
	

	
	
// Private methods
	
	private void addCredentials(Patient patient) {
		UserCredential credential = new UserCredential();
		credential.setUserId(patient.getRecordNumber());
		credential.setPassword("pass");
		credential.setUserName(patient.getName());  
		credential.setUserType(UserCredential.UserRole.PATIENT); 
		UserCredential saved = userCredentials.save(credential);
		if (saved == null) {
			LOG.error("ERROR : Credentials did not SAVE!!");
		} else {
			LOG.debug("Credential SAVED is: " + saved.toString());
		}
	}

}
