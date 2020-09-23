package testInterface.cases;

import org.testng.annotations.DataProvider;

import testInterface.utils.CaseUtil;

public class getCategoryCase extends BaseProcessor {
	
	@DataProvider
	public Object[][] datas(){
		Object[][] datas= CaseUtil.getCaseDataByApiId("4", cellNames);
		return datas;
	}
}
