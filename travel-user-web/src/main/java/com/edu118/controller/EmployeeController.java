package com.edu118.controller;
import java.time.LocalDateTime;

import java.time.format.DateTimeFormatter;

import javax.servlet.http.HttpServletRequest;

import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.edu118.api.BaseController;
import com.edu118.entity.Employee;
import com.edu118.service.EmployeeService;
import com.edu118.util.md5.PinYinUtils;
import com.edu118.utils.md5.MD5Utils;
import com.edu118.validateGroup.IAddGroup;

@Controller
@RequestMapping("/pages")
public class EmployeeController extends BaseController{
	
	@Autowired
	private EmployeeService employeeService;
	/*
	 * 获取添加信息表单的数据
	 */
	@PostMapping("/addEmp")
	@RequiresPermissions("emp:add")
	public ModelAndView addEmp(
			HttpServletRequest request,
			@Validated(IAddGroup.class) Employee emp,
			MultipartFile file) {
		System.out.println("已获取emp:add权限！");
		
		//获取当前请求的SessionId
		String sessionId = request.getHeader("xydLoginToke");
		System.out.println("测试，Session共享，请求头的SessionaId= "+sessionId);
		//获取当前的时间 
		LocalDateTime localDate = LocalDateTime.now();
		//localDate.format(DateTimeFormatter.ISO_LOCAL_DATE);
		String date = localDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		emp.setHiredate(date);
		
		//获取拼音
		String py = PinYinUtils.getPinYinHeadChar(emp.getEname());
		emp.setEmpNumber("XYD-" + py + "-" 
				+ localDate.format(DateTimeFormatter.ofPattern("yyyyMMddHHmmss")));
		//加密
		emp.setPassword(MD5Utils.getPassword(emp.getPassword()));
		
		logger.info("Emp = {}",emp);
		logger.info("file = {}",file);
		
		if (null != file) {		//上传文件
			String contentType = file.getContentType();
			System.out.println("上传文件的类型：" + contentType);
			//对类型进行逻辑处理，如果不是图片，返回错误信息。
			if("image/jpg|image/jpeg|image/png".contains(contentType)){}
			String fileName = upLoadFile(request,file,emp.getEmpNumber());
			emp.setPhoto(fileName);
		}
		//保存
		employeeService.insertEntity(emp);
		
		ModelAndView modelAndView = new ModelAndView("/pages/emp/emp_add.jsp");
		modelAndView.addObject("message", "数据添加完成！");
		return modelAndView;
	}
	
	//测试抽取结果
//	@GetMapping("/test")
//	@RequiresPermissions("emp:add")
//	public ModelAndView addEmp() {
//		System.out.println("测试，拥有emp:add权限！！");
//		return null;
//	}
	
	
}