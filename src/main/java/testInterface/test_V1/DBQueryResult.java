package testInterface.test_V1;

import java.util.Map;

public class DBQueryResult {
    private String getNo;
    private Map<String,Object> columnLableAndValues;
	public String getGetNo() {
		return getNo;
	}
	public void setGetNo(String getNo) {
		this.getNo = getNo;
	}
	public Map<String, Object> getColumnLableAndValues() {
		return columnLableAndValues;
	}
	public void setColumnLableAndValues(Map<String, Object> columnLableAndValues) {
		this.columnLableAndValues = columnLableAndValues;
	}
    
}
