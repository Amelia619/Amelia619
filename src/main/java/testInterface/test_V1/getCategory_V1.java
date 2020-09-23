package testInterface.test_V1;

import org.testng.annotations.DataProvider;

public class getCategory_V1 extends BaseProcessor_2 {
	
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params","CaseId"};
		Object[][] datas= CaseUtil_2.getCaseDataByApiId("4", cellNames);
		return datas;
	}
}
