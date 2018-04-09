package com.learn.modules.showcase.excel.service;

import java.io.InputStream;
import java.util.List;

import com.learn.common.sys.user.entity.User;
import com.learn.modules.showcase.excel.entity.ExcelData;

public interface ExcelDataService {

	/**
	 * 导入Excel2007版
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param user
	 * @param in
	 */
	public void importExcel2007(final User user, final InputStream in);
	/**
	 * 如果主键冲突 覆盖，否则新增
	 * @author JeeLearner
	 * @date 2018年4月8日
	 * @param dataList
	 */
	public void doBatchSave(final List<ExcelData> dataList);
}
