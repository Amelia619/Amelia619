package testInterface.test_V1;


import org.testng.annotations.DataProvider;

public class login_v12 extends BaseProcessor_5 {
	//���ķֱ�̳�BaseProcessor_3\4\5��cellNames����Ӵ洢sql�����ֶΣ�ʵ�����ݿ��ѯ
	//��ѯ���ݿ⣬�ԱȲ�ѯǰ��ı仯
	@DataProvider
	public Object[][] datas(){
		String cellNames[]= {"ApiId","Params","CaseId","PreValidateSql","AfterValidateSql"};
		Object[][] datas= CaseUtil_2.getCaseDataByApiId("1", cellNames);
		return datas;
	}
}
