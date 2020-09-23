package testInterface.cases;

import java.util.Map;

import org.apache.log4j.Logger;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.JSONPath;

import testInterface.pojo.WriteBackData;
import testInterface.utils.DBCheckUtil;
import testInterface.utils.ExcelUtil;
import testInterface.utils.HttpUtil;
import testInterface.utils.RestUtil;
import testInterface.utils.VariableUtil;
import testInterface.utils.propertiesUtil;

public class BaseProcessor {	
	public  Logger logger = Logger.getLogger(BaseProcessor.class); 
	String cellNames[]= {"ApiId","Params","CaseId","PreValidateSql","AfterValidateSql"};
	//实现变量参数化，在发送请求之前实现变量更换
	@Test(dataProvider="datas")
    public void test(String apiIdFromCase,String parameters,String CaseId,String PreValidateSql,String AfterValidateSql) {
		logger.info("执行前数据验证------");
		if(PreValidateSql!=null&&PreValidateSql.trim().length()>0) {
			String PreValidateResult=DBCheckUtil.doQuery(PreValidateSql);
			ExcelUtil.writeBackDatas.add(new WriteBackData("用例", CaseId, "PreValidateResult", PreValidateResult));
		}
		String url=RestUtil.getUrlByApiId(apiIdFromCase);
		String type=RestUtil.getTypeByApiId(apiIdFromCase);
		//执行请求前替换变量参数，实现变量参数化
		parameters=VariableUtil.replacesVariable(parameters);
		System.out.println(parameters);
		Map<String,String> parameter=(Map<String,String>)  JSONObject.parse(parameters);
		String result=null;
		if("get".equalsIgnoreCase(type)) {
			result=HttpUtil.doRequestByName(type,parameter,url);    
    	}else if("post".equalsIgnoreCase(type)){
    		if("1".equalsIgnoreCase(apiIdFromCase)) {
    			result=HttpUtil.doRequestByName(type,parameter,url,0);    
    		}else{
    			result=HttpUtil.doRequestByName(type,parameter,url,1);    
    		}
    	}
		//断言，判断是否与预期结果相同
//		 String result=AssertUtil.assertEqual(ExpectedResponseData, ActualResponseData);
		 //System.out.println(result);
		//将响应结果保存到list中,测试完成后，一起写入表格文件
		ExcelUtil.writeBackDatas.add(new WriteBackData("用例", CaseId, "ActualResponseData", result));
		if(AfterValidateSql!=null&&AfterValidateSql.trim().length()>0) {
			String AfterValidateResult=DBCheckUtil.doQuery(AfterValidateSql);
			ExcelUtil.writeBackDatas.add(new WriteBackData("用例", CaseId, "AfterValidateResult", AfterValidateResult));
		}
    }
	
	//批量写回数据
	@AfterSuite
	public static void  writeBackDatas() {
		ExcelUtil.batchWriteBack(propertiesUtil.getExcelPath());
	}
}
