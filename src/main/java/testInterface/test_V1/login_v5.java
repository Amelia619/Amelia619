package testInterface.test_V1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class login_v5 {
	//调用ExcelUtil_3,处理调取数据行列不连续问题
	@Test(dataProvider="datas")
     public void login(String mobile,String smsCode) {
		String url="http://114.135.61.188:58088/api/user/smsCodeLogin";
     	 Map<String,String> parameter=new HashMap<String,String>();
 		 parameter.put("mobile",mobile);
 		 parameter.put("smsCode",smsCode);
 		 HttpUtil1.doPost(url,parameter);
     }
	@DataProvider
	public Object[][] datas(){
		String excelPath="src/test/resources/测试用例_V1.xlsx";
		int rowNum[]= {1,2,3,4,5,6,7};
		int cellNum[]= {5,6};
		Object[][] datas= ExcelUtil_3.getDatas(excelPath,"Sheet1",rowNum,cellNum);
		return datas;

	}
 	
     
}
