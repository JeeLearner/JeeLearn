package com.learn.web.controller.test;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learn.common.base.bind.annotation.CurrentUser;
import com.learn.common.base.constants.Constants;
import com.learn.common.jdbc.jpa.entity.search.Searchable;
import com.learn.common.sys.user.entity.User;
import com.learn.web.support.BaseController;
import com.learn.web.support.permission.PermissionList;

/**
 * 设备部署管理控制器
 * <p>
 * Title: EquipmentMngController.java
 * </p>
 * <p>
 * Description:
 * </p>
 * <p>
 * Copyright: Copyright (c) 2015
 * </p>
 * <p>
 * Company: Petro-CyberWorks Information Technology Co.,Ltd.
 * </p>
 * 
 * @author lyd
 * @date 2017年11月24日
 * @version 1.0
 */
@Controller
@RequestMapping(value = "/showcase/excel")
public class EquipMngController extends BaseController{

	private static final Logger logger = LoggerFactory.getLogger(EquipMngController.class);

	protected PermissionList permissionList = null;

	/**
	 * 权限前缀
	 */
	public void setResourceIdentity(String resourceIdentity) {
		if (!StringUtils.isEmpty(resourceIdentity)) {
			permissionList = PermissionList.newPermissionList(resourceIdentity);
		}
	}

	public EquipMngController() {
		setResourceIdentity("showcase:excel");
	}

	List<ExcelBean> excelBeans = new ArrayList<>();
	MultipartFile multipartFile = null;

	@GetMapping
	public String index(){
		return "showcase/excel/plexcel";
	}
	
