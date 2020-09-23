package testInterface.test_V1;

import java.lang.reflect.Field;

public class reflectPractise {
	  //反射的使用
		 public static void main(String[] args) throws Exception {
			Class clazz = "Variable".getClass();
			Field[] fields=clazz.getFields();
			for (Field field : fields) {
			    System.out.println(field.getName());
			    
			}
		}
}
