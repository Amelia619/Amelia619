package testInterface.test_V1;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.http.HttpRequest;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;

import com.alibaba.fastjson.JSONPath;


public class HttpUtil2 {
	public static final List<String> tokenList=new ArrayList<String> ();
	public static String doGet(String url,Map<String,String> parameter) {
		//ƴ��URL
		Set<String>  keys=parameter.keySet();
		int mark=1;  //����һ����־λ
		for (String para : keys) {
			if(mark==1) {
				url=url+"?"+para+"="+parameter.get(para);
			}else {
				url=url+"&"+para+"="+parameter.get(para);
			}
			mark++;
		}
		System.out.print(url+"\n");
	 //2������HttpGet����ָ���ӿ��ύ�ķ�ʽ
     HttpGet get =new HttpGet(url);
     
     //3����������ͷ
      setToken(get);
     //get.addHeader("Authorization", "Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOjMsImxvZ2luVG9rZW4iOiI5ZTM1MGZjYy0wMWU5LTRlOTQtOTdmOS0xMTE4OTc1MjlkMzkiLCJleHAiOjE1OTQxMjgzMDh9._1eHgXPoWisAZHHuBacsIJnN3UERwFKe4tTYeoxk9vWKk4XFjTNoTY9KhDVJHC9h-4be9fkxCqOqmC-KRR4iqg");
     
     //4������һ���ɹرյ�HttpClient����
     HttpClient httpclient=HttpClients.createDefault();
     String result="";
     try {
     //5��ִ��post���󣬻�ȡ��Ӧ��Ϣ��״̬�롢��Ӧ���ġ���Ӧͷ�ȣ����൱��jmeter�ϵ�����а�ť��Ȼ��ֵ��HttpResponse�������
		HttpResponse httpresponse=httpclient.execute(get);
		
	 //6����ȡ��Ӧ״̬�����Ӧ���ģ������
		int code=httpresponse.getStatusLine().getStatusCode();
		System.out.print(code);
		 result=EntityUtils.toString((httpresponse.getEntity()));
		System.out.print(result+"\n");
	} catch (Exception e) {
		e.printStackTrace();
	} 
     return result;
 }
	
		public static String doPost(String url, Map<String,String> parameter)  {
		       
	    	//2��ʹ��BasicNameValuePair��װpost�����еĲ������ƺ�ֵ
	    	List<BasicNameValuePair> parameters=new ArrayList<BasicNameValuePair>();
	    	Set<String> keys=parameter.keySet();
			for (String para : keys) {
				parameters.add(new BasicNameValuePair(para,parameter.get(para)));
			}
    	//3������HttpPost����ָ���ӿ��ύ�ķ�ʽ
    	HttpPost post=new HttpPost(url);
   
    	
    	//4������������ŵ���������--------���������ʵ�壬���Ѳ�������������������ʽΪutf-8
    	String result="";
		try {
			post.setEntity(new UrlEncodedFormEntity(parameters,"utf-8"));
			//5������һ���ɹرյ�HttpClient����
			HttpClient httpclient=HttpClients.createDefault();
			System.out.println(tokenList.size());
			if(tokenList.size()==3) {
			  setToken(post);		  
			}
			//6��ִ��post���󣬻�ȡ��Ӧ��Ϣ��״̬�롢��Ӧ���ġ���Ӧͷ�ȣ����൱��jmeter�ϵ�����а�ť��Ȼ��ֵ��HttpResponse�������
			HttpResponse httpresponse=httpclient.execute(post);
			
			//7����ȡ��Ӧ״̬�����Ӧ���ģ������
			int code=httpresponse.getStatusLine().getStatusCode();
	//		System.out.print(code+";");
			 result=EntityUtils.toString((httpresponse.getEntity()));
			 getToken(result);
	
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
    }
		public static String doMethode(String url,String type,Map<String,String> param) {			
			String result="";
			if("get".equalsIgnoreCase(type)) {
				result=HttpUtil2.doGet(url, param);
			}else if("post".equalsIgnoreCase(type)) {
				result=HttpUtil2.doPost(url, param);	
		   }
			return result;
		}
		
		public static void setToken(HttpRequest request) {
		    String value=tokenList.get(0);
		 	value="Bearer "+value;
		 	System.out.println("get��token:--"+value);
		 	if(value!=null) {
		 		 request.setHeader("Authorization", value);
		 	}	
		}
		
		public static void getToken(String result) {
			Object token=JSONPath.read(result, "$.data");
			 if(token!=null) {
				 tokenList.add( token.toString().trim());
			 }
		}
}
