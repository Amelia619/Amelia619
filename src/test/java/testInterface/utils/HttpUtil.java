package testInterface.utils;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.Header;
import org.apache.http.HttpEntity;
import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.ParseException;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.apache.log4j.Logger;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;


public class HttpUtil{
	public static final List<String> tokenList=new ArrayList<String> ();
    public static Logger logger=Logger.getLogger(HttpUtil.class.getClass());
	/**处理没有参数的get请求
	 * @param url
	 * @return
	 */
	public static String doGet(String url) {
		HttpGet get = new HttpGet(url);
		HttpClient httpclient = HttpClients.createDefault();
		setToken(get);
		try {
			HttpResponse httpresponse = httpclient.execute(get);
			return getResult(httpresponse);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	 return null;
	}
	
    /**处理带参数的get请求
     * @param parameters
     * @param url
     * @return
     */
    public static String doGet(Map<String,String> parameter,String url) {
    	Set<String> keys= parameter.keySet();
    	int mark=1;
    	for (String para : keys) {
			if(mark==1) {
				url=url+"?"+para+"="+parameter.get(para);
			}else {
				url=url+"&"+para+"="+parameter.get(para);
			}
			mark++;
		}
		logger.info("处理带参get请求，请求地址为："+url);
	    HttpGet get =new HttpGet(url);
	    HttpClient httpclient=HttpClients.createDefault();
	   // setToken(get);
	    get.setHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjY2LCJsb2dpblRva2VuIjoiZTBmOGViYTktZDA0Ni00ZTlkLWE0ZmItNWU5ODhiYWVkMjQ1IiwiZXhwIjoxNTk5NTU5OTE0fQ.Voo9AkT_sAZF5AttzzUapCdSBtAZPiC8TUnVbZqMJe4iyUd_0tHAqfekwdGLnKEEm1SbXiZxUHqAUWnP5WH8pg");
	     try {
			HttpResponse httpresponse=httpclient.execute(get);
			return getResult(httpresponse);
		} catch (Exception e) {
			e.printStackTrace();
		} 
	     return null;
    }
    
    
    //处理参数类型为非json格式的请求-----登录请求
    public static String  mapPost(Map<String,String> parameter,String url) {
    	List<BasicNameValuePair> paralist=new ArrayList<BasicNameValuePair>();
    	Set<String> keys=parameter.keySet();
		for (String para : keys) {
			paralist.add(new BasicNameValuePair(para,parameter.get(para)));
		}		
	    HttpPost post=new HttpPost(url);
		String result=null;
		try {
			post.setEntity(new UrlEncodedFormEntity(paralist,"utf-8"));
			HttpClient httpclient=HttpClients.createDefault();
	     	HttpResponse httpresponse=httpclient.execute(post);
	     	result= getResult(httpresponse);
		    getToken(result);
		} catch (Exception e) {		
			e.printStackTrace();
		}
	  return result;
    }
    
    //处理参数类型为json格式的请求
    public  static String jsonPost(Map<String,String> parameter,String url) {	
	    HttpPost post=new HttpPost(url);
	    JSONObject jsonParam = new JSONObject();
    	Set<String> keys=parameter.keySet();
		for (String para : keys) {
			jsonParam.put(para,parameter.get(para));
		}	
		 // setToken(post);	
	    post.setHeader("Authorization","Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjY2LCJsb2dpblRva2VuIjoiZTBmOGViYTktZDA0Ni00ZTlkLWE0ZmItNWU5ODhiYWVkMjQ1IiwiZXhwIjoxNTk5NTU5OTE0fQ.Voo9AkT_sAZF5AttzzUapCdSBtAZPiC8TUnVbZqMJe4iyUd_0tHAqfekwdGLnKEEm1SbXiZxUHqAUWnP5WH8pg");
	    post.setHeader("Content-Type", "application/json;charset=utf-8");
	    try {
			post.setEntity(new StringEntity(jsonParam.toString(),"utf-8"));
			HttpClient httpclient=HttpClients.createDefault();
	     	HttpResponse httpresponse=httpclient.execute(post);
	     	return getResult(httpresponse);
		} catch (Exception e) {		
			e.printStackTrace();
		}
	   return null;
    }
   
    
    /**获取响应结果
     * @param response
     * @return
     */
    public static String getResult(HttpResponse response) {
		// 获取状态码
		int code = response.getStatusLine().getStatusCode();
		System.out.println(code);
		// 获取body
		HttpEntity entity = response.getEntity();
		String body=null;
		try {
			body = EntityUtils.toString(entity);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		logger.info("请求响应结果为："+code+"; "+body);
		// 获取头信息
//		Header[] allHeaders = response.getAllHeaders();
//		String headers = Arrays.toString(allHeaders);
//		System.out.println(headers);
		// 返回body
		return body;
	}
    
    //设置鉴权信息
	public static void setToken(HttpRequest request) {
	    String value=tokenList.get(0);
	 	if(value!=null) {
	 		value="Bearer "+value;
	 		 request.setHeader("Authorization", value);
	 	}	
	}
	
	 //获取登录后的鉴权信息
	public static void getToken(String result) {
		Object token=JSONPath.read(result, "$.data");
		 if(token!=null) {
			 tokenList.add( token.toString().trim());
		 }
		 logger.info("获取的token为："+tokenList.get(0));
	}
    //根据请求类型调用不同的请求
    public static String doRequestByName(String type,Map<String,String> parameter,String url,int flag) {
    	if("get".equalsIgnoreCase(type)) {
    		return doGet(parameter,url);
    	}else if("post".equalsIgnoreCase(type)){
    		if(flag==0) {
    		return mapPost(parameter,url);
    		}else if(flag==1) {
    		return jsonPost(parameter,url);
    		}
    	}
    	return null;
    }
    
    //默认flag=1，调用json格式的post请求
    public static String doRequestByName(String type,Map<String,String> parameter,String url) {
    	return  doRequestByName(type,parameter,url,1);
    }
}
