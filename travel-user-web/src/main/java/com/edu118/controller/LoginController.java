package com.edu118.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.apache.shiro.subject.Subject;
import org.springframework.stereotype.Controller;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.edu118.api.BaseController;
import com.edu118.entity.Employee;
import com.edu118.validateGroup.ILoginGroup;

@Controller
public class LoginController extends BaseController{
	/*
	 * 获取登录表单到的数据
	 */
	Logger logger = LogManager.getLogger(LoginController.class);
	
	@PostMapping("/login")
	public ModelAndView login(@Validated(ILoginGroup.class)Employee emp,HttpSession session) {
//		logger.info("登录表单的数据 ：{}",emp); 
		System.out.println("登录表单的数据>>>>>>>>"+emp);
		
		ModelAndView modelAndView = new ModelAndView("pages/index.jsp");
		try {
			UsernamePasswordToken token  =  new UsernamePasswordToken(emp.getEid(),emp.getPassword());
			Subject subject = SecurityUtils.getSubject();
			subject.login(token);
//			登录成功，把sessionId保存到session域中
			String sessionId = subject.getSession().getId().toString();
			session.setAttribute("loginSessionId", sessionId);
			System.out.println("登录成功，生成的SessionId= "+sessionId);
			
		} catch (Exception e) {
			// TODO: handle exception
			//如果登陆失败，用户将会被锁定或者用户名和密码错误,返回登录界面
			modelAndView.addObject("logMsg", e.getMessage());
			modelAndView.setViewName("login.jsp");
		}
		//如果登录成功，进入到index.jsp页面
		return modelAndView;
	}
	
	//测试登录时获取到的SessionId
	@GetMapping("/test")
	@RequiresPermissions("emp:add")
	public ModelAndView addEmp(HttpServletRequest request) {
		String sessionId = request.getHeader("xydLoginToke");
		System.out.println("测试，Session共享，请求头的SessionId= "+sessionId);
		return null;
	}
}
