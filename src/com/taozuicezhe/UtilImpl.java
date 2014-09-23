package com.taozuicezhe;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class UtilImpl implements UtilInterface{
	Logger log = Logger.getLogger(UtilImpl.class);
	Connection conn = null;
	String isTruncate = null;
	String tableName = null;
	private final String artificialFolderPath = System.getProperty("user.dir") + "/Man-MadeValues/";
	
	@Override
	public List<String> getSourceValueFileNameList() {
		long startTime = System.nanoTime();
		List<String> artificialDataFileNameList = new ArrayList<String>();
		File file = new File(artificialFolderPath);
		if(file.isDirectory()){
			String[] nameList = file.list();
			if(nameList != null){
				for(String name:nameList){
					artificialDataFileNameList.add(name);
				}
			}
		}
		long endTime = System.nanoTime() - startTime;
		log.info("Got "+artificialDataFileNameList.size()+" MAN-MADE data files in Man-MadeValues path!Time costing: "+endTime + " ns");
		return artificialDataFileNameList;
	}

	@Override
	public List<List<String>> getArtificialData(String fileName) {
		List<List<String>> ArtificialData = new ArrayList<List<String>>();
		String line = "";
		log.info("Start to get man-made datas in "+fileName+"... ...");
//Get Column Name list
		try {
			FileReader fr = new FileReader(new File(artificialFolderPath+"/"+fileName));
			BufferedReader br = new BufferedReader(fr);
//非#或*后的数据全部为人造数据部分，以~分隔				
			while ((line = br.readLine()) != null) {
				if(line.isEmpty()){
					continue;
				}
				if (line.startsWith("#")){
					continue;
				}else if(line.startsWith("*")){
					continue;
				}else if(line.toLowerCase().startsWith("rem")){
					continue;
				}else{
					String[] ele = line.split("~");
					List<String> lineData = new ArrayList<String>();
					for(String elements:ele){
						lineData.add(elements);
					}
					ArtificialData.add(lineData);
				}
			}
			if(br != null){
				br.close();
			}
		} catch (IOException e1) {
			log.error(e1.getLocalizedMessage());
		}
		return ArtificialData;
	}

	@Override
	public void batchInsertArtificialData(String tableName,
		List<String> columnNames, List<List<String>> datas, String DB_NUM) throws CustomerizedException{		
		String sqlInsert = "";
		String columnNamesStr = "";
		String values = "";
//build insert SQL
		int count = 0;
		for(String columnName:columnNames){
			count++;
			if(count!=columnNames.size()){
				columnNamesStr+=columnName+",";
				values+="?"+",";
			}else{
				columnNamesStr+=columnName;
				values+="?";
			}			
		}
		sqlInsert = "INSERT INTO "+tableName+" ("
				+columnNamesStr+")"+" VALUES("+values+")";
		if(this.conn != null){
		long startTime = System.currentTimeMillis();
		log.info("Start to INSERT man-made datas into ["+tableName+"]");		
//设置回滚机制				
		PreparedStatement prest;
		try {
			conn.setAutoCommit(false); 
			prest = conn.prepareStatement(sqlInsert,ResultSet.TYPE_SCROLL_SENSITIVE,ResultSet.CONCUR_READ_ONLY);
			for(List<String> lines:datas){
				for(int i=0;i<lines.size();i++){
					prest.setString(i+1,lines.get(i));
				}
				prest.addBatch();
			}
			prest.executeBatch();
			prest.clearBatch();
			conn.commit();
			prest.close();
		} catch (SQLException e) {
			log.error("Batch insert Man-Made data meet trouble,err: "+e.getLocalizedMessage());
		}
		long endTime =System.currentTimeMillis() - startTime;
		log.info("Finishing to INSERT man-made datas into ["+tableName+"],time costing: "+endTime+" ms");
	}
	}

	@Override
	public String getIsTruncateStatus(String DB_NUM) throws CustomerizedException {
		DBConnection connection = new DBConnection(DB_NUM);
		this.isTruncate = connection.getIsTruncate();
		return isTruncate;
	}

	@Override
	public String getTableName(String fileName) {
		String line = "";
		log.info("Start to read table name in "+fileName+"... ...");
//GET Table Name
		try {
			FileReader fr = new FileReader(new File(artificialFolderPath+"/"+fileName));
			BufferedReader br = new BufferedReader(fr);
//#后为TableName				
			while ((line = br.readLine()) != null) {
				if(line.startsWith("rem")){
					continue;
				}
				else if (line.startsWith("#")){
					tableName = line.substring(1);
				}
			}
			if(br != null){
				br.close();
			}
		} catch (IOException e1) {
			log.error(e1.getLocalizedMessage());
		}
		return tableName;
	}

	@Override
	public List<String> getColumns(String fileName) throws CustomerizedException {
		List<String> columnNames = new ArrayList<String>();
		String line = "";
		String columnLines = "";
		log.info("Start to read column names in "+fileName+"... ...");
//Get Column Name list
		try {
			FileReader fr = new FileReader(new File(artificialFolderPath+"/"+fileName));
			BufferedReader br = new BufferedReader(fr);
//*后为Columns，以~分隔				
			while ((line = br.readLine()) != null) {
				if(line.startsWith("rem")){
					continue;
				}
				else if (line.startsWith("*")){
					columnLines = line.substring(1);
				}
			}
			if(br != null){
				br.close();
			}
			if(!columnLines.isEmpty()){
				String[] ele = columnLines.split("~");
				for(String elements:ele){
					columnNames.add(elements);
				}
			}else{
				throw new CustomerizedException("You forgot to set columns start with * in file: "+fileName);
			}
		} catch (IOException e1) {
			log.error(e1.getLocalizedMessage());
		}
		return columnNames;
	}

	@Override
	public void truncateTable(String DB_NUM,String tableName) throws CustomerizedException {
		if(conn == null){
			String truncateSQL = "TRUNCATE TABLE "+tableName;
			DBConnection connection = new DBConnection(DB_NUM);
			conn = connection.connectToDB();
			try {
				PreparedStatement prpstm = conn.prepareStatement(truncateSQL);
				prpstm.close();
				connection.closeConnection();
				this.conn = null;
			} catch (SQLException e) {
				log.error("TRUNCATE TABLE = "+tableName+" meet trouble,err: "+e.getLocalizedMessage());
			}
			
		}		
	}

	@Override
	public void connectToDB(String DB_NUM) throws CustomerizedException {
		if(this.conn == null){
			DBConnection connection = new DBConnection(DB_NUM);
			this.conn = connection.connectToDB();
			if(this.conn != null){
				log.info("Connect to DB<=>"+DB_NUM+" successfully!");
			}
		}
		
	}

	@Override
	public void disconnectFromDB(String DB_NUM) throws CustomerizedException {
		if(this.conn != null){
			DBConnection connection = new DBConnection(DB_NUM);
			connection.closeConnection();
			this.conn = null;
			if(this.conn ==  null){
				log.info("Disconnect from DB<=>"+DB_NUM+" successfully!");
			}
		}
	}
}
