package com.learn.modules.showcase.excel.service;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.apache.commons.io.IOUtils;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xssf.eventusermodel.XSSFReader;
import org.hibernate.context.spi.CurrentTenantIdentifierResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.xml.sax.ContentHandler;
import org.xml.sax.InputSource;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.XMLReaderFactory;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.learn.common.jdbc.jpa.service.BaseService;
import com.learn.common.service.maintain.notification.NotificationApi;
import com.learn.common.sys.user.entity.User;
import com.learn.modules.showcase.excel.dao.ExcelDataDao;
import com.learn.modules.showcase.excel.entity.ExcelData;

@Service
public class ExcelDataServiceImpl extends BaseService<ExcelData, Long> implements ExcelDataService {

	private final Logger log = LoggerFactory.getLogger(ExcelDataService.class);

    private int batchSize = 1000; //批处理大小
    private int pageSize = 1000;//查询时每页大小
    
    @Autowired
    private ExcelDataDao excelDataDao;
    @Autowired
    private NotificationApi notificationApi;
    
    @Async
	@Override
	public void importExcel2007(User user, InputStream in) {
		ExcelDataService proxy = ((ExcelDataService)AopContext.currentProxy());
		
		BufferedInputStream bis = null;
		try {
			long beginTime = System.currentTimeMillis();
			List<ExcelData> dataList = Lists.newArrayList();
			
			bis = new BufferedInputStream(in);
			OPCPackage pkg = OPCPackage.open(bis);
			XSSFReader r = new XSSFReader(pkg);
			
			XMLReader parser = XMLReaderFactory.createXMLReader();
			
			ContentHandler handler = new Excel2007ImportSheetHandler(batchSize, dataList, proxy);
			parser.setContentHandler(handler);
			
			Iterator<InputStream> sheets = r.getSheetsData();
            while (sheets.hasNext()) {
                InputStream sheet = null;
                try {
                    sheet = sheets.next();
                    InputSource sheetSource = new InputSource(sheet);
                    parser.parse(sheetSource);
                } catch (Exception e) {
                    throw e;
                } finally {
                    IOUtils.closeQuietly(sheet);
                }
            }
			
            //把最后剩下的不足batchSize大小
            if (dataList.size() > 0) {
                proxy.doBatchSave(dataList);
            }
            
            long endTime = System.currentTimeMillis();
            Map<String, Object> context = Maps.newHashMap();
            context.put("seconds", (endTime - beginTime) / 1000);
            //保存并推送新通知
			notificationApi.notify(user.getId(), "excelImportSuccess", context);
		} catch (Exception e) {
			log.error("excel import error", e);
            Map<String, Object> context = Maps.newHashMap();
            context.put("error", e.getMessage());
            //保存并推送新通知
			notificationApi.notify(user.getId(), "excelImportError", context);
		} finally {
            IOUtils.closeQuietly(bis);
        }
	}

	@Override
	public void doBatchSave(final List<ExcelData> dataList) {
		for(ExcelData data : dataList) {
            ExcelData dbData = findOne(data.getId());
            if(dbData == null) {
                excelDataDao.save(data.getId(), data.getContent());
            } else {
                dbData.setContent(data.getContent());
                update(dbData);
            }
        }
	}

	
}
