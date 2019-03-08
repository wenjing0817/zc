package com.zy.login.controller;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.zy.login.pojo.Login;
import com.zy.login.service.LoginService;
import com.zy.util.YzmUtil;

@Controller
public class LoginController {
	
	@Autowired
	private LoginService ser;
	
	@RequestMapping("/toIndex")
	public String toIndex(){
		return "index";
	}
	
	@RequestMapping("/login/yzm")
	public void getYzm(HttpServletRequest request,HttpServletResponse response){
		try {
			YzmUtil.CreateYZM(request, response);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping("/login/getYzm")
	@ResponseBody
	public Map<String,Object> getYzm(HttpSession session){
		Map<String,Object> map = new HashMap<>();
		map.put("yzm", session.getAttribute("yzm"));
		return map;
	}
	
	@RequestMapping("/login/login")
	@ResponseBody
	public Map<String,Object> login(HttpSession session,Login login){
		Map<String,Object> map = new HashMap<>();
		try {
			map = ser.login(session,login);
		} catch (Exception e) {
			e.printStackTrace();
			map.put("seccess", false);
			map.put("msg", "系统异常!");
		}
		return map;
	}
}
