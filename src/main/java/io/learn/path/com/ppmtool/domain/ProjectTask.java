package io.learn.path.com.ppmtool.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;

import lombok.Data;

@Entity
@Data
public class ProjectTask {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@Column(updatable = false)
	private String projectSequence;
	
	@NotBlank(message = "Please include a project summary")
	private String summary;
	
	private String status;
	
	private Integer priority;
	
	private Date dueDate;
	//ManyToOne with project
	
	@Column(updatable = false)
	private String projectIdentifier;
	//ManyToOne with backlog
	
	private Date create_At;
	
	private Date update_At;
	
	@PrePersist
	protected void onCreate() {
		this.create_At = new Date();
	}
	
	@PreUpdate
	protected void onUPdate() {
		this.update_At = new Date();
	}
	
	
}
