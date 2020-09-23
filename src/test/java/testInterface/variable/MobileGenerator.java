package testInterface.variable;

import java.util.Map;

import testInterface.utils.JDBCUtil;

public class MobileGenerator {
	
    /**生成系统中存在的最大手机号
     * @return
     */
    public  String  generateRightMobile() {
    	String sql="select concat(max(mobile_phone),'') as rightMobile from  sc_user";
    	Map<String,Object> columnLableAndValues=JDBCUtil.query(sql);
    	return  columnLableAndValues.get("rightMobile").toString();
    }
    
    /**生成系统中不存在的手机号
     * @return
     */ 
    public  String  generateNotRegisterMobile() {
    	String sql="select concat(max(mobile_phone)+1,'') as notRegisterMobile from  sc_user";
    	Map<String,Object> columnLableAndValues=JDBCUtil.query(sql);
    	return  columnLableAndValues.get("notRegisterMobile").toString();
    }
}
