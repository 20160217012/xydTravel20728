package com.edu118.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.edu118.api.BaseController;
import com.edu118.service.IEmployeeService;

import lombok.Setter;

@Controller
public class TravelController extends BaseController{
	
	@Setter
	private IEmployeeService employeeService;
	@GetMapping("findTest")
	@ResponseBody
	public Object findTest() {
		System.out.println("获取到的employeeService：    "+employeeService);
		return employeeService.findByExample(null, null);
	}
	
}
