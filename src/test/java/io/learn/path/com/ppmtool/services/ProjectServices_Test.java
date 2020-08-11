package io.learn.path.com.ppmtool.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import io.learn.path.com.ppmtool.domain.Project;
import io.learn.path.com.ppmtool.repositories.ProjectRepository;
import io.learn.path.com.ppmtool.validation.exception.ProjectIdException;

@SpringBootTest
class ProjectServices_Test {
	
	
	@Mock
	private ProjectRepository projectRepository;
	
	@InjectMocks
	ProjectServiceImpl projectService;
	List<Project> list = getDataList();
	
	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
	}

	@Test
	public void findAllProjects_test() {
		 when(projectRepository.findAll()).thenReturn(list);
		 Iterable<Project> result = projectService.findAllProjects();
		 List<Project> projectList = new ArrayList<Project>();
		 result.forEach(projectList::add);
		 
		 assertThat(result).hasSize(3);
		 assertMemberFields(projectList.get(0));
		 
		 verify(projectRepository, times(1)).findAll();
	}
	
	@Test
	public void findProjectByIdentity_test() {
		when(projectRepository.findByProjectIdentifier("E001")).thenReturn(getDataList().get(0));
		Project project = projectService.findProjectByIdentity("E001");
		
		assertThat(!project.equals(null)).isTrue();
		
		assertMemberFields(project);
	}
	
	@Test
	public void createProject_test() {
		when(projectRepository.save(list.get(0))).thenReturn(list.get(0));
		Project project = projectService.createProject(list.get(0));
		
		assertMemberFields(project);
		
		verify(projectRepository, times(1)).save(list.get(0));
	}

	@Test
	public void deleteProjectByIdentity_test() {
		try {
			projectService.deleteProjectByIdentity("E001");
			verify(projectRepository, times(1)).delete(list.get(0));
				
		}catch(ProjectIdException ex) {
			ex.getMessage();
		}
		
	}

	private void assertMemberFields(Project project) {
		assertThat(project.getId()).isInstanceOf(Long.class);
		assertThat(project.getProjectIdentifier()).isEqualTo("E001");
		assertThat(project.getProjectName()).isEqualTo("ProjectName1");
		
	}

	private List<Project> getDataList() {
		List<Project> dataList = new ArrayList<Project>();
		Project pr1 = getData("pr1");
		Project pr2 = getData("pr2");
		Project pr3 = getData("pr3");
		dataList.add(pr1);
		dataList.add(pr2);
		dataList.add(pr3);
		return dataList;
	}


	private Project getData(String str) {				
		Project project = new Project();
		String data = str;
		Long generalId = "pr1".equals(data) ? 1L : ("pr2".equals(data) ? 2L : 3L);
		String identifier = "pr1".equals(data) ? "E001" : ("pr2".equals(data) ? "E002" : "E003");
		String name = "pr1".equals(data) ? "ProjectName1" : ("pr2".equals(data) ? "ProjectName2" : "ProjectName3");
		String description = "pr1".equals(data) ? "ProjectDescription1" : ("pr2".equals(data) ? "ProjectDescription2" : "ProjectDescription3");
		project.setId(generalId);
		project.setCreate_At(new Date());		
		project.setProjectIdentifier(identifier);
		project.setProjectName(name);
		project.setDescription(description);		
		project.setStart_date(new Date());	
		return project;
	}
	
	


}
