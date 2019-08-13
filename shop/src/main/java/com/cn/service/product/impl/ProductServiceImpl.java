package com.cn.service.product.impl;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cn.domain.Good;
import com.cn.domain.produ.Product;
import com.cn.mapper.product.ProductMapper;
import com.cn.service.product.ProductService;
import com.cn.utils.Page;
import com.cn.utils.PageUtil;


@Service("productService")
public class ProductServiceImpl implements ProductService {

	@Resource(name = "productMapper")
	private ProductMapper productMapper;
	
	@Override
	public Page<Product> getProductList(Map<String,Object> map) {
		PageUtil.setStartEnd(map);
		List<Product> list = productMapper.getProductList(map);
		Page<Product> page = new Page<Product>();
		PageUtil.getPageFromMap(page, map);
		page.setData(list);
		int totalCount = productMapper.getProductCount(map);
		page.setCount(totalCount);
		return page;
	}

	@Override
	public boolean insert(Product product) {
		
		return productMapper.insert(product)>0;
	}
	
	/**
	 * 将数据 写入磁盘 导出页面选中的数据 excel
	 * */
	@Override
	public void excelPro(List<Product> list,String [] rowName,HttpServletResponse response) {
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("工作薄1");
		
		HSSFRow rown = sheet.createRow(0);
		HSSFCell cellTitle = rown.createCell(0);
		
		HSSFCellStyle columnTopStyle = this.getColumnTopStyle(wb);//获取列头样式对象
        // HSSFCellStyle style = this.getStyle(wb);                    //单元格样式对象
		cellTitle.setCellValue("商品表");
		
        HSSFRow rowRowName = sheet.createRow(1);                // 在索引2的位置创建行(最顶端的行开始的第二行)
        for(int n = 0; n < rowName.length; n++){
        	HSSFCell  cellRowName = rowRowName.createCell(n);                //创建列头对应个数的单元格
            cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
            cellRowName.setCellValue(text);                                    //设置列头单元格的值
            cellRowName.setCellStyle(columnTopStyle);                        //设置列头单元格样式
        }
        Product product = null;
        //将传入的集合设置到时对应的单元格中
        for(int i = 0;i < list.size(); i++){
        	product = list.get(i);
        	HSSFRow row = sheet.createRow(i+2);
        	row.createCell(0).setCellStyle(columnTopStyle);
        	row.createCell(0).setCellValue(product.getId());
        	row.createCell(1).setCellStyle(columnTopStyle);
        	row.createCell(1).setCellValue(product.getProName());
        	row.createCell(2).setCellStyle(columnTopStyle);
        	row.createCell(2).setCellValue(product.getProQty());
        	row.createCell(3).setCellStyle(columnTopStyle);
        	row.createCell(3).setCellValue(product.getProType());
        	row.createCell(4).setCellStyle(columnTopStyle);
        	row.createCell(4).setCellValue(product.getProState());
        	row.createCell(5).setCellStyle(columnTopStyle);
        	row.createCell(5).setCellValue(product.getPrice());
        	row.createCell(6).setCellStyle(columnTopStyle);
        	row.createCell(6).setCellValue(product.getCreDate());
        	row.createCell(7).setCellStyle(columnTopStyle);
        	row.createCell(7).setCellValue(product.getUpDate());
        	row.createCell(8).setCellStyle(columnTopStyle);
        	row.createCell(8).setCellValue(product.getDiscount());
        	row.createCell(9).setCellStyle(columnTopStyle);
        	row.createCell(9).setCellValue(product.getDescs());   	
       
        }
        if(wb !=null){
            try
	            {
	                String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
	               
	                String headStr = "attachment; filename=\"" + fileName + "\"";
	                FileOutputStream out = new FileOutputStream("d:\\"+fileName);
	                wb.write(out);
	                out.flush();
	                out.close();
	                
	                //因为ajax的返回类型只有xml,text,json,html!没有流类型所以下面代码不用出现浏览器下载框
	               /* response.reset();
	                response.setContentType("APPLICATION/OCTET-STREAM");
	                response.setHeader("Content-Disposition", "attachment; filename=student.xls");
	                OutputStream out = response.getOutputStream();
	                wb.write(out);*/
	                
	            }
	            catch (IOException e)
	            {
	                e.printStackTrace();
	            }
	        }
	   
	}
	
