package testInterface.test_V1;


import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpRequest;
import org.apache.http.client.ClientProtocolException;
import org.omg.CORBA.Request;

import com.alibaba.fastjson.JSONPath;



public class postDemo {
	
	//   public static void  main(String[] args)  {
	public static void login() {	
	//1、准备测试请求的地址和参数（测试数据）
    	String url="http://114.135.61.188:58088/api/user/smsCodeLogin";
    	String mobile="13265537610";
    	String smsCode="9999";
    	 Map<String,String> parameter=new HashMap<String,String>();
		 parameter.put("mobile",mobile);
		 parameter.put("smsCode",smsCode);
		String result= HttpUtil1.doPost(url,parameter);
//		 Object token=JSONPath.read(result, "$.data");
//		 System.out.println("获取token:--"+token);
//		 if(token!=null) {
//			getDemo.tokenMapping.put("token", token.toString());
//		 }

     }	
//    public static void setToken(HttpRequest request) {
//    	String value=getDemo.tokenMapping.get("token");
//    	System.out.println("map中token:--"+"Bearer "+value);
//    	if(value!=null) {
//    		 request.addHeader("Authorization","Bearer "+value);
//    	}	
//    }
    
}
