package testInterface.test_V1;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

public class BaseProcessor_5 {	
	//ʵ�ֱ������������ڷ�������֮ǰʵ�ֱ�������
	@Test(dataProvider="datas")
    public void test(String apiIdFromCase,String parameters,String CaseId,String PreValidateSql,String AfterValidateSql) {
		if(PreValidateSql!=null&&PreValidateSql.trim().length()>0) {
			String PreValidateResult=DBCheckUtil.doQuery(PreValidateSql);
			ExcelUtil_6.writeBackDatas.add(new WriteBackData("����", CaseId, "PreValidateResult", PreValidateResult));
		}
		String url=RestUtil_1.getUrlByApiId(apiIdFromCase);
		String type=RestUtil_1.getTypeByApiId(apiIdFromCase);
		//ִ������ǰ�滻����������ʵ�ֱ���������
		parameters=VariableUtil.replacesVariable(parameters);
		System.out.println(parameters);
		int flag=0;
		String result=HttpUtil3.doRequestByName(type,parameters,url,0);
		//���ԣ��ж��Ƿ���Ԥ�ڽ����ͬ
//		 String result=AssertUtil.assertEqual(ExpectedResponseData, ActualResponseData);
		 System.out.println(result);
		//����Ӧ������浽list��,������ɺ�һ��д�����ļ�
		ExcelUtil_6.writeBackDatas.add(new WriteBackData("����", CaseId, "ActualResponseData", result));
		if(AfterValidateSql!=null&&AfterValidateSql.trim().length()>0) {
			String AfterValidateResult=DBCheckUtil.doQuery(AfterValidateSql);
			ExcelUtil_6.writeBackDatas.add(new WriteBackData("����", CaseId, "AfterValidateResult", AfterValidateResult));
		}
    }
	
	//����д������
	@AfterSuite
	public static void  writeBackDatas() {
		ExcelUtil_6.batchWriteBack(propertiesUtil.getExcelPath());
	}
}
