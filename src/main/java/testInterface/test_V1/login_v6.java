package testInterface.test_V1;


import java.util.Map;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class login_v6 {
	//调用ExcelUtil_3,使用JSONObject.parse处理传入数据格式为Json形式，需要将ExcelUtil_3中的表格名称换成Sheet2
	@Test(dataProvider="datas")
     public void login(String parameters) {
		String url="http://114.135.61.188:58088/api/user/smsCodeLogin";
		JSONObject.parse(parameters);
		Map<String,String> parameter= (Map<String,String>) JSON.parse(parameters);
		Set<String> pa=parameter.keySet();
		for (String pas : pa) {
			System.out.println(pas+"  "+parameter.get(pas));
		}
 		 HttpUtil1.doPost(url,parameter);
     }
	@DataProvider
	public Object[][] datas(){
		String excelPath="src/test/resources/测试用例_V1.xlsx";
		int rowNum[]= {1,2,3,4,5,6,7};
		int cellNum[]= {5};
		Object[][] datas= ExcelUtil_3.getDatas(excelPath,"Sheet2",rowNum,cellNum);
		return datas;
	}
 	
     
}
