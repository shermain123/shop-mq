package com.cn.service.var;

import java.io.InputStream;
import java.util.List;
import java.util.Map;

public interface VarService {

	List<Map<Integer,String>> importEx(InputStream input);
	
	List<Map<String,String>> formatMap(List<Map<Integer,String>> listMap);
	
	boolean insert(List<Map<String,String>> list);
	
	int insertVar(Map<String,String> map);
}
