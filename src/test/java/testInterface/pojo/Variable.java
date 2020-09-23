package testInterface.pojo;

public class Variable {
     private String Name;
     private String Value;
     private String Remarks;
     private String ReflectClass;
     private String ReflectMethod;
     private String ReflectValues;
     
	public String getReflectClass() {
		return ReflectClass;
	}
	public void setReflectClass(String reflectClass) {
		ReflectClass = reflectClass;
	}
	public String getReflectMethod() {
		return ReflectMethod;
	}
	public void setReflectMethod(String reflectMethod) {
		ReflectMethod = reflectMethod;
	}
	public String getReflectValues() {
		return ReflectValues;
	}
	public void setReflectValues(String reflectValues) {
		ReflectValues = reflectValues;
	}
	public String getName() {
		return Name;
	}
	public void setName(String name) {
		Name = name;
	}
	public String getValue() {
		return Value;
	}
	public void setValue(String value) {
		Value = value;
	}
	public String getRemarks() {
		return Remarks;
	}
	public void setRemarks(String remarks) {
		Remarks = remarks;
	}
     
}
