package com.edu118.service;

import java.util.List;
import java.util.Map;
import java.util.Set;

/*
 * 定义一个接口，存放集合对象
 */
import com.edu118.entity.Employee;

public interface IEmployeeService extends IBaseService<Employee, String>{
	
	public List<Employee> findByExample(Employee entity,String flag);
	public Map<String, Set<String>> findRolesAndPermissionsByDid(Long did);
}


