package com.cn.utils;

import java.io.IOException;
import java.io.InputStream;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

import com.cn.domain.act.Act;

public class ImportAct {

	public static List<Act> importActPoi(InputStream fis){
		List<Act> infos = new ArrayList<Act>();
		Act act = null;
		try {

			HSSFWorkbook hwb = new HSSFWorkbook(fis);

			HSSFSheet sheet = hwb.getSheetAt(0);
			HSSFRow row = null;
			DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
			for(int i = 0;i < sheet.getPhysicalNumberOfRows(); i++){
				row = sheet.getRow(i);
				act = new Act();
				if(ImportAct.getCellValue(row.getCell(0)) != null && !"".equals(ImportAct.getCellValue(row.getCell(0)))){
					act.setId(Integer.parseInt(ImportAct.getCellValue(row.getCell(0))));
				}
				act.setName(ImportAct.getCellValue(row.getCell(1)));
				act.setDesc(ImportAct.getCellValue(row.getCell(2)));
				infos.add(act);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	 private static String getCellValue(HSSFCell cell){   
	        String value = null;   
	        //简单的查检列类型   
	        switch(cell.getCellType())   
	        {   
	            case HSSFCell.CELL_TYPE_STRING://字符串   
	                value = cell.getRichStringCellValue().getString();   
	                break;   
	            case HSSFCell.CELL_TYPE_NUMERIC://数字   
	                long dd = (long)cell.getNumericCellValue();   
	                value = dd+"";   
	                break;   
	            case HSSFCell.CELL_TYPE_BLANK:   
	                value = "";   
	                break;      
	            case HSSFCell.CELL_TYPE_FORMULA:   
	                value = String.valueOf(cell.getCellFormula());   
	                break;   
	            case HSSFCell.CELL_TYPE_BOOLEAN://boolean型值   
	                value = String.valueOf(cell.getBooleanCellValue());   
	                break;   
	            case HSSFCell.CELL_TYPE_ERROR:   
	                value = String.valueOf(cell.getErrorCellValue());   
	                break;   
	            default:   
	                break;   
	        }   
	        return value;   
	    }  
}
