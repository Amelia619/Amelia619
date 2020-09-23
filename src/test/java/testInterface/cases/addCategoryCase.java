package testInterface.cases;


import org.testng.annotations.DataProvider;

import testInterface.utils.CaseUtil;

public class addCategoryCase extends BaseProcessor {
	@DataProvider
	public Object[][] datas(){
		Object[][] datas= CaseUtil.getCaseDataByApiId("2", cellNames);
		return datas;
	}
	
}
