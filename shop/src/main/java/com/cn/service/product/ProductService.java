package com.cn.service.product;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.springframework.web.multipart.MultipartFile;

import com.cn.domain.produ.Product;
import com.cn.utils.Page;


public interface ProductService {
	
	Page<Product> getProductList(Map<String,Object> map);
	
	boolean insert(Product product);
	
	void excelPro(List<Product> list,String [] rowName,HttpServletResponse response);
	
	String importExcel(MultipartFile addFile); 
	
	void exportData1(String [] rowName,HttpServletResponse response);
	
}
