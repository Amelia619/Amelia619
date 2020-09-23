package testInterface.test_V1;


import java.util.Map;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;


import com.alibaba.fastjson.JSONObject;

public class login_v9 {
	//�޸�login,ʵ�ֽӿ���Ϣ��Ԥ���غͲ��Խ����д
	@Test(dataProvider="datas")
     public void login(String apiIdFromCase,String parameters,String CaseId) {
		String url=RestUtil_1.getUrlByApiId(apiIdFromCase);
		String type=RestUtil_1.getTypeByApiId(apiIdFromCase);
		Map<String,String> parameter=(Map<String,String>)  JSONObject.parse(parameters);
 		String result=HttpUtil2.doMethode(url,type,parameter);
 		System.out.println(result);
    	ExcelUtil_5.writeBack("src/test/resources/��������_V2.xlsx","����", CaseId, "ActualResponseData", result);
     }
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params","CaseId"};
		Object[][] datas= CaseUtil_2.getCaseDataByApiId("1", cellNames);
		return datas;

	}
 	
     
}
