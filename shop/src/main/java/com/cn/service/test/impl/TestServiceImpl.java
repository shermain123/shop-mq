package com.cn.service.test.impl;

import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.cn.mapper.test.TestMapper;
import com.cn.service.test.TestService;




@Service("testService")
public class TestServiceImpl implements TestService {

	@Resource(name = "testMapper")
	private TestMapper testMapper;
	
	@Override
	public int insertTest(Map<String, String> map) {
		int count = 0;
		count = testMapper.insertTest(map);
		return count;
	}

	
	

}
