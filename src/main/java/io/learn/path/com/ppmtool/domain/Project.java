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
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

@Entity
@Data
public class Project {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
		
	@NotBlank(message = "Project name is required")
	private String projectName;
	
	@NotBlank(message = "Project Identifier is required")
	@Size(min=4, max=5, message ="Please use 4 to 5 characters")
	@Column(updatable = false, unique = true)
	private String projectIdentifier;
	
	@NotBlank(message = "Project description name is required")
	private String description;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date start_date;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date end_date;
	
	@JsonFormat(pattern = "yyyy-mm-dd")
	@Column(updatable = false)
	private Date create_At;

	@JsonFormat(pattern = "yyyy-mm-dd")
	private Date updated_At;

	@PrePersist
	protected void onCreate() {
		this.create_At = new Date();
	}

	@PreUpdate 
	protected void onUpdate() {
		this.updated_At = new Date();
	}

}
