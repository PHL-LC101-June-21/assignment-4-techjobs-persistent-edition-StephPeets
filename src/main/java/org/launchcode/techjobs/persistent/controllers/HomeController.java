package org.launchcode.techjobs.persistent.controllers;

import org.launchcode.techjobs.persistent.models.Employer;
import org.launchcode.techjobs.persistent.models.Job;
import org.launchcode.techjobs.persistent.models.Skill;
import org.launchcode.techjobs.persistent.models.data.EmployerRepository;
import org.launchcode.techjobs.persistent.models.data.JobRepository;
import org.launchcode.techjobs.persistent.models.data.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

/**
 * Created by LaunchCode
 */
@Controller
public class HomeController {

	@Autowired
	JobRepository jobRepository;

	@Autowired
	EmployerRepository employerRepository;

	@Autowired
	SkillRepository skillRepository;

	@RequestMapping("")
	public String index(Model model) {
		model.addAttribute("title", "My Jobs");
		model.addAttribute("jobs", jobRepository.findAll());
		return "index";
	}

	@GetMapping("add")
	public String displayAddJobForm(Model model) {
		model.addAttribute("title", "Add Job");
		model.addAttribute("employers", employerRepository.findAll());
		model.addAttribute("skills", skillRepository.findAll());
		model.addAttribute("job", new Job());
		return "add";
	}

	@PostMapping("add")
	public String processAddJobForm(@ModelAttribute @Valid Job newJob, Errors errors, Model model, @RequestParam int employerId, @RequestParam List<Integer> skills) {

		String result = "redirect:";

		if (errors.hasErrors()) {
			model.addAttribute("title", "Add Job");
			result = "add";
		}

		Optional<Employer> optEmployer = employerRepository.findById(employerId);
		if (optEmployer.isPresent()) {
			Employer employer = optEmployer.get();
			newJob.setEmployer(employer);
		}
		List<Skill> skillObjs = (List<Skill>) skillRepository.findAllById(skills);
		newJob.setSkills(skillObjs);
		model.addAttribute("employerId", employerId);
		jobRepository.save(newJob);
		return result;
	}

	@GetMapping("view/{jobId}")
	public String displayViewJob(Model model, @PathVariable int jobId) {
		model.addAttribute("job", jobRepository.findById(jobId));
		return "view";
	}

}
