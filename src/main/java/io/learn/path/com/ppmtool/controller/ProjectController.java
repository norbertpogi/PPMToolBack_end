package io.learn.path.com.ppmtool.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.learn.path.com.ppmtool.domain.Project;
import io.learn.path.com.ppmtool.services.ProjectService;
import io.learn.path.com.ppmtool.validation.exception.ValidationError;

@RestController
@RequestMapping("/api/project")
public class ProjectController {

	ProjectService projectService;
	ValidationError validationError;

	@Autowired
	public ProjectController(ProjectService projectService, ValidationError validationError) {		
		this.projectService = projectService;
		this.validationError = validationError;
	}
	
	@PostMapping("")
	public ResponseEntity<?> createNewProject(@Valid @RequestBody Project project, BindingResult result) {
		ResponseEntity<?> errorMap = validationError.MapValidationError(result);		
		if(null != errorMap) return errorMap;		
		return new ResponseEntity<Project>(projectService.createProject(project), HttpStatus.CREATED);
	}
	
	@GetMapping("/{projectIdentity}")
	public ResponseEntity<?> getProjectByIdentity(@PathVariable String projectIdentity) {
		return new ResponseEntity<Object>(projectService.findProjectByIdentity(projectIdentity.toUpperCase()), 
				HttpStatus.OK);
	}
	
	@GetMapping("/all")
	public Iterable<?> getAllProject() {
		return projectService.findAllProjects();
	}
	
	@DeleteMapping("/{projectIdentity}")
	public ResponseEntity<?> deleteProjectByIdentity(@PathVariable String projectIdentity) {		
		Object project = projectService.deleteProjectByIdentity(projectIdentity);
		return new ResponseEntity<Object>(project, HttpStatus.OK);
	}
	
	

}













