package com.taozuicezhe;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class BatchDataImportorX {

	/**
	 * @param args
	 */
	private static Logger log = Logger.getLogger(BatchDataImportorX.class);
	
	public static void main(String[] args) throws CustomerizedException {
		if(args.length == 0){
			log.error("Your parameters mustn't be empty,we need" +
					" one para to choose whitch DB you want to connect to (Total surpport 6 DBs)-Please using DB1,DB2... to change DB!");
		}else if(args.length>1){
			log.error("Only need one param here!You've used " +
					"more than one param, but using "+args.length+" params!");
		}else{
			List<String> dataFileNameList = new ArrayList<String>();
			UtilImpl util = new UtilImpl();
			String DB_NUM = args[0];
			dataFileNameList = util.getSourceValueFileNameList();
			util.connectToDB(DB_NUM);
			for(String fileName:dataFileNameList){
				String tableName = util.getTableName(fileName);
				List<String> columnNames = util.getColumns(fileName);
				List<List<String>> datas = util.getArtificialData(fileName);
				
				if(util.getIsTruncateStatus(DB_NUM).equalsIgnoreCase("yes")){
					util.truncateTable(DB_NUM, tableName);
					util.batchInsertArtificialData(tableName, columnNames, datas, DB_NUM);					
				}
				if(util.getIsTruncateStatus(DB_NUM).equalsIgnoreCase("no")){
					util.batchInsertArtificialData(tableName, columnNames, datas, DB_NUM);
				}
				if(!columnNames.isEmpty()){
					columnNames.clear();
				}
				if(!datas.isEmpty()){
					datas.clear();
				}
			}
			util.disconnectFromDB(DB_NUM);
		}
	}

}
