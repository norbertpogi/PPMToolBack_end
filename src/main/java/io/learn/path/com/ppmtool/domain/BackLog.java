package io.learn.path.com.ppmtool.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Data;

@Entity
@Data
public class BackLog {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	private  Integer PTSequence = 0;
	private String projectIdentifier;
	
	//OneToOne with project
	
	//OneTomany projectTasks

}
