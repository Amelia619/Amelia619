package testInterface.test_V1;


import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class login_v8 {
	//处理两个表单，接口信息和用例,获取url和type
	@Test(dataProvider="datas")
     public void login(String apiIdFromCase,String parameters) {
		String url="";
		String type="";
		int rowNum[]= {1,2,3,4,5,6,7,8,9,10,11};
		int cellNum[]= {0,2,3};
		Object[][] datas= ExcelUtil_3.getDatas("src/test/resources/测试用例_V2.xlsx","接口信息",rowNum,cellNum);
		//(0,0)1;(0,1)post;(0,2)地址； 
		//(1,0)2;(1,1)post;(1,2)地址；
		String apiIdFromRest="";
		for(Object[] objects:datas) {//行循环
			 apiIdFromRest=objects[0].toString();	//取出第一列---apiId列的数据
			if(apiIdFromRest.equalsIgnoreCase(apiIdFromCase)) {
				type=objects[1].toString();//取出第二列---请求类型列的数据
				url=objects[2].toString();   //取出第三列--地址列的数据
				break;
			}
		}
		Map<String,String> parameter=(Map<String,String>)  JSON.parse(parameters);
		System.out.println(apiIdFromRest+type+url);
 		 System.out.println(HttpUtil2.doMethode(url,type,parameter));
     }
	
	@DataProvider
	public Object[][] datas(){
		int rowNum[]= {1,2,3,4,5,6,7};
		int cellNum[]= {1,3};
		Object[][] datas= ExcelUtil_3.getDatas("src/test/resources/测试用例_V2.xlsx","用例",rowNum,cellNum);
		return datas;
	}
	/*
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params"};
		Object[][] datas= CaseUtil_1.getCaseDataByApiId("1", cellNames);
		return datas;
	} 
	*/
}
