package com.learn.modules.showcase.excel.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.google.common.collect.Lists;
import com.learn.modules.showcase.excel.entity.ExcelData;

class Excel2007ImportSheetHandler extends DefaultHandler {

	private final Logger log = LoggerFactory.getLogger(this.getClass());

	private int batchSize; // 批处理大小
	private int totalSize; // 总行数

	private int rowNumber = 1;
	private String lastContents;
	private List<ExcelData> dataList;
	private ExcelDataService excelDataService;

	private List<String> currentCellData = Lists.newArrayList();

	public Excel2007ImportSheetHandler(final int batchSize, final List<ExcelData> dataList,
			final ExcelDataService excelDataService) {
		this.batchSize = batchSize;
		this.dataList = dataList;
		this.excelDataService = excelDataService;
	}

	public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
		if ("row".equals(qName)) {// 如果是行开始 清空cell数据 重来
			rowNumber = Integer.valueOf(attributes.getValue("r"));// 当前行号
			if (rowNumber == 1) {
				return;
			}
			currentCellData.clear();
		}
		lastContents = "";
	}

	public void endElement(String uri, String localName, String qName) throws SAXException {
		if ("row".equals(qName)) {//如果是行开始 清空cell数据 重来
            if (rowNumber == 1) {
                return;
            }
            ExcelData data = new ExcelData();
            data.setId(Double.valueOf(currentCellData.get(0)).longValue());
            data.setContent(currentCellData.get(1));
            dataList.add(data);

            totalSize++;

            if (totalSize % batchSize == 0) {
                try {
                    excelDataService.doBatchSave(dataList);
                } catch (Exception e) {
                    Long fromId = dataList.get(0).getId();
                    Long endId = dataList.get(dataList.size() - 1).getId();
                    log.error("from " + fromId + " to " + endId + ", error", e);
                }
                dataList.clear();
            }
        }

        if ("c".equals(qName)) {//按照列顺序添加数据
            currentCellData.add(lastContents);
        }
	}
	
	public void characters(char[] ch, int start, int length) throws SAXException {
        lastContents += new String(ch, start, length);
    }

}
