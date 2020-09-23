package testInterface.pojo;

public class WriteBackData {
     String SheetName;
     String rowIdentifier;
     String cellName;
     String result;
    
	public WriteBackData(String sheetName, String rowidentifier, String cellName, String result) {
		super();
		SheetName = sheetName;
		rowIdentifier = rowidentifier;
		this.cellName = cellName;
		this.result = result;
	}
	public String getSheetName() {
		return SheetName;
	}
	public void setSheetName(String sheetName) {
		SheetName = sheetName;
	}

	public String getCellName() {
		return cellName;
	}
	public void setCellName(String cellName) {
		this.cellName = cellName;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getRowIdentifier() {
		return rowIdentifier;
	}
	public void setRowIdentifier(String rowIdentifier) {
		this.rowIdentifier = rowIdentifier;
	}
	
}
