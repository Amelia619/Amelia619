package testInterface.test_V1;

import java.util.HashMap;
import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class login_v4 {
	//����ExcelUtil_2���Ż���ȡ�࣬�������л��Ǻͽ����������������һ�汾�������ڴ�����ָ�������⡣
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
		String excelPath="src/test/resources/��������_V1.xlsx";
		Object[][] datas= ExcelUtil_2.getDatas(excelPath,1,7,5,6);//���뿪ʼ�ͽ���������������ע�ⲻ�����к�
		return datas;

	}
 	
     
}
