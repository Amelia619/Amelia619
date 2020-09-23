package testInterface.test_V1;


import org.testng.annotations.DataProvider;

public class addCategory2 extends BaseProcessor_2 {
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params","CaseId"};
		Object[][] datas= CaseUtil_2.getCaseDataByApiId("2", cellNames);
		return datas;
	}
}