	/**
	 * 解析excel
	 * <p>
	 * Title: readExcel
	 * </p>
	 * <p>
	 * Description:
	 * </p>
	 * 
	 * @author lyd
	 * @date 2018年1月8日
	 * @param uploadExcel
	 * @param request
	 * @param response
	 * @param searchable
	 * @param model
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "readExcel")
	@ResponseBody
	public String readExcel(@RequestParam("uploadExcel") MultipartFile uploadExcel, HttpServletRequest request,
			HttpServletResponse response, Model model) throws Exception {
		if (permissionList != null) {
			this.permissionList.assertHasCreatePermission();
		}
		String fileName = uploadExcel.getOriginalFilename();
		InputStream inputStream = uploadExcel.getInputStream();
		Workbook workbook = null;
		// 判断excel版本
		String fileType = fileName.substring(fileName.lastIndexOf(".") + 1, fileName.length());
		if ("xls".equals(fileType)) {
			workbook = new HSSFWorkbook(inputStream);
		} else if ("xlsx".equals(fileType)) {
			workbook = new XSSFWorkbook(inputStream);
		}
		// 创建sheet对象
		List<String[]> list = new ArrayList<String[]>();
		XSSFSheet sheet = (XSSFSheet) workbook.getSheetAt(0);
		int firstRowIndex = sheet.getFirstRowNum();
		// int lastRowIndex = sheet.getLastRowNum();
		int lastRowIndex = sheet.getPhysicalNumberOfRows();
		int coloumNum = sheet.getRow(0).getPhysicalNumberOfCells();// 获得总列数
		String msg = null;
		for (int rIndex = firstRowIndex; rIndex <= lastRowIndex; rIndex++) {
			Row row = sheet.getRow(rIndex);
			if (row != null && row.getFirstCellNum() == 0) {
				int firstCellIndex = row.getFirstCellNum();
				int lastCellIndex = row.getLastCellNum();
				String[] s = new String[coloumNum];
				for (int cIndex = firstCellIndex; cIndex < lastCellIndex; cIndex++) {
					Cell cell = row.getCell(cIndex);
					String value = "";
					int type = cell.getCellType();
					if (cell != null) {
						String pattern = "[a-zA-Z0-9]*";
						value = cell.toString();
						// 验证设备名称
						if (rIndex > 0 && cIndex == 0 && value == "") {
							msg = "设备名称不能为空，请检查Excel文件！";
							return msg;
						}
						// 验证设备型号
						if (rIndex > 0 && cIndex == 1 && value == "") {
							msg = "设备型号不能为空，请检查Excel文件！";
							return msg;
						}
						// 验证用户名
						if (rIndex > 0 && cIndex == 2 && value == "") {
							msg = "用户名不能为空，请检查Excel文件！";
							return msg;
						}
						// 验证密码
						if (rIndex > 0 && cIndex == 3 && value == "") {
							msg = "密码不能为空，请检查Excel文件！";
							return msg;
						}
						// 验证管理地址
						if (rIndex > 0 && cIndex == 4 && value == "") {
							msg = "管理地址不能为空，请检查Excel文件！";
							return msg;
						}
						// 验证互联地址
						if (rIndex > 0 && cIndex == 5 && value == "") {
							msg = "互联地址不能为空，请检查Excel文件！";
							return msg;
						}
						// 验证区域号
						if (rIndex > 0 && cIndex == 6 && value == "") {
							msg = "区域号不能为空，请检查Excel文件！";
							return msg;
						}
						// 验证核心标志位
						if (rIndex > 0 && cIndex == 7 && value == "") {
							msg = "核心标志位不能为空，请检查Excel文件！";
							return msg;
						}
						// 验证下连内网地址
						if (rIndex > 0 && cIndex == 8 && value == "") {
							msg = "下连内网地址不能为空，请检查Excel文件！";
							return msg;
						}

						if (Cell.CELL_TYPE_NUMERIC == type) {
							Double val = cell.getNumericCellValue();
							if (val == val.longValue()) {
								value = "" + val.longValue();
							}
						}
						s[cIndex] = value;
					}
				}
				list.add(s);
			}
		}
		// 将数据封装到EXCEL实体中
		for (int i = 1; i < list.size(); i++) {
			ExcelBean excelBean = new ExcelBean();
			String[] strings = list.get(i);
			excelBean.setDeviceName(strings[0]);
			excelBean.setModelCode(strings[1]);
			excelBean.setUsername(strings[2]);
			excelBean.setPassword(strings[3]);
			excelBean.setMngIP(strings[4]);
			excelBean.setInterIP(strings[5]);
			excelBean.setAreaID(strings[6]);
			excelBean.setCoreFlag(strings[7]);
			excelBean.setPrivateIP(strings[8]);
			excelBeans.add(excelBean);
		}
		model.addAttribute("excelBeans", excelBeans);
		if (msg == null) {
			msg = "解析Excel文件成功，请确认导入！";
			multipartFile = uploadExcel;
		}
		return msg;
	}
	
	/**
	 * 保存解析后的信息
	 * <p>Title: patchExcel</p>
	 * <p>Description: </p>
	 * @author lyd
	 * @date 2018年1月8日
	 * @param user
	 * @param model
	 * @param backURL
	 * @param redirectAttributes
	 * @return
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/patchExcel")
	public String patchExcel(@CurrentUser User user, Model model,
			@RequestParam(value = Constants.BACK_URL, required = false) String backURL,
			RedirectAttributes redirectAttributes, String fileName, HttpServletRequest request) throws UnsupportedEncodingException {
		if (permissionList != null) {
			this.permissionList.assertHasCreatePermission();
		}
		if(fileName == null){
			fileName = multipartFile.getOriginalFilename();
		}
		fileName = new String(fileName.getBytes("ISO-8859-1"), "utf8");
		
		//如果文件不为空，写入上传路径
		if(multipartFile != null) {
			//上传文件路径
			String path = getTomcatPath(request);
			
			//String path = getTomcatWebappsPath(request);
			//上传文件名
			File filepath = new File(path,fileName);
			File parentFile = filepath.getParentFile();
			System.out.println(parentFile);
			//判断路径是否存在，如果不存在就创建一个
			if (!filepath.getParentFile().exists()) { 
				filepath.getParentFile().mkdirs();
			}
			//将上传文件保存到一个目标文件当中
			try {
				multipartFile.transferTo(new File(path + File.separator + fileName));
			} catch (IllegalStateException | IOException e) {
				redirectAttributes.addFlashAttribute(Constants.ERROR, "数据导入成功,上传服务器失败！");
				return redirectToUrl(null);
			}
		} else {
		}
		
		redirectAttributes.addFlashAttribute(Constants.MESSAGE, "测试导入成功");
    	return redirectToUrl(null);
	}
	
	public static String getTomcatPath(HttpServletRequest request){
		String path = null;
		if(request != null){
			path = request.getServletContext().getRealPath(""); //  webapps\jLearn-ui-bootstrap\
			String contextPath = request.getContextPath();//  /esp-web
			contextPath = contextPath.substring(1, contextPath.length()); // esp-web
			//String contextPath1 = "esp-web";
			
			//int endIndex = path.indexOf(contextPath);
			//path = path.substring(0, endIndex); // webapps\
			int ind = path.lastIndexOf(File.separator);
			path = path.substring(0, ind);
			path = path + File.separator + "ROOT"+ File.separator +"file_deploy" + File.separator + "deploy_upload";
		} 
		return path;
	}
}
