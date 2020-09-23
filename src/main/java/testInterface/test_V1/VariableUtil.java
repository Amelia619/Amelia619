package testInterface.test_V1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class VariableUtil {
	 public static Map<String,String> VariableNameAndValues=new HashMap<String,String>();
	 public static List<Variable> variables=new ArrayList<Variable> ();
	  static {
		  List<Variable> list=ExcelUtil_6.load(propertiesUtil.getExcelPath(),"����",Variable.class);
	      variables.addAll(list);
	      loadVariableToMap();
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
			String variableName=variable.getName();
			String variableValue=variable.getValue();
			VariableNameAndValues.put(variableName, variableValue);
		}
	  }
}
