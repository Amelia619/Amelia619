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
		  List<Variable> list=ExcelUtil.load(propertiesUtil.getExcelPath(),"����",Variable.class);
	      variables.addAll(list);
	      loadVariableToMap();
	  	ExcelUtil.loadRowIndexCellIndexMapping(propertiesUtil.getExcelPath(),"����");
	  }
	  public static String replacesVariable(String parameter) {
		  Set<String>  variableNames=VariableNameAndValues.keySet();
		  for (String variableName : variableNames) {
			if(parameter.contains(variableName)) {
				//����ʹ��replaceAll��������Ϊ���ĵ�һ��������������ʽ
				parameter=parameter.replace(variableName,VariableNameAndValues.get(variableName));
			}
		}
		  return parameter;
	  }
	  
	  //�����������ϣ����������ͱ�����Ӧ�ı���ֵ���浽map��
	  public static void loadVariableToMap() {
		  for (Variable variable : variables) {
			//��ȡ������
			String variableName=variable.getName();
			//��ȡ������ֵ
			String variableValue=variable.getValue();
			//���valueֵΪ��
			if(variableValue==null||variableValue.trim().length()==0) {
				//Ҫ�������
				String reflectClass=variable.getReflectClass();
				//Ҫ������õķ���
				String reflectMethod=variable.getReflectMethod();
				try {
					//ͨ�������ȡ�����ֽ���clazz
					Class clazz=Class.forName(reflectClass);
					//ͨ�����䴴������
					Object obj=clazz.newInstance();
					//��ȡҪ������õķ�������method
					Method method=clazz.getMethod(reflectMethod);
					//������÷�������ȡ�������ķ���ֵ
					variableValue=(String) method.invoke(obj);
					ExcelUtil.writeBackDatas.add(new WriteBackData("����", variableName, "ReflectValues", variableValue));	
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}								
			}
			VariableNameAndValues.put(variableName, variableValue);
		}
	  }
	  
}
