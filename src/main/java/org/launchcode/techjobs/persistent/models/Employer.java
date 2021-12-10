package org.launchcode.techjobs.persistent.models;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Entity
public class Employer extends AbstractEntity {

	@NotBlank(message = "Location required")
	@Size(min = 3, max = 100, message = "Location must be between 3 and 100 characters")
	private String location;

	public Employer() {
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}
}
