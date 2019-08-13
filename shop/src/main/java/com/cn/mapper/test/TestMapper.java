package com.cn.mapper.test;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Param;

import com.cn.domain.Good;
import com.cn.domain.produ.Product;


public interface TestMapper {
	
	int insertTest(Map<String,String> map);
}
