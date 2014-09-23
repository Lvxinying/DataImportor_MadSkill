package com.taozuicezhe;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

import org.apache.log4j.Logger;

public class ConfigFileReader extends ResourceManagerAbs{
	Logger log = Logger.getLogger(ConfigFileReader.class);
	Properties prop = null;
	String configPath = System.getProperty("user.dir")+"/Config/Config.properties";
	
	String DriverName;
	String Url;
	String UserName;
	String PassWord;
	String isTruncate;

	@Override
	public Properties getProperties(String configFilePath) throws CustomerizedException {
		try {
			this.prop = loadPropertiesFile(new File(configFilePath));
			if(this.prop ==  null){	
			log.error("Load Config.properties file meet trouble!");
			throw new CustomerizedException("Load Config.properties file meet trouble!");
		}			
		} catch (IOException e) {
			e.printStackTrace();
		}
		return this.prop;
	}

	@Override
	public String getResourceInfo(String methodName) {
		String returnStr = "";
		try {
			switch(methodName){
			case "TRUNCATE_STATUS":
				this.isTruncate = getTruncateStatus();
				returnStr = this.isTruncate;
				break;
			case "DB1_DRIVERNAME":
				this.DriverName = getDriverNameDB1();
				returnStr = this.DriverName;
				break;
			case "DB2_DRIVERNAME":
				this.DriverName = getDriverNameDB2();
				returnStr = this.DriverName;
				break;
			case "DB3_DRIVERNAME":
				this.DriverName = getDriverNameDB3();
				returnStr = this.DriverName;
				break;
			case "DB4_DRIVERNAME":
				this.DriverName = getDriverNameDB4();
				returnStr = this.DriverName;
				break;
			case "DB5_DRIVERNAME":
				this.DriverName = getDriverNameDB5();
				returnStr = this.DriverName;
				break;
			case "DB6_DRIVERNAME":
				this.DriverName = getDriverNameDB6();
				returnStr = this.DriverName;
				break;
			case "DB1_URL":
				this.Url = getUrlDB1();
				returnStr = this.Url;
				break;
			case "DB2_URL":
				this.Url = getUrlDB2();
				returnStr = this.Url;
				break;
			case "DB3_URL":
				this.Url = getUrlDB3();
				returnStr = this.Url;
				break;
			case "DB4_URL":
				this.Url = getUrlDB4();
				returnStr = this.Url;
				break;
			case "DB5_URL":
				this.Url = getUrlDB5();
				returnStr = this.Url;
				break;
			case "DB6_URL":
				this.Url = getUrlDB6();
				returnStr = this.Url;
				break;
			case "DB1_USERNAME":
				this.UserName = getUserNameDB1();
				returnStr = this.UserName;
				break;
			case "DB2_USERNAME":
				this.UserName = getUserNameDB2();
				returnStr = this.UserName;
				break;
			case "DB3_USERNAME":
				this.UserName = getUserNameDB3();
				returnStr = this.UserName;
				break;
			case "DB4_USERNAME":
				this.UserName = getUserNameDB4();
				returnStr = this.UserName;
				break;
			case "DB5_USERNAME":
				this.UserName = getUserNameDB5();
				returnStr = this.UserName;
				break;
			case "DB6_USERNAME":
				this.UserName = getUserNameDB6();
				returnStr = this.UserName;
				break;
			case "DB1_PASSWORD":
				this.PassWord = getPassWordDB1();
				returnStr = this.PassWord;
				break;
			case "DB2_PASSWORD":
				this.PassWord = getPassWordDB2();
				returnStr = this.PassWord;
				break;
			case "DB3_PASSWORD":
				this.PassWord = getPassWordDB3();
				returnStr = this.PassWord;
				break;
			case "DB4_PASSWORD":
				this.PassWord = getPassWordDB4();
				returnStr = this.PassWord;
				break;
			case "DB5_PASSWORD":
				this.PassWord = getPassWordDB5();
				returnStr = this.PassWord;
				break;
			case "DB6_PASSWORD":
				this.PassWord = getPassWordDB6();
				returnStr = this.PassWord;
				break;
				default:
					break;
			}
		} catch (CustomerizedException e) {
			e.printStackTrace();
		}
		return returnStr;
	}
	
	private Properties loadPropertiesFile(File propsFile) throws IOException {
		Properties props = new Properties();				
		FileInputStream fis = new FileInputStream(propsFile);
		props.load(fis);
		fis.close();
		return props;
	}
	
	private String getTruncateStatus() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("Truncate").trim();		
		return returnStr;
	}
	
	private String getDriverNameDB1() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num1.driver").trim();		
		return returnStr;
	}
	
	private String getDriverNameDB2() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num2.driver").trim();		
		return returnStr;
	}
	
	private String getDriverNameDB3() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num3.driver").trim();		
		return returnStr;
	}
	
	private String getDriverNameDB4() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num4.driver").trim();		
		return returnStr;
	}
	
	private String getDriverNameDB5() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num5.driver").trim();		
		return returnStr;
	}
	
	private String getDriverNameDB6() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num6.driver").trim();		
		return returnStr;
	}
	
	private String getUrlDB1() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num1.url").trim();		
		return returnStr;
	}
	
	private String getUrlDB2() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num2.url").trim();		
		return returnStr;
	}
	
	private String getUrlDB3() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num3.url").trim();		
		return returnStr;
	}
	
	private String getUrlDB4() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num4.url").trim();		
		return returnStr;
	}
	
	private String getUrlDB5() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num5.url").trim();		
		return returnStr;
	}
	
	private String getUrlDB6() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num6.url").trim();		
		return returnStr;
	}
	
	private String getUserNameDB1() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num1.username").trim();		
		return returnStr;
	}
	
	private String getUserNameDB2() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num2.username").trim();		
		return returnStr;
	}
	
	private String getUserNameDB3() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num3.username").trim();		
		return returnStr;
	}
	
	private String getUserNameDB4() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num4.username").trim();		
		return returnStr;
	}
	
	private String getUserNameDB5() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num5.username").trim();		
		return returnStr;
	}
	
	private String getUserNameDB6() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num6.username").trim();		
		return returnStr;
	}
	
	private String getPassWordDB1() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num1.password").trim();		
		return returnStr;
	}
	
	private String getPassWordDB2() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num2.password").trim();		
		return returnStr;
	}
	
	private String getPassWordDB3() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num3.password").trim();		
		return returnStr;
	}
	
	private String getPassWordDB4() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num4.password").trim();		
		return returnStr;
	}
	
	private String getPassWordDB5() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num5.password").trim();		
		return returnStr;
	}
	
	private String getPassWordDB6() throws CustomerizedException{
		String returnStr;
		Properties prop = getProperties(this.configPath);
		returnStr = prop.getProperty("db.num6.password").trim();		
		return returnStr;
	}
}
