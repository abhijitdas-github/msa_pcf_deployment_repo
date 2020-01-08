package com.msa.pcf.employeeservice;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;


@RestController
@EnableHystrix
public class EmployeeController {
	
	@Autowired
	EmployeeRepository employeeRepository;
	
	@Autowired
	EmployeeConfiguration employeeConfiguration;
	
	@Autowired
	Environment environment;

	@GetMapping("/employee/{empId}")
	@HystrixCommand(fallbackMethod="fallBackEmployeeDetails")
	public Employee getEmployeeDetails(@PathVariable Long empId) {
		
		Employee employee = employeeRepository.findOne(empId);
		
		employee.setPort(Integer.parseInt(environment.getProperty("local.server.port")));
		//throw new RuntimeException();
		return employee;
		//return new Employee("AAA","BBB",101L,new Date());
	}
	
	public Employee fallBackEmployeeDetails(Long empId) {
		String firstName = employeeConfiguration.getDefaultFirstName();
		String lastName = employeeConfiguration.getDefaultLastName();
		return new Employee(firstName,lastName,empId,new Date());
	}
}
