package testInterface.test_V1;

import java.util.ArrayList;
import java.util.List;

public class CaseUtil_1 {
  public static List<Case> cases=new ArrayList<Case> ();
  static {
	  ExcelUtil_3.load("src/test/resources/≤‚ ‘”√¿˝_V2.xlsx","”√¿˝");
  }
  public static Object[][] getCaseDataByApiId(String ApiId,String[] cellNames) {
	  ArrayList<Case> csList=new ArrayList<Case> ();
	  for(Case cs:cases) {
		  if(cs.getApiId().equalsIgnoreCase(ApiId)) {
			  csList.add(cs);
		  }
	  }
	  Object[][] caseDatas=new Object[csList.size()][cellNames.length];
	  String value="";
	  for(int i=0; i< csList.size();i++) {
		  Case cs=csList.get(i);
		  for(int j=0;j<cellNames.length;j++) {
			  String cellName=cellNames[j];
			  if(cellName.equalsIgnoreCase("CaseId")) {
				  value=cs.getCaseId();
			  }else if(cellName.equalsIgnoreCase("ApiId")) {
					  value=cs.getApiId();
			  }else if(cellName.equalsIgnoreCase("Desc")) {
				  value=cs.getDesc();
			  }else if(cellName.equalsIgnoreCase("Params")) {
				  value=cs.getParams();
			  }
			  caseDatas[i][j]=value;
		  }
	  }
	  return caseDatas;
    }
 /*
  public static void main(String[] args) {
	  String cellNames[]= {"ApiId","Params"};
		Object[][] datas= getCaseDataByApiId("1", cellNames);
		   for(Object[] objects : datas) {
		   // System.out.print("["+objects"]\n");
		     for (Object object : objects) { 
		     System.out.print("["+object+"]\n");
		   }
		 }
  }
 */
}
