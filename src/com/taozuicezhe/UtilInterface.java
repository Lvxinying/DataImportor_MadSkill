package com.taozuicezhe;

import java.sql.SQLException;
import java.util.List;

public interface UtilInterface {
	List<String> getSourceValueFileNameList();
	String getIsTruncateStatus(String DB_NUM) throws CustomerizedException;
	String getTableName(String fileName);
	List<String> getColumns(String fileName) throws CustomerizedException;
	List<List<String>> getArtificialData(String fileName);
	void batchInsertArtificialData(String tableName,List<String> columnNames,List<List<String>> datas,String DB_NUM) throws CustomerizedException;
	void truncateTable(String DB_NUM,String tableName) throws CustomerizedException, SQLException;
	void connectToDB(String DB_NUM) throws CustomerizedException;
	void disconnectFromDB(String DB_NUM) throws CustomerizedException;
}
