package testInterface.test_V1;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSONObject;

public class BaseProcessor_1 {
	//ʵ����������д��
	@Test(dataProvider="datas")
    public void login(String apiIdFromCase,String parameters,String CaseId) {
		String url=RestUtil_1.getUrlByApiId(apiIdFromCase);
		String type=RestUtil_1.getTypeByApiId(apiIdFromCase);
		Map<String,String> parameter=(Map<String,String>)  JSONObject.parse(parameters);
		String result=HttpUtil2.doMethode(url,type,parameter);
		System.out.println(result);
		//����Ӧ������浽list��,������ɺ�һ��д�����ļ�
		ExcelUtil_6.writeBackDatas.add(new WriteBackData("����", CaseId, "ActualResponseData", result));

    }
	@AfterSuite
	public static void  writeBackDatas() {
		ExcelUtil_6.batchWriteBack("src/test/resources/��������_V2.xlsx");
	}
}
