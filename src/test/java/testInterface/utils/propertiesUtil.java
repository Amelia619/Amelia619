package testInterface.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.Properties;

public class propertiesUtil {
	//获取excel表格的路径
    private static Properties properties=new Properties();
    static {
 	   InputStream inputstream;	   
 	   try {
			inputstream=new FileInputStream(new File("src/test/resources/config.properties"));
			properties.load(inputstream);
		} catch (Exception e) {
			e.printStackTrace();
		}
    }
    
    public static String getExcelPath() {
    	return properties.getProperty("ExcelPath");
    }
    
    public static void main(String[] args) {
		System.out.println(getExcelPath());
	}
}
