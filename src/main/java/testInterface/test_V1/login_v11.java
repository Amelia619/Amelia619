package testInterface.test_V1;


import org.testng.annotations.DataProvider;

public class login_v11 extends BaseProcessor_2 {
	//���ļ̳�BaseProcessor_2 ��cellNames�����Ԥ�ڽ���ֶΣ�ʵ����Ӧ����
	
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params","CaseId","ExpectedResponseData"};
		Object[][] datas= CaseUtil_2.getCaseDataByApiId("1", cellNames);
		return datas;

	}
 	
     
}
