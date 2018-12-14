package com.msa.pcf.welcomeservice;

import java.io.Serializable;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.netflix.hystrix.EnableHystrix;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RestController
@EnableHystrix
public class WelcomeController implements Serializable{
	
	private Integer id;
    private String firstName;
    private String lastName;
    private static final long serialVersionUID = 1L;
    
    @Autowired
    EmployeeDetails employeeDetails;
    
    @Autowired
    EmployeeServiceInterface employeeServiceInterface;
    
	@GetMapping("/welcome/{empId}")
	@HystrixCommand(fallbackMethod="fallBackEmployeeDetails")
	public EmployeeDetails getEmployeeDetails(@PathVariable Long empId) {
		EmployeeDetails employeeDetails = employeeServiceInterface.getEmployeeDetails(empId);
		return employeeDetails;
	}
	
	@Override
    public String toString() {
        return "Employee Details [id=" + id + ", firstName=" + firstName
                + ", lastName=" + lastName + "]";
    }
	
	public EmployeeDetails fallBackEmployeeDetails(Long empId) {
		EmployeeDetails defaultEmployeeDetails = new EmployeeDetails("Test","Data",101L);
		return defaultEmployeeDetails;
	}
}
