package org.mperezcastell.ImPatient.service.repository;

import java.util.Collection;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

@RepositoryRestResource(path = "/credential")
public interface UserCredentialRepository extends CrudRepository<UserCredential, String>{

		// Find all credentials with a matching name
		public Collection<UserCredential> findByUserName(@Param("name") String userName);
		
}
