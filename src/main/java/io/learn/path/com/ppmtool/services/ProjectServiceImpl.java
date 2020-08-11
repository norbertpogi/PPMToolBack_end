package io.learn.path.com.ppmtool.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import io.learn.path.com.ppmtool.constants.Constants;
import io.learn.path.com.ppmtool.domain.Project;
import io.learn.path.com.ppmtool.repositories.ProjectRepository;
import io.learn.path.com.ppmtool.validation.exception.ProjectIdException;
import io.learn.path.com.ppmtool.validation.exception.ProjectIdExceptionResponse;

@Service
public class ProjectServiceImpl implements ProjectService {

	ProjectRepository projectRepository;

	@Autowired
	public ProjectServiceImpl(ProjectRepository projectRepository) {		
		this.projectRepository = projectRepository;
	}

	@Override
	public Project createProject(Project project) {			
		try {
			project.setProjectIdentifier(project.getProjectIdentifier().toUpperCase());
			return projectRepository.save(project);
		}catch(Exception e) {			
			throw new ProjectIdException(Constants.PROJECT_IDENTITY + project.getProjectIdentifier() + Constants.ALREADY_EXISTS);
		}

	}

	@Override
	public Project findProjectByIdentity(String projectIdentity) {		
		return validateProjectByIdentity(projectIdentity);
	}


	@Override
	public Iterable<Project> findAllProjects() {		
		return projectRepository.findAll();
	}
	

	@Override
	public Object deleteProjectByIdentity(String projectIdentity) {		
		projectRepository.delete(validateProjectByIdentity(projectIdentity));
		ProjectIdExceptionResponse successResponse = new ProjectIdExceptionResponse(
				Constants.PROJECT_IDENTITY + projectIdentity + Constants.PROJECT_DELETED);
		return successResponse;
	}


	private Project validateProjectByIdentity(String projectIdentity) {
		Project project = projectRepository.findByProjectIdentifier(projectIdentity);
		if(null == project) {
			throw new ProjectIdException(Constants.PROJECT_IDENTITY + projectIdentity + Constants.NOT_FOUND);
		}
		return project;
	}



}
