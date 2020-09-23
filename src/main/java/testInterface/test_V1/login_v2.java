package testInterface.test_V1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class login_v2 {
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

		Object[][] datas= {
				{"13265537610","6666"},
				{"","6666"},
				{"12345689562","6666"},
				{"13265","6666"},
				{"13203890553","6666"},
				{"13265537610","7777"},
				{"13265537610",""},
		};
		return datas;

	}
 	
     
}
