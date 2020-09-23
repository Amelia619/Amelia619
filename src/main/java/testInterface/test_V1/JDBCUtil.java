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
        		//1、根据数据信息，获取数据库连接（连接数据库）
        		Connection con=getConnection();
        		//2、获取PreparedStatement对象，此类对象有提供数据库操作方法
				PreparedStatement ps=con.prepareStatement(sql);
				//3、设置条件字段的值（实时绑定）
				for (int i = 0; i < values.length; i++) {
					ps.setObject(i+1, values[i]);
				}
				//4、调用查询方法执行查询，返回ResultSet（结果集）
				ResultSet resultSet=ps.executeQuery();
				//获取查询相关信息
				ResultSetMetaData MetaData=resultSet.getMetaData();
				//得到查询字段的数目
				int columnNum=MetaData.getColumnCount();
				columnLableAndValues = new HashMap<String,Object> ();
				//5、从结果集取查询数据
				while(resultSet.next()) {
					//循环取出每个查询字段的数据
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
        //验证是否能连接上数据库
        public static void main(String[] args) throws SQLException, ClassNotFoundException {
        	
        	String url=properties.getProperty("jdbc.url");
        	String user=properties.getProperty("jdbc.username");
        	String password=properties.getProperty("jdbc.password");
        	System.out.println(url+"\n"+user+"\n"+password);
        	
        	String sql="select max(id) as maxid from  sc_login_logs";
        	System.out.println();
        	Connection conn = DriverManager.getConnection(url, user, password);
            // 创建一个 Statment对象
            Statement stmt = conn.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println(rs);
            while(rs.next()) {
        	System.out.println(rs.getString("maxid"));
            }
		}
	*/
}
