package com.edu118.service;

import java.util.Map;
import java.util.Set;

import com.edu118.entity.Employee;

public class EmployeeService extends ServiceAdapter<Employee, String> implements IEmployeeService{

	@Override
	public Map<String, Set<String>> findRolesAndPermissionsByDid(Long did) {
		// TODO Auto-generated method stub
		return null;
	}

}
