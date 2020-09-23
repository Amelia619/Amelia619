package testInterface.test_V1;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

public class addCategory {
	
	public static void main(String[] args) {
	    	add1();
	    }
	    
	    public static void add1() {
	    //http://127.0.0.1:8080/api/pay/getAllBaseWatercategory?pageNum=1&pageSize=10&name=
	    //1、准备URL和测试数据，并拼接成请求的测试地址
	   //  String  url="http://114.135.61.188:58080/api/pay/addPayBaseWatercategory";
    String url="http://114.135.61.188:58088/api/user/smsCodeLogin";
//	     String id="52";
//	     String name="name";
//	     String remarks="remarksaa";
//	         
//	     List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
	     HttpPost post=new HttpPost(url);
//	      parameters.add(new BasicNameValuePair("id",id));
//	      parameters.add(new BasicNameValuePair("name",name));
//	      parameters.add(new BasicNameValuePair("remarks",remarks));
	      //   String parameter="{\"id\":\"\",\"name\":\"1\",\"remarks\":\"1\"}";   
	      String parameter="{'mobile':'13265537610','smsCode':'6666'}";
	      post.setHeader("Content-Type", "application/x-www-form-urlencoded");
	    //  post.setHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjMsImxvZ2luVG9rZW4iOiJiN2U0ZjRkMi1iOWRkLTQ1NzUtOTgzYS1jYjdjM2EyMDY5NmEiLCJleHAiOjE1OTYxOTI1OTB9.LhL8kgJVc3AOSlJLb3enUpILzNWbSlEhgplEYmKa15MlrvENfh0blQD8yyktpK9u7MAzt3GU7h50ULvjdvPEBw");
//	      post.setHeader("Referer", "http://114.135.61.188:58088/");
//	      post.setHeader("Accept-Language", "zh-CN,zh;q=0.8,en-US;q=0.5,en;q=0.3");
	     // post.setHeader("Content-Type", "application/json;charset=utf-8");
//	      post.setHeader("Accept-Encoding", "gzip, deflate");
//	      post.setHeader("User-Agent", "Mozilla/5.0 (Windows NT 6.1; Win64; x64; rv:56.0) Gecko/20100101 Firefox/56.0");
//	      post.setHeader("Accept", "application/json, text/plain, */*");
//	      post.setHeader("Cookie", "Hm_lvt_b6c55502f1f8d7304ff69b6cf38fca51=1595209012,1595377223,1596005570; td_cookie=3047893734; Hm_lpvt_b6c55502f1f8d7304ff69b6cf38fca51=1596156598");
//	      post.setHeader("Connection", "keep-alive");	    
	      try {
			post.setEntity(new StringEntity(parameter,"utf-8"));
			HttpClient httpclient=HttpClients.createDefault();
			HttpResponse httpresponse=httpclient.execute(post);
			//获取响应状态码和响应报文，并输出
		
			int code=httpresponse.getStatusLine().getStatusCode();
			System.out.print(code);
			String result=EntityUtils.toString((httpresponse.getEntity()));
			System.out.print(result);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
      }
	    
	    public static void add2() {
	    	 String url ="http://114.135.61.188:58080/api/pay/addPayBaseWatercategory";
	    	 HttpPost httppost= new HttpPost(url);
	    	 
	         JSONObject jsonParam = new JSONObject();
	         jsonParam.put("id", "156");
	         jsonParam.put("name", "15627898765");
	         jsonParam.put("remarks","e10adc3949ba59abbe56e057f20f883e");
	         httppost.setHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjMsImxvZ2luVG9rZW4iOiJiN2U0ZjRkMi1iOWRkLTQ1NzUtOTgzYS1jYjdjM2EyMDY5NmEiLCJleHAiOjE1OTYxOTI1OTB9.LhL8kgJVc3AOSlJLb3enUpILzNWbSlEhgplEYmKa15MlrvENfh0blQD8yyktpK9u7MAzt3GU7h50ULvjdvPEBw");
	         httppost.setHeader("Content-Type", "application/json;charset=utf-8");
	        
	         try {
	            // StringEntity entity = new StringEntity(jsonParam.toString(), "utf-8");// 解决中文乱码问题
	             //entity.setContentEncoding("UTF-8");
	            // entity.setContentType("application/json");
	             httppost.setEntity(new StringEntity(jsonParam.toString(), "utf-8"));
	             HttpClient httpclient=HttpClients.createDefault();
	             HttpResponse response= httpclient.execute(httppost); 
	             String strResult = EntityUtils.toString(response.getEntity());
	             System.out.println("查看返回的结果：" + strResult);
	         } catch (Exception e) {
	             e.printStackTrace();
	         }

	         httppost.releaseConnection();
	     }

}

