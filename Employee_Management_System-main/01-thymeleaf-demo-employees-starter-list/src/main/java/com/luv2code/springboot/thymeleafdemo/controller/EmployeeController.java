package com.luv2code.springboot.thymeleafdemo.controller;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;
import jakarta.annotation.PostConstruct;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	// load employee data
	private EmployeeService employeeService;

	public EmployeeController(EmployeeService the){
		employeeService=the;
	}
	@PostConstruct
	private void loadData() {

		// create employees

	}

	// add mapping for "/list"

	@GetMapping("/list")
	public String listEmployees(Model theModel) {

		// add to the spring model
		List<Employee>theEmployees=employeeService.findAll();
		theModel.addAttribute("employees", theEmployees);

		return "employees/list-employees";
	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int theId,Model theModel){
		Employee theEmployee=employeeService.findById(theId);

		theModel.addAttribute("employee",theEmployee);
		return "employees/employee-form";
	}
	@GetMapping("delete")
	public String delete(@RequestParam("employeeId") int theId){

		employeeService.deleteById(theId);

		return "redirect:/employees/list";
	}
	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel){
		Employee theEmployee=new Employee();
		theModel.addAttribute("employee",theEmployee);
		return "employees/employee-form";
	}
	@PostMapping("/save")
	public String saveEmployees(@ModelAttribute("employee")Employee theEmployee){
		employeeService.save(theEmployee);
//		System.out.println(theEmployee);
		return "redirect:/employees/list";
	}
}









