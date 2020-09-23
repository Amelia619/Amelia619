package testInterface.test_V1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class login_v4 {
	//调用ExcelUtil_2，优化读取类，传入行列还是和结束的索引，解决上一版本行列需在代码中指定的问题。
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
		Object[][] datas= ExcelUtil_2.getDatas(excelPath,1,7,5,6);//传入开始和结束的行列索引，注意不是行列号
		return datas;

	}
 	
     
}
