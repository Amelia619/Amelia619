package testInterface.cases;


import org.testng.annotations.DataProvider;

import testInterface.utils.CaseUtil;

public class loginCase extends BaseProcessor {
	//���ķֱ�̳�BaseProcessor_3\4\5��cellNames����Ӵ洢sql�����ֶΣ�ʵ�����ݿ��ѯ
	//��ѯ���ݿ⣬�ԱȲ�ѯǰ��ı仯
	@DataProvider
	public Object[][] datas(){
		Object[][] datas= CaseUtil.getCaseDataByApiId("1", cellNames);
		return datas;
	}
}
