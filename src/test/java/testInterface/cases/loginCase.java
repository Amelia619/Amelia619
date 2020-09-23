package testInterface.cases;


import org.testng.annotations.DataProvider;

import testInterface.utils.CaseUtil;

public class loginCase extends BaseProcessor {
	//更改分别继承BaseProcessor_3\4\5，cellNames中添加存储sql语句的字段，实现数据库查询
	//查询数据库，对比查询前后的变化
	@DataProvider
	public Object[][] datas(){
		Object[][] datas= CaseUtil.getCaseDataByApiId("1", cellNames);
		return datas;
	}
}