	/**
	 * 导入excel
	 * */
	@Override
	public String importExcel(MultipartFile addFile) {
		String fileName = addFile.getOriginalFilename();
		InputStream input = null;
		try {
			input = addFile.getInputStream();
			Workbook wb = null;
			String postfix = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
			if ("xls".equals(postfix)) {
				wb = new HSSFWorkbook(input);
			} else if ("xlsx".equals(postfix)) {
				wb = new XSSFWorkbook(input);
			}
			Good good = null;
			for( int i = 0; i < wb.getNumberOfSheets(); i++){
				Sheet sheet = wb.getSheetAt(i);
				for(int rowNum = 0; rowNum < sheet.getLastRowNum(); rowNum++){
					Row row = sheet.getRow(rowNum);
					if(row != null){
					/*	good = new Good();
						good.setGid(getValue(row.getCell(0)));
						good.setName(getValue(row.getCell(1)));
						good.setDescs(getValue(row.getCell(2)));
						*/
						productMapper.insertGD(getValue(row.getCell(0)),getValue(row.getCell(1)),getValue(row.getCell(2)));	
					}
				}
			}
			
		} catch (IOException e) {
			if(input != null){
				try {
					input.close();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			e.printStackTrace();
		}finally{
			if(input != null){
				try {
					input.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
	/**
	 *下载 excel
	 */
	@Override
	public void exportData1(String[] rowName, HttpServletResponse response) {
		
		List<Product> list = productMapper.getList(null);
		HSSFWorkbook wb = new HSSFWorkbook();
		HSSFSheet sheet = wb.createSheet("工作薄1");
		
		HSSFRow rown = sheet.createRow(0);
		HSSFCell cellTitle = rown.createCell(0);
		
		HSSFCellStyle columnTopStyle = this.getColumnTopStyle(wb);//获取列头样式对象
        // HSSFCellStyle style = this.getStyle(wb);                    //单元格样式对象
		cellTitle.setCellValue("商品表");
		
        HSSFRow rowRowName = sheet.createRow(1);                // 在索引2的位置创建行(最顶端的行开始的第二行)
        for(int n = 0; n < rowName.length; n++){
        	HSSFCell  cellRowName = rowRowName.createCell(n);                //创建列头对应个数的单元格
            cellRowName.setCellType(HSSFCell.CELL_TYPE_STRING);                //设置列头单元格的数据类型
            HSSFRichTextString text = new HSSFRichTextString(rowName[n]);
            cellRowName.setCellValue(text);                                    //设置列头单元格的值
            cellRowName.setCellStyle(columnTopStyle);                        //设置列头单元格样式
        }
        Product product = null;
        //将传入的集合设置到时对应的单元格中
        for(int i = 0;i < list.size(); i++){
        	product = list.get(i);
        	HSSFRow row = sheet.createRow(i+2);
        	row.createCell(0).setCellStyle(columnTopStyle);
        	row.createCell(0).setCellValue(product.getId());
        	row.createCell(1).setCellStyle(columnTopStyle);
        	row.createCell(1).setCellValue(product.getProName());
        	row.createCell(2).setCellStyle(columnTopStyle);
        	row.createCell(2).setCellValue(product.getProQty());
        	row.createCell(3).setCellStyle(columnTopStyle);
        	row.createCell(3).setCellValue(product.getProType());
        	row.createCell(4).setCellStyle(columnTopStyle);
        	row.createCell(4).setCellValue(product.getProState());
        	row.createCell(5).setCellStyle(columnTopStyle);
        	row.createCell(5).setCellValue(product.getPrice());
        	row.createCell(6).setCellStyle(columnTopStyle);
        	row.createCell(6).setCellValue(product.getCreDate());
        	row.createCell(7).setCellStyle(columnTopStyle);
        	row.createCell(7).setCellValue(product.getUpDate());
        	row.createCell(8).setCellStyle(columnTopStyle);
        	row.createCell(8).setCellValue(product.getDiscount());
        	row.createCell(9).setCellStyle(columnTopStyle);
        	row.createCell(9).setCellValue(product.getDescs());   	
       
        }
        if(wb !=null){
            try
	            {
	                String fileName = "Excel-" + String.valueOf(System.currentTimeMillis()).substring(4, 13) + ".xls";
	                String headStr = "attachment; filename=\"" + fileName + "\"";
	               
	                //浏览器下载
	                response.reset();
	                response.setContentType("APPLICATION/OCTET-STREAM");
	                response.setHeader("Content-Disposition", "attachment; filename=student.xls");
	                OutputStream out = response.getOutputStream();
	                wb.write(out);
	                response.flushBuffer();
	            }
	            catch (IOException e)
	            {
	                e.printStackTrace();
	            }
	        }
			
		}
	
	/**
	 * excel cell style
	 * */
	 public HSSFCellStyle getColumnTopStyle(HSSFWorkbook workbook) {
       HSSFFont font = workbook.createFont();
       font.setFontHeightInPoints((short)11);
       font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
       font.setFontName("Courier New");
       HSSFCellStyle style = workbook.createCellStyle();
       style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
       style.setBottomBorderColor(HSSFColor.BLACK.index);
       style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
       style.setLeftBorderColor(HSSFColor.BLACK.index);
       style.setBorderRight(HSSFCellStyle.BORDER_THIN);
       style.setRightBorderColor(HSSFColor.BLACK.index);
       style.setBorderTop(HSSFCellStyle.BORDER_THIN);
       style.setTopBorderColor(HSSFColor.BLACK.index);
       style.setFont(font);
       style.setWrapText(false);
       style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
       style.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
       
       return style;
       
   }
	
	
	private String getValue(Cell cell) {

		String cellValue = "";

		if (cell == null) {
			return cellValue;
		}

		switch (cell.getCellType()) {
		case XSSFCell.CELL_TYPE_BOOLEAN: // Boolean
			cellValue = cell.getBooleanCellValue() + "";
			break;

		case XSSFCell.CELL_TYPE_STRING: // 字符串
			cellValue = cell.getStringCellValue();
			break;

		case XSSFCell.CELL_TYPE_NUMERIC:// 数字
			cellValue = cell.getNumericCellValue() + "";
			break;

		case HSSFCell.CELL_TYPE_FORMULA: // 公式
			cellValue = cell.getCellFormula() + "";
			break;

		case HSSFCell.CELL_TYPE_BLANK: // 空值
			cellValue = "";
			break;

		case HSSFCell.CELL_TYPE_ERROR: // 故障
			cellValue = "非法字符";
			break;

		default:
			cellValue = "未知类型";
			break;
		}
		return cellValue;
	}
	
	
}
