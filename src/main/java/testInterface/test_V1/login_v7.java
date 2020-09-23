package testInterface.test_V1;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class login_v7 {
	//����ExcelUtil_3,ʹ��JSONObject.parseObject���������ݸ�ʽΪJson��ʽ����Ҫ��ExcelUtil_3�еı�����ƻ���Sheet2
	@Test(dataProvider="datas")
     public void login(String parameters) {
		String url="http://114.135.61.188:58088/api/user/smsCodeLogin";
		Map<String,String> parameter=new HashMap<String,String> ();
		//��json��ʽ����ת��Ϊjava����
		LoginParameter loginPara = JSON.parseObject(parameters, LoginParameter.class);
			 //��java������ȡ����������====�ֻ��ź�����
			String mobile = loginPara.getMobile().toString();
			String smsCode = loginPara.getSmsCode().toString();
			//��ȡ���Ĳ���������ӵ�map������
		    parameter.put("mobile",mobile);
		    parameter.put("smsCode",smsCode);
		   //����map���ϣ��жϲ��������Ƿ��Ѿ���ӵ�map��
			Set<String> pa=parameter.keySet();
			for (String pas : pa) {
				System.out.println(pas+"  "+parameter.get(pas));
			}
			//�����Ӧ����
    	System.out.println(HttpUtil2.doPost(url,parameter)); 
     }
	
	@DataProvider
	public Object[][] datas(){
		String excelPath="src/test/resources/��������_V1.xlsx";
		int rowNum[]= {1,2,3,4,5,6,7};
		int cellNum[]= {5};
		Object[][] datas= ExcelUtil_3.getDatas(excelPath,"Sheet2",rowNum,cellNum);
		return datas;
	}
}
