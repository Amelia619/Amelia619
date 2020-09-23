package testInterface.test_V1;


import org.testng.annotations.DataProvider;

public class login_v12 extends BaseProcessor_5 {
	//更改分别继承BaseProcessor_3\4\5，cellNames中添加存储sql语句的字段，实现数据库查询
	//查询数据库，对比查询前后的变化
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params","CaseId","PreValidateSql","AfterValidateSql"};
		Object[][] datas= CaseUtil_2.getCaseDataByApiId("1", cellNames);
		return datas;
	}
}
