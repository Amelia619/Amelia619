package testInterface.test_V1;

import org.testng.Assert;

public class AssertUtil {
     public static String assertEqual( String ExpectedResponseData,String ActualResponseData) {
    	 String result="ͨ��";
    	 try {
    	 Assert.assertEquals(ExpectedResponseData, ActualResponseData);
    	 } catch(Throwable e) {
    		 result = ActualResponseData;
    	 }	 
    	 return result;
     }
}
