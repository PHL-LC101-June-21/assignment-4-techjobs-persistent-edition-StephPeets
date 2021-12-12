package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Skill extends AbstractEntity {

	@Size(max = 300, message = "Description must be 300 characters or less")
	private String description;

	@ManyToMany(mappedBy = "skills")
	private final List<Job> jobs = new ArrayList();

	public Skill() {
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public List<Job> getJobs() {
		return jobs;
	}

}
