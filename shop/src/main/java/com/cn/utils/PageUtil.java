package com.cn.utils;

import java.util.HashMap;
import java.util.Map;

import org.apache.commons.lang.StringUtils;





/********声明*************
 *
 * 版权所有：港澳资讯
 *
 * 项目名称：finance-util
 * 类    名称：PageUtil
 * 功能描述：
 *
 * 创建人员：gaoyunz
 * 创建时间：2014-3-7
 * @version
 ********修改记录************
 * 修改人员：
 * 修改时间：
 * 修改描述：
 */
public class PageUtil {
	public static final String page = "page";
	public static final String limit = "limit";
	public static final String totalCount = "totalCount";
	public static final String order = "order";
	public static final String orderBy = "orderBy";
	public static final String start = "start";
	public static final String end = "end";
	public static Page<?> getPageFromMap(Page<?> page,Map<String,Object> map){
		if(map.get(order) != null){
			page.setOrder((String)map.get(order));
		}
		if(map.get(orderBy) != null){
			page.setOrderBy((String)map.get(orderBy));
		}
		if(map.get(PageUtil.page) != null){
			page.setPage((Integer)map.get(PageUtil.page));
		}
		if(map.get(limit) != null){
			page.setLimit((Integer)map.get(limit));
		}	
		return page;
	}
	
	public static void setStartEnd(Map<String,Object> map){
		Integer cuPageNo = 1;
		if(map.get(page) != null){
			cuPageNo = Integer.parseInt((String)map.get(page));
		}
		
		Integer cuPageSize = 10;
		if(map.get(limit) != null){
			cuPageSize = Integer.parseInt((String)map.get(limit));
		}
		
		map.put(page, cuPageNo);
		map.put(limit, cuPageSize);
		map.put(start, (cuPageNo-1)*cuPageSize);
		map.put(end, cuPageNo*cuPageSize);
	}
	
	public static Map<String,Object> getPageMap(Integer pageNo,Integer pageSize,String order,String orderBy){
		Map<String,Object> map = new HashMap<String,Object>();
		if(pageNo != null){
			map.put(PageUtil.page, pageNo);
		}else{
			map.put(PageUtil.page, 1);
		}
		if(pageSize != null){
			map.put(PageUtil.limit, pageSize);
		}else{
			map.put(PageUtil.limit, 5);
		}
		if(StringUtils.isNotEmpty(order)){
			map.put(PageUtil.order, order);
		}
		if(StringUtils.isNotEmpty(orderBy)){
			map.put(PageUtil.orderBy, orderBy);
		}
		return map;
	}

}
