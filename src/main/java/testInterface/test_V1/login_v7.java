package testInterface.test_V1;


import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;

public class login_v7 {
	//调用ExcelUtil_3,使用JSONObject.parseObject处理传入数据格式为Json形式，需要将ExcelUtil_3中的表格名称换成Sheet2
	@Test(dataProvider="datas")
     public void login(String parameters) {
		String url="http://114.135.61.188:58088/api/user/smsCodeLogin";
		Map<String,String> parameter=new HashMap<String,String> ();
		//将json格式数据转换为java对象
		LoginParameter loginPara = JSON.parseObject(parameters, LoginParameter.class);
			 //从java对象中取出参数数据====手机号和密码
			String mobile = loginPara.getMobile().toString();
			String smsCode = loginPara.getSmsCode().toString();
			//将取出的参数数据添加到map集合中
		    parameter.put("mobile",mobile);
		    parameter.put("smsCode",smsCode);
		   //遍历map集合，判断参数数据是否已经添加到map中
			Set<String> pa=parameter.keySet();
			for (String pas : pa) {
				System.out.println(pas+"  "+parameter.get(pas));
			}
			//输出响应数据
    	System.out.println(HttpUtil2.doPost(url,parameter)); 
     }
	
	@DataProvider
	public Object[][] datas(){
		String excelPath="src/test/resources/测试用例_V1.xlsx";
		int rowNum[]= {1,2,3,4,5,6,7};
		int cellNum[]= {5};
		Object[][] datas= ExcelUtil_3.getDatas(excelPath,"Sheet2",rowNum,cellNum);
		return datas;
	}
}
