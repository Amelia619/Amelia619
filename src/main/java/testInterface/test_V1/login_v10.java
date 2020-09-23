package testInterface.test_V1;


import org.testng.annotations.DataProvider;

public class login_v10 extends BaseProcessor_1 {
	//修改login,提取测试方法，并实现测试结果批量回写
	
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params","CaseId"};
		Object[][] datas= CaseUtil_2.getCaseDataByApiId("1", cellNames);
		return datas;

	}
 	
     
}
