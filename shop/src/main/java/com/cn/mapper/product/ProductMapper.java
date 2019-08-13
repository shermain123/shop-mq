package com.cn.mapper.product;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.domain.Good;
import com.cn.domain.produ.Product;


public interface ProductMapper {
	
	List<Product> getProductList(Map<String,Object> map);
	
	int getProductCount(Map<String,Object> map);
	
	int insert(Product product);
	
	int insertGD(@Param("gid") String id ,@Param("name")String name,@Param("descs")String descs);
	
	int insertPro(String id,String proName,String proQty,String proType,String proState,String price,String discount,String descs);
	
	List<Product> getList(String id);
}
