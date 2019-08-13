package com.cn.utils;

import org.apache.commons.lang.RandomStringUtils;

/********声明*************
 *
 * 类    名称：Constant
 * 功能描述：
 *
 * 创建人员：zhougaoyun
 * 创建时间：2016-8-2
 * 版      本：
 ********修改记录************
 * 修改人员：
 * 修改时间：
 * 修改描述：
 */
public class Constants {
	public static final int KEY_LENGTH = 18;
	public static final int FLAG_0=0;//未提交
	public static final int FLAG_1=1;//正常
	public static final int FLAG_10=10;//删除
	
	public static final String PAGE_SIZE="30";
	
	public static final String PAGE_START="start";
	public static final String PAGE_END="end";
	
	public static  String getId(){
		return RandomStringUtils.randomAlphanumeric(KEY_LENGTH);
	}
}
