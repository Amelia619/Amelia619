package testInterface.test_V1;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class JDBCUtil {
       private static Properties properties=new Properties();
       static {
    	   InputStream inputstream;	   
    	   try {
			inputstream=new FileInputStream(new File("src/test/resources/jdbc.properties"));
			properties.load(inputstream);
		} catch (Exception e) {
			e.printStackTrace();
		}
    	   
       }
        public static Map<String, Object> query(String sql,Object ...values) {
        	Map<String, Object> columnLableAndValues=null;
        	try {
        		//1������������Ϣ����ȡ���ݿ����ӣ��������ݿ⣩
        		Connection con=getConnection();
        		//2����ȡPreparedStatement���󣬴���������ṩ���ݿ��������
				PreparedStatement ps=con.prepareStatement(sql);
				//3�����������ֶε�ֵ��ʵʱ�󶨣�
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i+1, values[i]);
				}
				//4�����ò�ѯ����ִ�в�ѯ������ResultSet���������
				ResultSet resultSet=ps.executeQuery();
				//��ȡ��ѯ�����Ϣ
				ResultSetMetaData MetaData=resultSet.getMetaData();
				//�õ���ѯ�ֶε���Ŀ
				int columnNum=MetaData.getColumnCount();
				columnLableAndValues = new HashMap<String,Object> ();
				//5���ӽ����ȡ��ѯ����
				while(resultSet.next()) {
					//ѭ��ȡ��ÿ����ѯ�ֶε�����
					for (int i = 1; i <= columnNum; i++) {
						String columnLable=MetaData.getColumnLabel(i);
						String columnValue=resultSet.getObject(columnLable).toString();
						System.out.println(columnLable+columnValue);
						columnLableAndValues.put(columnLable, columnValue);
					}	
				}	
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return columnLableAndValues;
        }
        
        public static Connection getConnection() {
        	String url=properties.getProperty("jdbc.url");
        	String user=properties.getProperty("jdbc.username");
        	String password=properties.getProperty("jdbc.password");
        	Connection cn=null;
        	try {
				 cn=DriverManager.getConnection(url, user, password);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
        	return cn;
        }
        
    /*
        //��֤�Ƿ������������ݿ�
        public static void main(String[] args) throws SQLException, ClassNotFoundException {
        	
        	String url=properties.getProperty("jdbc.url");
        	String user=properties.getProperty("jdbc.username");
        	String password=properties.getProperty("jdbc.password");
        	System.out.println(url+"\n"+user+"\n"+password);
        	
        	String sql="select max(id) as maxid from  sc_login_logs";
        	System.out.println();
        	Connection conn = DriverManager.getConnection(url, user, password);
            // ����һ�� Statment����
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);
            while(rs.next()) {
        	System.out.println(rs.getString("maxid"));
            }
		}
	*/
}
