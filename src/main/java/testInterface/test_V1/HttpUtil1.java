package testInterface.test_V1;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

public class HttpUtil1 {
	public static String result="";
	public static String doGet(String url,Map<String,String> parameter) {
		//拼接URL
		Set<String>  keys=parameter.keySet();
		int mark=1;  //定义一个标志位
		for (String para : keys) {
			if(mark==1) {
				url=url+"?"+para+"="+parameter.get(para);
			}else {
				url=url+"&"+para+"="+parameter.get(para);
			}
			mark++;
		}
		System.out.print(url+"\n");
	 //2、创建HttpGet对象，指定接口提交的方式
     HttpGet get =new HttpGet(url);
     
     //3、设置请求头
//     String value=getDemo.tokenMapping.get("token");
// 	value="Bearer"+" "+value;
// 	System.out.println("map中token:--"+value);
// 	if(value!=null) {
// 		 get.setHeader("Authorization",value);
// 	}	
   get.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjMsImxvZ2luVG9rZW4iOiI5ZTM1MGZjYy0wMWU5LTRlOTQtOTdmOS0xMTE4OTc1MjlkMzkiLCJleHAiOjE1OTQxMjgzMDh9._1eHgXPoWisAZHHuBacsIJnN3UERwFKe4tTYeoxk9vWKk4XFjTNoTY9KhDVJHC9h-4be9fkxCqOqmC-KRR4iqg");
     //4、创建一个可关闭的HttpClient对象
     HttpClient httpclient=HttpClients.createDefault();
     try {
     //5、执行post请求，获取响应信息（状态码、响应报文、响应头等）。相当于jmeter上点击运行按钮，然后赋值给HttpResponse对象接收
		HttpResponse httpresponse=httpclient.execute(get);
		
	 //6、获取响应状态码和响应报文，并输出
		int code=httpresponse.getStatusLine().getStatusCode();
		System.out.print(code);
		 result=EntityUtils.toString((httpresponse.getEntity()));
		System.out.print(result);
	} catch (Exception e) {
		e.printStackTrace();
	} 
     return result;
 }
	public static String doPost(String url, Map<String,String> parameter)  {
	       
    	//2、使用BasicNameValuePair封装post请求中的参数名称和值
    	List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
    	Set<String> keys=parameter.keySet();
		for (String para : keys) {
			parameters.add(new BasicNameValuePair(para,parameter.get(para)));
		}
    	
    	//3、创建HttpPost对象，指定接口提交的方式
    	HttpPost post=new HttpPost(url);
    	
    	//4、把请求参数放到请求体中--------定义请求的实体，并把参数传进来，定义编码格式为utf-8
		try {
        post.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
			//5、创建一个可关闭的HttpClient对象
			HttpClient httpclient=HttpClients.createDefault();
			
			//6、执行post请求，获取响应信息（状态码、响应报文、响应头等）。相当于jmeter上点击运行按钮，然后赋值给HttpResponse对象接收
			HttpResponse httpresponse=httpclient.execute(post);
			
			//7、获取响应状态码和响应报文，并输出
			int code=httpresponse.getStatusLine().getStatusCode();
			System.out.print(code+";");
			 result=EntityUtils.toString((httpresponse.getEntity()));
			System.out.print(result+"\n");
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}	
		return result;
    }
}
