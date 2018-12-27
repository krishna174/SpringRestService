package com.venki.test;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Iterator;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.junit.BeforeClass;
import org.junit.Test;
import org.springframework.web.client.RestTemplate;

import com.venki.pojo.RequestPOJO;

public class ServiceJUnitTest {

	private static final String otpUri = "http://localhost:8080/SpringRestService/rest/MHL/pay?key=vk";
	private static final String userUri = "http://localhost:8080/SpringRestService/MHL/validateUser";
	private static final String keyUri = "http://localhost:8080/SpringRestService/MHL/validateKey";
	
	@Test
	public void userValidate() {
		  RequestPOJO msg = new RequestPOJO();
			 
		    msg.setUserId("VENKAT");
		   
		    RestTemplate restTemplate = new RestTemplate();
		     msg = restTemplate.postForObject( userUri, msg, RequestPOJO.class);
		     
		    writeToXLS(msg,"USER_VALIDATE");
		    
	}

	@Test
	public void keyValidate() {
		  RequestPOJO msg = new RequestPOJO();
			 
		    msg.setKeyId("123");
		   
		    RestTemplate restTemplate = new RestTemplate();
		     msg = restTemplate.postForObject( keyUri, msg, RequestPOJO.class);
		     
		    writeToXLS(msg,"KEY_VALIDATE");
		    
	}
	
	
	public static void writeToXLS(RequestPOJO res,String testID) {
		
		int rowSize=0;
		
		//Excel code
		
		FileInputStream fis=null;
		Workbook workbook=null;
		
		FileOutputStream fos =null;
		
		try {
			fis = new FileInputStream(new File("D:\\Test.xls"));
			 workbook = new HSSFWorkbook(fis);
             Sheet sheet = workbook.getSheetAt(0);
             Iterator rowIterator = sheet.iterator();
             int rowCount =sheet.getLastRowNum();
             
             
             while (rowIterator.hasNext()) {

                 Row row = (Row) rowIterator.next();
                 Iterator cellIterator = row.cellIterator();
                 
                 if(row.getRowNum()!=0){
                	 
                	/* if(row.getRowNum()==rowCount){
                		 sheet.createRow(rowCount+1);
                	 }*/
                	 
                 while (cellIterator.hasNext()) {

                     Cell cell = (Cell) cellIterator.next();
                         if (cell.getColumnIndex() == 0) {
                        	 cell.setCellType(Cell.CELL_TYPE_STRING);
                        	 
                        	 String val=cell.getStringCellValue();
                        	 
                        	 if(val!=null && val.equals(testID)){
                        		 
                        		 if((row.getCell(1)!=null && row.getCell(1).getStringCellValue()!=null && !"".equals(row.getCell(1).getStringCellValue())) &&
                        				 (row.getCell(1)!=null && row.getCell(1).getStringCellValue()!=null && !"".equals(row.getCell(1).getStringCellValue()))&&
                        						 (row.getCell(1)!=null && row.getCell(1).getStringCellValue()!=null && !"".equals(row.getCell(1).getStringCellValue()))){
                        		 
                        			
                        			 
                        			row.createCell(4);
                            		 row.getCell(4).setCellType(Cell.CELL_TYPE_STRING);
                            		 
                            		 if(testID.equalsIgnoreCase("KEY_VALIDATE"))
        							  row.getCell(4).setCellValue(res.getKeyId()); 
                            		 if(testID.equalsIgnoreCase("USER_VALIDATE"))
           							  row.getCell(4).setCellValue(res.getUserId()); 
                        			 
        							  
        							  
                        		 row.createCell(5);
                        		 row.getCell(5).setCellType(Cell.CELL_TYPE_STRING);
    							  row.getCell(5).setCellValue(res.getCode());  
    							  
    							  row.createCell(6);
                         		 row.getCell(6).setCellType(Cell.CELL_TYPE_STRING);
     							  row.getCell(6).setCellValue(res.getRemark()); 
     							  
                        		 }else{
                        			 
                        			 if(row.getCell(4)!=null ){
                        				 row.getCell(4).setCellValue("");
                        			 }
                        			 if(row.getCell(5)!=null ){
                        				 row.getCell(5).setCellValue("");
                        			 }
                        			 if(row.getCell(6)!=null ){
                        				 row.getCell(6).setCellValue("");
                        			 }
                        			 
                        			 row.createCell(1);
                            		 row.getCell(1).setCellType(Cell.CELL_TYPE_STRING);
                            		 
                            		 if(testID.equalsIgnoreCase("KEY_VALIDATE"))
           							  row.getCell(1).setCellValue(res.getKeyId()); 
                               		 if(testID.equalsIgnoreCase("USER_VALIDATE"))
              							  row.getCell(1).setCellValue(res.getUserId()); 
        							  
        							  
                        		 row.createCell(2);
                        		 row.getCell(2).setCellType(Cell.CELL_TYPE_STRING);
    							  row.getCell(2).setCellValue(res.getCode());  
    							  
    							  row.createCell(3);
                         		 row.getCell(3).setCellType(Cell.CELL_TYPE_STRING);
     							  row.getCell(3).setCellValue(res.getRemark()); 
                        			 
                        		 }
                         }
                         
                         }
                 }
             }
             }

         fis.close();
		
         	fos =new FileOutputStream(new File("D:\\Test.xls"));
	        workbook.write(fos);
	        fos.close();
		
		} catch (Exception e) {
			
			e.printStackTrace();
		}
		finally{
			try {
				if(fis!=null)
				   fis.close();
				if(fos!=null)
				   fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
		}
		
	}
}
