package testInterface.test_V1;


import org.testng.annotations.DataProvider;

public class login_v10 extends BaseProcessor_1 {
	//�޸�login,��ȡ���Է�������ʵ�ֲ��Խ��������д
	
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params","CaseId"};
		Object[][] datas= CaseUtil_2.getCaseDataByApiId("1", cellNames);
		return datas;

	}
 	
     
}
