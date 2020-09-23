package testInterface.test_V1;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

public class BaseProcessor_5 {	
	//实现变量参数化，在发送请求之前实现变量更换
	@Test(dataProvider="datas")
    public void test(String apiIdFromCase,String parameters,String CaseId,String PreValidateSql,String AfterValidateSql) {
		if(PreValidateSql!=null&&PreValidateSql.trim().length()>0) {
			String PreValidateResult=DBCheckUtil.doQuery(PreValidateSql);
			ExcelUtil_6.writeBackDatas.add(new WriteBackData("用例", CaseId, "PreValidateResult", PreValidateResult));
		}
		String url=RestUtil_1.getUrlByApiId(apiIdFromCase);
		String type=RestUtil_1.getTypeByApiId(apiIdFromCase);
		//执行请求前替换变量参数，实现变量参数化
		parameters=VariableUtil.replacesVariable(parameters);
		System.out.println(parameters);
		int flag=0;
		String result=HttpUtil3.doRequestByName(type,parameters,url,0);
		//断言，判断是否与预期结果相同
//		 String result=AssertUtil.assertEqual(ExpectedResponseData, ActualResponseData);
		 System.out.println(result);
		//将响应结果保存到list中,测试完成后，一起写入表格文件
		ExcelUtil_6.writeBackDatas.add(new WriteBackData("用例", CaseId, "ActualResponseData", result));
		if(AfterValidateSql!=null&&AfterValidateSql.trim().length()>0) {
			String AfterValidateResult=DBCheckUtil.doQuery(AfterValidateSql);
			ExcelUtil_6.writeBackDatas.add(new WriteBackData("用例", CaseId, "AfterValidateResult", AfterValidateResult));
		}
    }
	
	//批量写回数据
	@AfterSuite
	public static void  writeBackDatas() {
		ExcelUtil_6.batchWriteBack(propertiesUtil.getExcelPath());
	}
}
