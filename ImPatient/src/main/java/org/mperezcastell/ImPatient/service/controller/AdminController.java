package org.mperezcastell.ImPatient.service.controller;



import java.lang.reflect.Array;
import java.security.Principal;
import java.util.Collection;
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
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;




@Controller
public class AdminController {
	
	static final Logger LOG = LoggerFactory.getLogger(AdminController.class);
	
	@Autowired
	private PatientRepository patients;
	
	@Autowired
	private AppointmentRepository appointments;
	
	@Autowired
	private UserCredentialRepository userCredentials;
	
	@Autowired
	private QueueManager mQManager;
	
//	APIs for Admins
	
//	// Build list
//	
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
//	@RequestMapping(value = "/admin/buildlist", method = RequestMethod.GET)
//	public @ResponseBody List<Appointment> buildList() {
//		ArrayList<Appointment> appointmentList = appointments.findByArrivalCheck(true);
//		Collections.sort(appointmentList);
//		return appointmentList;		
//	}
	
	@PreAuthorize("hasRole('ROLE_ADMIN', 'ROLE_PATIENT)")
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public @ResponseBody Collection<UserCredential> entryPage(Principal p) {
//		SecurityContext context = SecurityContextHolder.getContext();
//		Authentication auth = context.getAuthentication();
//		String authorities = (String) auth.getAuthorities().toString();
//		return authorities.substring(1, authorities.length()-1);
		return userCredentials.findByUserName(p.getName());
	}
	
	// Refresh List
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin/getlist", method = RequestMethod.GET)
	public @ResponseBody List<Appointment> getList() {
		mQManager.initQueue();
		return mQManager.mQueue;
	}
	
	// Delete patient from queue
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin/delete/{id}", method = RequestMethod.GET)
	public @ResponseBody List<Appointment> deletePatient(@PathVariable("id") String patientId) {
		mQManager.initQueue();
		Patient p = patients.findByRecordNumber(patientId);	
		return mQManager.deletePatient(p);
	}
	
	// Delete patient from queue
	@PreAuthorize("hasRole('ROLE_ADMIN')")
	@RequestMapping(value = "/admin/gethello", method = RequestMethod.GET)
	public @ResponseBody String getHello() {
		return "Hello";
	}


}
