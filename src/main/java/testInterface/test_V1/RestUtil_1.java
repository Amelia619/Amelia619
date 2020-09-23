package testInterface.test_V1;

import java.util.ArrayList;
import java.util.List;

public class RestUtil_1 {
	public static List<Rest> rests=new ArrayList<Rest> ();
	static {
	//	  ExcelUtil_4.load("src/test/resources/��������_V2.xlsx","�ӿ���Ϣ",Rest.class);
	//	  ExcelUtil_5.load("src/test/resources/��������_V2.xlsx","�ӿ���Ϣ",Rest.class);
		  List<Rest> list=ExcelUtil_6.load(propertiesUtil.getExcelPath(),"�ӿ���Ϣ",Rest.class);
		  rests.addAll(list);
	  }
    public static String getUrlByApiId(String ApiId) {
     String url="";
  	  for(Rest res:rests) {
  		  if(res.getApiId().equalsIgnoreCase(ApiId)) {
  		 url= res.getUrl();
  		  }
  	  }
    	return url;
    }
    public static String getTypeByApiId(String ApiId) {
        String type="";
     	  for(Rest res:rests) {
     		  if(res.getApiId().equalsIgnoreCase(ApiId)) {
     		 type= res.getType();
     		  }
     	  }
       	return type;
       }
}
