package com.edu118.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.edu118.entity.Dept;
import com.edu118.mapper.DeptMapper;

@Service
public class DeptService extends ServiceAdapter<Dept, Long>{
	@Autowired
	private DeptMapper deptMapper;
	
	@Override
	public List<Dept> findAll(){
		return deptMapper.selectAll();
	}
	
//	//定义查询的方式
//	public Map<String, Set<String>> findRolesAndPermissionsByDid(Long did){
//		Set<String> roles = employeeMapper.findRolesByEid(did);
//		Set<String> permissions = employeeMapper.findPermissionByEid(did);
//		HashMap<String, Set<String>> map = new HashMap<>();
//		map.put("roles", roles);
//		map.put("per,missions", permissions);
//		return map;
//	}
}
