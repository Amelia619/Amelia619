package testInterface.test_V1;


import java.util.HashMap;
import java.util.Map;




public class getDemo {
//public static Map<String,String> tokenMapping=new HashMap<String,String>  ();
//	static{
//		postDemo.login();
//	}
	public static void main(String[] args) {
		  //http://114.135.61.188:58088/api/pay/getAllBaseWatercategory?pageNum=1&pageSize=10&name=
		//1��׼��URL�Ͳ������ݣ���ƴ�ӳ�����Ĳ��Ե�ַ
		 String  url="http://114.135.61.188:58088/api/pay/getAllBaseWatercategory";
         //��������
		 String pageNum="1";
		 String pageSize="10";
		 String name="";
		 Map<String,String> parameter=new HashMap<String,String>();
		 parameter.put("pageNum",pageNum);
		 parameter.put("pageSize",pageSize);
		 parameter.put("name",name);
	     HttpUtil1.doGet(url,parameter);
	}

}
