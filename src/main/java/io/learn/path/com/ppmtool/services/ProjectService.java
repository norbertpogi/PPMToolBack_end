package io.learn.path.com.ppmtool.services;

import io.learn.path.com.ppmtool.domain.Project;

public interface ProjectService {

	Project createProject(Project project);
	
	Project findProjectByIdentity(String projectIdentity);
	
	Iterable<Project> findAllProjects();
	
	Object deleteProjectByIdentity(String projectIdentity);
	
	
}
