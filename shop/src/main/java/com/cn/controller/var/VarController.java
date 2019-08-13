package com.cn.controller.var;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.cglib.transform.MethodFilter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartFile;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.cn.service.var.VarService;

/**
 * POI导入、导出、下载 ;layui上传
 * */

@Controller
@RequestMapping("/vat")
public class VarController {
	
	@Resource(name = "varService")
	private VarService varService;
	
	@RequestMapping("/index")
	public String index(){
		return "var/index";
	}
	
	@RequestMapping("/varUpload")
	public String varUpload(){
		return "var/varUpload";
	}
	
	//poi导出 下载excel
	@RequestMapping("/exportModel")
	public void exportModel(Model model,HttpServletResponse response){
		InputStream input = null;
		ServletOutputStream outputStream = null;
		try{ 
			String fileName = "";
			input = this.getClass().getResourceAsStream("/excel/10011.xlsx");
			int b = 0;
			byte [] buffer = new byte[512];
			fileName = "10011.xlsx";
			String trans = new String(fileName.getBytes("UTF-8"),"ISO-8859-1");
			response.setHeader("content-disposition", "attachment;filename="+trans);
			response.setContentType("multipart/form-data");
			outputStream = response.getOutputStream();
			while ((b = input.read(buffer)) != -1) {
				outputStream.write(buffer, 0, b);
			}
			
		}catch(IOException ex){
			ex.printStackTrace();
		}finally{
			try{
				if(input != null){
					input.close();
				}
				if(outputStream != null){
					outputStream.close();
					response.flushBuffer();
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
	}
	
	//poi导入
	@RequestMapping(value = "/importEx",method = {RequestMethod.POST,RequestMethod.GET})
	@ResponseBody
	public String importEx(Model model,HttpServletRequest request,@RequestParam(value = "file",required = false)MultipartFile file){
		//@RequestParam(value = "上传文件页面name值",required = false)MultipartFile file
		List<Map<String, String>> excelDatas = new ArrayList<>();
		// 获取不到MultipartFile 对象时 使用以下方式获取
		/*MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		MultipartFile file = multipartRequest.getFile("LoanReport");*/
		
		Map<String,String> result = new HashMap<String,String>();
		try{
			InputStream input = file.getInputStream();
			List<Map<Integer,String>> listMap = varService.importEx(input);
			excelDatas = varService.formatMap(listMap);
			if (excelDatas == null) return null;
			boolean flag = varService.insert(excelDatas);
			result.put("flag", "true");
		}catch(IOException ex){
			ex.printStackTrace();
			result.put("flag","false");
		}finally{
			
		}
		
		return JSONArray.toJSONString(result);
	}
	
	//上传图片
	@RequestMapping(value = "/uploadImg")
	@ResponseBody
	public String uploadImg(HttpServletRequest request,@RequestParam(value = "file",required = false)MultipartFile file){
		
		Map<String,String> result = new HashMap<String,String>();
		String fileName = file.getOriginalFilename();
		String localPath = "D:\\"+fileName;
		String suffix = fileName.substring(fileName.lastIndexOf("."));
		InputStream input = null;
		FileOutputStream output = null;
		try {
			input = file.getInputStream();
			output = new FileOutputStream(localPath);
			byte [] temp = new byte[1024*8];
			int len = 0;
			while((len = input.read(temp)) != -1){
				System.out.println("len-------"+len);
				output.write(temp, 0, len);
			}
			result.put("result", "true");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			result.put("result", "false");
		}finally{
			try{
				if(output != null){
					output.close();
				}
				if(input != null){
					input.close();
				}
			}catch(IOException ex){
				ex.printStackTrace();
			}
		}
		return JSONArray.toJSONString(result);
	}
	

}
