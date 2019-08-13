package com.cn.controller;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.fastjson.JSONArray;
import com.cn.domain.produ.Product;
import com.cn.service.test.TestService;

@Controller
@RequestMapping("/testCon")
public class TestController {
	
	@Resource(name = "testService")
	private TestService testService;
	
	@RequestMapping("/inserTest")
	public String insertTest(){
		Map<String,String> map = new HashMap<String,String>();
		map.put("var1", "var1");
		map.put("var2", "var2");
		map.put("var3", "var3");
		map.put("var4", "var4");
		map.put("var5", "var5");
		int count = testService.insertTest(map);
		return "";
	}
	
	@RequestMapping("/getTest")
	public String getTest(HttpServletRequest request) throws MalformedURLException{
		String root = request.getRealPath("/");
		String realPath = request.getRealPath(request.getRequestURI());
		String rootPath = request.getSession().getServletContext().getRealPath("/");
		URL classPath = request.getSession().getServletContext().getResource("/");
		
		System.out.println("1--------------"+root);
		System.out.println("2--------------"+realPath);
		System.out.println("3--------------"+rootPath);
		System.out.println("4--------------"+classPath);
		System.out.println("5--------------"+request.getSession().getServletContext().getResourceAsStream(""));
		System.out.println("6--------------"+request.getSession().getServletContext());
		return "";
	}
	
	@RequestMapping("/getContext")
	public String getContext(@RequestParam(value="source",required=false)ServletContextEvent sce){
		ServletContext application = sce.getServletContext();
		String contextPath = application.getContextPath();
		System.out.println("contextPath------------------"+contextPath);
		return "";
	}
}
