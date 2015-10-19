package org.mperezcastell.ImPatient.service.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PatientRepository extends CrudRepository<Patient, Long>{

	public Patient findByRecordNumber(String recordNumber);
	public Patient findByUserName(String userName);

		
}
