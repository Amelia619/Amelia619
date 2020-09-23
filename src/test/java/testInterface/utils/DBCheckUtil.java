package testInterface.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.alibaba.fastjson.JSONObject;

import testInterface.pojo.DBCheck;
import testInterface.pojo.DBQueryResult;

public class DBCheckUtil {
   public static String doQuery(String PreValidateSql) {
	 List<DBCheck> dbCheckers=JSONObject.parseArray(PreValidateSql, DBCheck.class);
     List<DBQueryResult>  dbQueryResults=new ArrayList<DBQueryResult> ();
     for(DBCheck dbChecker:dbCheckers) {
    	 String no=dbChecker.getNo();
    	 String sql=dbChecker.getSql();
    	 Map<String,Object> columnLablesAndValues=JDBCUtil.query(sql);
    	 DBQueryResult dbQueryResult=new  DBQueryResult();
    	 dbQueryResult.setGetNo(no);
    	 dbQueryResult.setColumnLableAndValues(columnLablesAndValues);
    	 dbQueryResults.add(dbQueryResult);
     }
     return JSONObject.toJSONString(dbQueryResults);
   } 
   /*测试是否能读出数据
   public static void main(String[] args) {
	   String PreValidateSql="[{\"no\":\"1\",\"sql\":\"select max(id) from  sc_login_logs\"}]";
	   List<DBCheck> dbCheckers=JSONObject.parseArray(PreValidateSql, DBCheck.class);
	   for(DBCheck dbChecker:dbCheckers) {
	    System.out.println(dbChecker);
	   }
   }
 */
}
