package testInterface.test_V1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.Test;

public class login_v1 {
	@Test
     public void login_1() {
    	 String url="http://114.135.61.188:58088/api/user/smsCodeLogin";
     	String mobile="13265537610";
     	String smsCode="9999";
     	 Map<String,String> parameter=new HashMap<String,String>();
 		 parameter.put("mobile",mobile);
 		 parameter.put("smsCode",smsCode);
 		 HttpUtil1.doPost(url,parameter);
     }
	@Test
     public void login_2() {
    	 String url="http://114.135.61.188:58088/api/user/smsCodeLogin";
     	String mobile="123";    //ÊÖ»úºÅ´íÎó
     	String smsCode="9999";
     	 Map<String,String> parameter=new HashMap<String,String>();
 		 parameter.put("mobile",mobile);
 		 parameter.put("smsCode",smsCode);
 		 HttpUtil1.doPost(url,parameter);
     }
	@Test
     public void login_3() {
    	 String url="http://114.135.61.188:58088/api/user/smsCodeLogin";
     	String mobile="13265537610";
     	String smsCode="11";   //ÃÜÂë´íÎó
     	 Map<String,String> parameter=new HashMap<String,String>();
 		 parameter.put("mobile",mobile);
 		 parameter.put("smsCode",smsCode);
 		 HttpUtil1.doPost(url,parameter);
     }
 
     
}
