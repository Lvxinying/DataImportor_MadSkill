package com.taozuicezhe;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.apache.log4j.Logger;

public class DBConnection {	
	Logger log = Logger.getLogger(DBConnection.class);
	Connection conn = null;
	String isTruncate = null;
	String DriverName = null;
	String URL = null;
	String UserName = null;
	String PassWord = null;
	
	public DBConnection(String DB_NUM) throws CustomerizedException{
		ConfigFileReader config = new ConfigFileReader();
		if(config != null){
			this.isTruncate = config.getResourceInfo("TRUNCATE_STATUS");
			if(DB_NUM.equalsIgnoreCase("DB1")){
				this.DriverName = config.getResourceInfo("DB1_DRIVERNAME");
				this.URL = config.getResourceInfo("DB1_URL");
				this.UserName = config.getResourceInfo("DB1_USERNAME");
				this.PassWord = config.getResourceInfo("DB1_PASSWORD");
			}else if(DB_NUM.equalsIgnoreCase("DB2")){
				this.DriverName = config.getResourceInfo("DB2_DRIVERNAME");
				this.URL = config.getResourceInfo("DB2_URL");
				this.UserName = config.getResourceInfo("DB2_USERNAME");
				this.PassWord = config.getResourceInfo("DB2_PASSWORD");
			}else if(DB_NUM.equalsIgnoreCase("DB3")){
				this.DriverName = config.getResourceInfo("DB3_DRIVERNAME");
				this.URL = config.getResourceInfo("DB3_URL");
				this.UserName = config.getResourceInfo("DB3_USERNAME");
				this.PassWord = config.getResourceInfo("DB3_PASSWORD");
			}else if(DB_NUM.equalsIgnoreCase("DB4")){
				this.DriverName = config.getResourceInfo("DB4_DRIVERNAME");
				this.URL = config.getResourceInfo("DB4_URL");
				this.UserName = config.getResourceInfo("DB4_USERNAME");
				this.PassWord = config.getResourceInfo("DB4_PASSWORD");
			}else if(DB_NUM.equalsIgnoreCase("DB5")){
				this.DriverName = config.getResourceInfo("DB5_DRIVERNAME");
				this.URL = config.getResourceInfo("DB5_URL");
				this.UserName = config.getResourceInfo("DB5_USERNAME");
				this.PassWord = config.getResourceInfo("DB5_PASSWORD");
			}else if(DB_NUM.equalsIgnoreCase("DB6")){
				this.DriverName = config.getResourceInfo("DB6_DRIVERNAME");
				this.URL = config.getResourceInfo("DB6_URL");
				this.UserName = config.getResourceInfo("DB6_USERNAME");
				this.PassWord = config.getResourceInfo("DB6_PASSWORD");
			}else{
				throw new CustomerizedException("Only surport 6 DB,you " +
						"must use DB1,DB2,DB3,DB4,DB5,DB6 to change the DB " +
						"you want to connect with!");
			}
		}
	}

	public Connection connectToDB() throws CustomerizedException{
		if(this.DriverName != null && 
				this.URL != null && this.UserName != null &&
				this.PassWord != null && this.conn == null){			
			try {
				Class.forName(this.DriverName);
				this.conn = DriverManager.getConnection(this.URL,this.UserName,this.PassWord);
			} catch (SQLException e) {
				log.error("Connect to DB meet trouble,err: "+e.getLocalizedMessage());
				e.printStackTrace();
			} catch (ClassNotFoundException e) {
				log.error("Adding driver meet trouble,err: "+e.getLocalizedMessage());
			}
		}else{
			throw new CustomerizedException("Can't connect to the DB!");
		}
		log.info("Connecting to DB successfully!");
		return conn;
	}

	public void closeConnection(){
		if(this.conn != null){
			try {
				this.conn.close();
				log.info("Closing DB connection succesfully!");
			} catch (SQLException e) {
				log.error("Close connection meet trouble,err: "+e.getLocalizedMessage());
			}
		}
	}

	public String getIsTruncate() {
		return this.isTruncate;
	}
}
