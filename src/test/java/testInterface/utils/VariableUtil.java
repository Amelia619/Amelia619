package testInterface.utils;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import testInterface.pojo.Variable;
import testInterface.pojo.WriteBackData;

public class VariableUtil {
	 public static Map<String,String> VariableNameAndValues=new HashMap<String,String>();
	 public static List<Variable> variables=new ArrayList<Variable> ();
	  static {
		  List<Variable> list=ExcelUtil.load(propertiesUtil.getExcelPath(),"变量",Variable.class);
	      variables.addAll(list);
	      loadVariableToMap();
	  	ExcelUtil.loadRowIndexCellIndexMapping(propertiesUtil.getExcelPath(),"变量");
	  }
	  public static String replacesVariable(String parameter) {
		  Set<String>  variableNames=VariableNameAndValues.keySet();
		  for (String variableName : variableNames) {
			if(parameter.contains(variableName)) {
				//不能使用replaceAll方法，因为它的第一个参数是正则表达式
				parameter=parameter.replace(variableName,VariableNameAndValues.get(variableName));
			}
		}
		  return parameter;
	  }
	  
	  //遍历变量集合，将变量名和变量对应的变量值保存到map中
	  public static void loadVariableToMap() {
		  for (Variable variable : variables) {
			//获取变量名
			String variableName=variable.getName();
			//获取变量的值
			String variableValue=variable.getValue();
			//如果value值为空
			if(variableValue==null||variableValue.trim().length()==0) {
				//要反射的类
				String reflectClass=variable.getReflectClass();
				//要反射调用的方法
				String reflectMethod=variable.getReflectMethod();
				try {
					//通过反射获取类型字节码clazz
					Class clazz=Class.forName(reflectClass);
					//通过反射创建对象
					Object obj=clazz.newInstance();
					//获取要反射调用的方法对象method
					Method method=clazz.getMethod(reflectMethod);
					//反射调用方法，获取到方法的返回值
					variableValue=(String) method.invoke(obj);
					ExcelUtil.writeBackDatas.add(new WriteBackData("变量", variableName, "ReflectValues", variableValue));	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}								
			}
			VariableNameAndValues.put(variableName, variableValue);
		}
	  }
	  
}
