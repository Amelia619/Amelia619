package testInterface.test_V1;

import java.util.Map;

import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

public class BaseProcessor_2 {	
	//实现结果验证，与预期结果相同的写通过，不相同返回真实的响应数据
	@Test(dataProvider="datas")
    public void login(String apiIdFromCase,String parameters,String CaseId,String ExpectedResponseData) {
		String url=RestUtil_1.getUrlByApiId(apiIdFromCase);
		String type=RestUtil_1.getTypeByApiId(apiIdFromCase);
		Map<String,String> parameter=(Map<String,String>)  JSONObject.parse(parameters);
		String ActualResponseData=HttpUtil2.doMethode(url,type,parameter);
		//断言，判断是否与预期结果相同
		 String result=AssertUtil.assertEqual(ExpectedResponseData, ActualResponseData);
		 System.out.println(result);
		//将响应结果保存到list中,测试完成后，一起写入表格文件
		ExcelUtil_6.writeBackDatas.add(new WriteBackData("用例", CaseId, "ActualResponseData", result));

    }
	
	//批量写回数据
	@AfterSuite
	public static void  writeBackDatas() {
		ExcelUtil_6.batchWriteBack("src/test/resources/测试用例_V2.xlsx");
	}
}
