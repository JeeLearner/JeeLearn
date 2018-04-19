package com.learn.common.base.utils;

import java.io.UnsupportedEncodingException;

import org.springframework.util.StringUtils;

/**
 * 转换get请求时候乱码问题
 * @author JeeLearner
 * @date 2018年4月10日
 * @version 1.0
 * @CSDN http://blog.csdn.net/it_lyd
 */
public class TypeToUtil {

	/**
	 * 将get请求的编码由ISO8859-1转换为UTF-8
	 * @author JeeLearner
	 * @date 2018年4月10日
	 * @param baseName
	 * @return
	 */
	public static String byteToUTF(String baseName){
		if(!StringUtils.isEmpty(baseName)){
			try {
				byte[] bytes = baseName.getBytes("ISO8859-1");
				baseName = new String(bytes,"utf-8");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}
		}
		return baseName;
	}
}
