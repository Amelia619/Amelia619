package testInterface.utils;


import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import testInterface.pojo.Case;

public class CaseUtil {
	//在1的版本上修改getCaseDataByApiId函数，使用反射方法替代字段项参数判断，降低耦合性。
  public static List<Case> cases=new ArrayList<Case> ();
  static {
//	  ExcelUtil_4.load("src/test/resources/测试用例_V2.xlsx","用例",Case.class);
//	  ExcelUtil_5.load("src/test/resources/测试用例_V2.xlsx","用例",Case.class);
	  List<Case> list=ExcelUtil.load(propertiesUtil.getExcelPath(),"用例",Case.class);
	  cases.addAll(list);
  }
  public static Object[][] getCaseDataByApiId(String ApiId,String[] cellNames){
	  Class<Case> claz=Case.class;
	  ArrayList<Case> csList=new ArrayList<Case> ();
	  for(Case cs:cases) {
		  if(cs.getApiId().equalsIgnoreCase(ApiId)) {
			  csList.add(cs);
		  }
	  }
	  Object[][] caseDatas=new Object[csList.size()][cellNames.length];
	  for(int i=0; i< csList.size();i++) {
		  Case cs=csList.get(i);
		  for(int j=0;j<cellNames.length;j++) {
			  String methodName="get"+cellNames[j];
			  try {
				  Method method=claz.getMethod(methodName);
				 String value= (String) method.invoke(cs);
				  caseDatas[i][j]=value; 
			  }catch(Exception e) {
				  e.printStackTrace();
			  }
		  }
	  }
	  return caseDatas;
    }
/*
  public static void main(String[] args)  {
	  String cellNames[]= {"ApiId","Params","CaseId","ExpectedResponseData"};
		Object[][] datas= getCaseDataByApiId("4", cellNames);
		   for(Object[] objects : datas) {
		   // System.out.print("["+objects"]\n");
		     for (Object object : objects) { 
		     System.out.print("["+object+"]");
		   }
		     System.out.print("\n");
		 }
  }
*/
}
