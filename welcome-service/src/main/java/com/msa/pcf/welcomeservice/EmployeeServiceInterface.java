package com.msa.pcf.welcomeservice;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.cloud.netflix.ribbon.RibbonClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name="zuul-server")
@RibbonClient(name="employee-service")
public interface EmployeeServiceInterface {

	@GetMapping("/employee-service/employee/{empId}")
	public EmployeeDetails getEmployeeDetails(@PathVariable("empId") Long empId);
	
}
