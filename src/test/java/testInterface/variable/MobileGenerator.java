package testInterface.variable;

import java.util.Map;

import testInterface.utils.JDBCUtil;

public class MobileGenerator {
	
    /**����ϵͳ�д��ڵ�����ֻ���
     * @return
     */
    public  String  generateRightMobile() {
    	String sql="select concat(max(mobile_phone),'') as rightMobile from  sc_user";
    	Map<String,Object> columnLableAndValues=JDBCUtil.query(sql);
    	return  columnLableAndValues.get("rightMobile").toString();
    }
    
    /**����ϵͳ�в����ڵ��ֻ���
     * @return
     */ 
    public  String  generateNotRegisterMobile() {
    	String sql="select concat(max(mobile_phone)+1,'') as notRegisterMobile from  sc_user";
    	Map<String,Object> columnLableAndValues=JDBCUtil.query(sql);
    	return  columnLableAndValues.get("notRegisterMobile").toString();
    }
}
