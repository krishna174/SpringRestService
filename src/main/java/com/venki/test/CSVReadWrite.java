package com.venki.test;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

import org.supercsv.io.CsvMapReader;
import org.supercsv.io.CsvMapWriter;
import org.supercsv.io.ICsvMapReader;
import org.supercsv.io.ICsvMapWriter;
import org.supercsv.prefs.CsvPreference;

public class CSVReadWrite {
	
	public static final String PROPERTIES_FILE = "D:\\DataExport\\Properties\\DataExport.properties";

	/**
	 * @param args
	 */
	public static void main(String[] args) {

		importData();
	}

	public static void importData() {
		
		String  fileName = "D:\\EclipseMarsVenkiDev\\DevData\\tst.csv";
		String  fileNameW = "D:\\EclipseMarsVenkiDev\\DevData\\tst.csv";
		
		
		//final String[] header1 = new String[] { "BRANCH", "CUSTNO", "ACCOUNTNUMBER1", "ISSUEDATE", "EXPIRYTDA", "CARDNO", "NAME", "MOBILENO", "STATUS" };
		
		ICsvMapReader mapReader = null;
		ICsvMapWriter mapWriter = null;
		try {

			Map<String, String> headerMap;


				mapReader = new CsvMapReader(new FileReader(fileName), CsvPreference.STANDARD_PREFERENCE);
			//	mapWriter = new CsvMapWriter(new FileWriter(fileNameW) ,CsvPreference.STANDARD_PREFERENCE);

				final String[] header = mapReader.getHeader(true);
				
						
			
				//mapWriter.writeHeader(header1);
			//	mapWriter1.writeHeader(header1);
				
				while ((headerMap = mapReader.read(header)) != null) {
					
					String testID = headerMap.get("TEST_ID");
					if(testID!=null &&  !"".equals(testID) ){
						testID = testID.trim();
						
						  Map<String,String> map  = new HashMap<String, String>();
						  
						    map.put("RESULT_2", "RESULT2");
							
						  mapWriter.write(map, "RESULT_2");
					}else{
						
						Map<String,String> map  = new HashMap<String, String>();
						  
					    map.put("RESULT_1", "RESULT1");
						
					  mapWriter.write(map, "RESULT_1");
					}
					  
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		finally{
			if(mapWriter!=null){
			 try {
				mapWriter.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			}
			
			System.out.println("COMPOLETED");
			
		}
	}
	
	
}
