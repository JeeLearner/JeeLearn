package com.learn.modules.test;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;
import java.util.List;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.io.FileUtils;
import org.w3c.dom.Document;


public class PoiExcelToHtml {
   /* final static String path = "D:\\";
     final static String file = "b.xlsx";
 public static void main(String args[]) throws Exception {

     InputStream input=new FileInputStream(path+file);
	 String loadurl = "http://10.178.2.81:8080/img/imageesp";
		URL url = new URL(loadurl + "/APmodel.xlsx");
		InputStream input = null;
		try {
			URLConnection conn = url.openConnection();
			input = conn.getInputStream();
		} catch (Exception ex){
			
		}
     
     HSSFWorkbook excelBook= null;
     if("1".equals("1")){
    	 XSSFWorkbook excelBook1 = new XSSFWorkbook(input);
    	 //excelBook = (HSSFWorkbook)excelBook1;
     }
    		 new HSSFWorkbook(input);
     ExcelToHtmlConverter excelToHtmlConverter = new ExcelToHtmlConverter (DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument() );
     excelToHtmlConverter.processWorkbook(excelBook);
     List pics = excelBook.getAllPictures();
     if (pics != null) {
         for (int i = 0; i < pics.size(); i++) {
             Picture pic = (Picture) pics.get (i);
             try {
                 pic.writeImageContent (new FileOutputStream (path + pic.suggestFullFileName() ) );
             } catch (FileNotFoundException e) {
                 e.printStackTrace();
             }
         }
     }
     Document htmlDocument =excelToHtmlConverter.getDocument();
     ByteArrayOutputStream outStream = new ByteArrayOutputStream();
     DOMSource domSource = new DOMSource (htmlDocument);
     StreamResult streamResult = new StreamResult (outStream);
     TransformerFactory tf = TransformerFactory.newInstance();
     Transformer serializer = tf.newTransformer();
     serializer.setOutputProperty (OutputKeys.ENCODING, "utf-8");
     serializer.setOutputProperty (OutputKeys.INDENT, "yes");
     serializer.setOutputProperty (OutputKeys.METHOD, "html");
     serializer.transform (domSource, streamResult);
     outStream.close();

     String content = new String (outStream.toByteArray() );

     FileUtils.writeStringToFile(new File (path, "exportExcel.html"), content, "utf-8");
 }*/
}