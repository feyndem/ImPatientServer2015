package org.mperezcastell.ImPatient.service;

import java.util.Calendar;
import java.util.Collections;
import java.util.List;

import org.mperezcastell.ImPatient.service.repository.Appointment;
import org.mperezcastell.ImPatient.service.repository.AppointmentRepository;
import org.mperezcastell.ImPatient.service.repository.Patient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class QueueManager {
	
	public List<Appointment> mQueue;
	private Calendar mCalendar = Calendar.getInstance();
	
	@Autowired
	AppointmentRepository appointments;
	

	public QueueManager () {
		super();
	}

	
	public List<Appointment> getmQueue() {
		if (mQueue == null) 
			initQueue();
		return mQueue;
	}

	public void setmQueue(List<Appointment> mQueue) {
		this.mQueue = mQueue;
	}
	
	public List<Appointment> patientDelay (Patient p) {
		for (Appointment a : mQueue) {
			if (a.getPatient() == p) {
				mCalendar.setTime(a.getAppointmentTime());
				mCalendar.add(Calendar.MINUTE, 5);
				a.setAppointmentTime(mCalendar.getTime());				
				appointments.save(mQueue);
			}
		}
		return mQueue;
	}
	
	public List<Appointment> deletePatient (Patient p) {
		for (Appointment a : mQueue) {
			if (a.getPatient() == p) {
				mQueue.remove(a);
				a.setArrivalCheck(false);
				appointments.save(mQueue);
			}
		}
		return mQueue;
	}

	public void initQueue () {
		List<Appointment> c = appointments.findByArrivalCheck(true);
		Collections.sort(c);
		this.mQueue = c;
	}
	
}
