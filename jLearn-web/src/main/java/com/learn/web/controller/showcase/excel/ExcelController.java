package com.learn.web.controller.showcase.excel;

import java.io.IOException;
import java.io.InputStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.learn.common.base.constants.Constants;
import com.learn.common.sys.user.entity.User;
import com.learn.modules.showcase.excel.entity.ExcelData;
import com.learn.modules.showcase.excel.entity.ExcelDataType;
import com.learn.modules.showcase.excel.service.ExcelDataService;
import com.learn.web.extra.bind.annotation.CurrentUser;
import com.learn.web.support.BaseCRUDController;

@Controller
@RequestMapping(value = "/showcase/excel")
public class ExcelController extends BaseCRUDController<ExcelData, Long> {
	
	@Autowired
	private ExcelDataService excelDataService;
	//@Autowired
	//private ExcelService excelService;
	
	public ExcelController(){
		setResourceIdentity("showcase:excel");
	}
	
	/**
	 * excel导入页面
	 * @author JeeLearner
	 * @date 2018年4月7日
	 * @param type
	 * @param model
	 * @return
	 */
	@RequestMapping(value = "/import", method = RequestMethod.GET)
    public String showImportExcelForm(@RequestParam("type") ExcelDataType type, Model model) {
        model.addAttribute("type", type);
        return viewName("importForm");
    }
	
	/**
	 * 导入操作
	 * @author JeeLearner
	 * @date 2018年4月7日
	 * @param user
	 * @param type
	 * @param file
	 * @param model
	 * @param redirectAttributes
	 * @return
	 */
	@RequestMapping(value = "/import", method = RequestMethod.POST)
	public String importExcel(
			@CurrentUser User user,
			@RequestParam("type") ExcelDataType type,
            @RequestParam("file") MultipartFile file,
            Model model,
            RedirectAttributes redirectAttributes){
		if(!canImport(file, model)){
			return showImportExcelForm(type, model);
		}
		try {
			InputStream in = file.getInputStream();
			switch (type) {
			case csv:
				
				break;
			case excel2003:
                
                break;
            case excel2007:
            	excelDataService.importExcel2007(user, in);
                break;
			default:
				//none
			}
		} catch (IOException e) {
			e.printStackTrace();
			redirectAttributes.addFlashAttribute(Constants.ERROR, "导入失败");
	        return redirectToUrl(null);
		}
		
		redirectAttributes.addFlashAttribute(Constants.MESSAGE, "导入任务已提交，任务执行完成后会在页面右上角的“我的通知”中通知你");
        return redirectToUrl(null);
	}
	
	/*
	 * 基础判断文件能够导入
	 */
	private boolean canImport(final MultipartFile file, final Model model){
		if(file == null || file.isEmpty()){
			model.addAttribute(Constants.ERROR, "请选择要导入的文件");
            return false;
		}
		String filename = file.getOriginalFilename();
		if(!(filename.endsWith("csv") || filename.endsWith("xls") || filename.endsWith("xlsx"))) {
            model.addAttribute(Constants.ERROR, "导入的文件格式错误，允许的格式：csv、xls、xlsx");
            return false;
        }
		return true;
	}
}
