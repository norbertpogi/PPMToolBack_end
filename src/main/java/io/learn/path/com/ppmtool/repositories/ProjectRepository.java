package io.learn.path.com.ppmtool.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import io.learn.path.com.ppmtool.domain.Project;

@Repository
public interface ProjectRepository extends CrudRepository<Project, Long>{
	
	Project findByProjectIdentifier(String projectIdentity);
	

	
	
}
