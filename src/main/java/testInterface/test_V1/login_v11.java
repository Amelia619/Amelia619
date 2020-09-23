package testInterface.test_V1;


import org.testng.annotations.DataProvider;

public class login_v11 extends BaseProcessor_2 {
	//更改继承BaseProcessor_2 ，cellNames中添加预期结果字段，实现响应断言
	
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params","CaseId","ExpectedResponseData"};
		Object[][] datas= CaseUtil_2.getCaseDataByApiId("1", cellNames);
		return datas;

	}
 	
     
}
