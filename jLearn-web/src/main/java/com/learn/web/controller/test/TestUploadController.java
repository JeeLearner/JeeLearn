package com.learn.web.controller.test;

import java.io.BufferedOutputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.net.URL;
import java.net.URLConnection;

import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.learn.web.support.BaseController;
import com.learn.web.support.permission.PermissionList;

@Controller
@RequestMapping(value = "/showcase/sample")
public class TestUploadController extends BaseController {

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

	public TestUploadController() {
		setResourceIdentity("showcase:sample");
	}
	
	@GetMapping
	public String index(){
		return "showcase/simple/plexcel";
	}

	@RequestMapping("/patchExcel")
	public String test(HttpServletResponse response, @RequestParam("uploadExcel") MultipartFile uploadExcel) {
		int byteread = 0;
		URL url = null;
		try {
			url = new URL("http://116.196.73.58:8080/file_deploy/deploy_upload/a.xlsx");
			URLConnection conn = url.openConnection();
			conn.setDoOutput(true);
			OutputStream outputStream = conn.getOutputStream();
			
			InputStream inputStream = uploadExcel.getInputStream();
			
			byte[] buffer = new byte[1204];
			while ((byteread = inputStream.read(buffer)) != -1) {
				outputStream.write(buffer, 0, byteread);
			}
			
			outputStream.flush();
			outputStream.close();
			
			/*URLConnection conn = url.openConnection();
			InputStream inStream = conn.getInputStream();
			response.reset();
			response.addHeader("Content-Disposition",
					"attachment;filename=" + new String("测试.xlsx".getBytes("gb2312"), "ISO8859-1"));
			response.addHeader("Content-Length", "" + conn.getContentLength());
			OutputStream toClient = new BufferedOutputStream(response.getOutputStream());
			response.setContentType("application/octet-stream");
			// toClient.write(buffer);

			byte[] buffer = new byte[1204];
			while ((byteread = inStream.read(buffer)) != -1) {
				toClient.write(buffer, 0, byteread);
			}
			toClient.flush();
			toClient.close();*/
		} catch (FileNotFoundException e) {
			logger.error("Excel下载失败:" + e.getMessage());
			e.printStackTrace();
		} catch (IOException e) {
			logger.error("Excel下载失败:" + e.getMessage());
			e.printStackTrace();
		}

		return null;
	}
}
