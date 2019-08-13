package com.cn.controller.product;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import com.alibaba.fastjson.JSONArray;
import com.cn.domain.Good;
import com.cn.domain.produ.Product;
import com.cn.service.product.ProductService;
import com.cn.utils.Constants;
import com.cn.utils.Page;


@Controller
@RequestMapping("/produ")
public class ProductController {
					
	@Resource(name = "productService")
	private ProductService productService;
	
	@RequestMapping("/product")
	public String product(){
		return "product/productList";
	}
	
	@RequestMapping(value="/getProductList")
	@ResponseBody
	public Page<Product> getProductList(
			@RequestParam(value = "page", required = false) String page,
			@RequestParam(value = "limit", required = false, defaultValue = Constants.PAGE_SIZE) String limit)throws Throwable{
		Map<String,Object> map = new HashMap<String,Object>();
		map.put("orderby", "");
		map.put("order", "");
		map.put("page", page);
		map.put("limit", limit);
		Page<Product> pageList = productService.getProductList(map);
		return pageList;
	}
	
	//接收json数组	 导出页面选中的数据 excel
	@RequestMapping(value="/excelPro",method=RequestMethod.POST)
	@ResponseBody
	public String excelPro(@RequestBody Product[]product,HttpServletResponse response){
		Map<String,Object> result = new HashMap<String,Object>();
		List<Product> list = Arrays.asList(product);
		String [] rowName = {"id","名称","数量","物品类型","状态","价格","上架时间","修改时间","折扣","描述"};
		
		productService.excelPro(list,rowName,response);
		result.put("flag", "s");
		return JSONArray.toJSONString(result);
	}
	
	//接收
	@RequestMapping(value="/excelProJson")
	@ResponseBody 
	public String excelProJson(Product product){
		String o = product.getProName();
		System.out.println(product.getId());
		System.out.println(product.getProName());
		/*Object o = request.getParameter("check");
		JSONArray jsonArray =  JSONArray.parseArray(request.getParameter("check"));
		List<Product> list = (List<Product>) jsonArray.toJavaObject(jsonArray, Product.class);
		System.out.println(list.size());*/
		return null;
	}
	
	//import excel
	@RequestMapping(value = "/importProduct")
	@ResponseBody
	public String savePro(Model model,HttpServletRequest request){
		
		MultipartResolver resolver = new CommonsMultipartResolver(request.getSession().getServletContext());
		MultipartHttpServletRequest multipartRequest = resolver.resolveMultipart(request);
		MultipartFile addFile = multipartRequest.getFile("postLoanReport");
		productService.importExcel(addFile);
		return null;
	}
	
	//下载excel
	@RequestMapping("/exportData1")
	public void exportData1(HttpServletResponse response){
		String [] rowName = {"id","名称","数量","物品类型","状态","价格","上架时间","修改时间","折扣","描述"};
		productService.exportData1(rowName,response);
	}
	
}
