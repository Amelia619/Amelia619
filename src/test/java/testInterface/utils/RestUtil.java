package testInterface.utils;

import java.util.ArrayList;
import java.util.List;

import testInterface.pojo.Rest;

public class RestUtil {
	public static List<Rest> rests=new ArrayList<Rest> ();
	static {
		  List<Rest> list=ExcelUtil.load(propertiesUtil.getExcelPath(),"接口信息",Rest.class);
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
