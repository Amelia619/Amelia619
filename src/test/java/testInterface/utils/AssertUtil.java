package testInterface.utils;

import org.testng.Assert;

public class AssertUtil {
     public static String assertEqual( String ExpectedResponseData,String ActualResponseData) {
    	 String result="Í¨¹ý";
    	 try {
    	 Assert.assertEquals(ExpectedResponseData, ActualResponseData);
    	 } catch(Throwable e) {
    		 result = ActualResponseData;
    	 }	 
    	 return result;
     }
}
