package com.cn.service.var.impl;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import com.cn.mapper.var.VarMapper;
import com.cn.service.var.VarService;

@Service("varService")
public class VarServiceImpl implements VarService {
	
	@Resource(name = "varMapper")
	private VarMapper varMapper;
	
	@Override
	public List<Map<Integer, String>> importEx(InputStream input) {
		List<Map<Integer,String>> listMap = new ArrayList<Map<Integer,String>>();
		try {
			XSSFWorkbook wb = new XSSFWorkbook(input);
			if(wb == null)return null;
			XSSFSheet sheet = wb.getSheetAt(0);
			for(int rowNum = 1; rowNum < sheet.getLastRowNum(); rowNum++){
				XSSFRow row = sheet.getRow(rowNum);
				if (row == null) continue;
				Map<Integer,String> map = new HashMap<Integer,String>();
				for(int cellNum = 0; cellNum < row.getLastCellNum(); cellNum++){
					XSSFCell cell = row.getCell(cellNum);
					int cellType = cell.getCellType();
					if(cellType == cell.CELL_TYPE_STRING){
						map.put(cellNum, cell.getStringCellValue());
					}else if(cellType == cell.CELL_TYPE_NUMERIC){
						map.put(cellNum, String.valueOf(cell.getNumericCellValue()) );
					}else {
						map.put(cellNum, "");
					}
				}
				listMap.add(map);
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return listMap;
	}

	@Override
	public List<Map<String, String>> formatMap(List<Map<Integer, String>> listMap) {
		List<Map<String ,String>> list = new ArrayList<Map<String,String>>();
		if (listMap == null) return null;
		for(Map<Integer,String> map : listMap){
			Map<String,String> strMap = new HashMap<String,String>();
			strMap.put("var1", map.get(0));
			strMap.put("var2", map.get(1));
			strMap.put("var3", map.get(2));
			strMap.put("var4", map.get(3));
			strMap.put("var5", map.get(4));
			list.add(strMap);
		}
		return list;
	}

	@Override
	public boolean insert(List<Map<String, String>> list) {
		boolean flag = false;
		try{
			for(Map<String,String> map : list){
				varMapper.insert(map);
			}
			flag = true;
		}catch(Exception e){
			e.printStackTrace();
			flag = false;
		}
		return flag;
	}
	
	public int insertVar(Map<String,String> map){
		int num = 0;
		num = varMapper.insertVar(map);
		return num;
	}

	

}
