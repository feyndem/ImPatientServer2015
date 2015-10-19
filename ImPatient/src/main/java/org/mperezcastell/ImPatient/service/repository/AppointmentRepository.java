package org.mperezcastell.ImPatient.service.repository;

import java.util.ArrayList;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AppointmentRepository extends CrudRepository<Appointment, Long>{

	public ArrayList<Appointment> findByArrivalCheck(Boolean arrivalcheck);
	public Appointment findByPatient(Patient patient);

		
}
