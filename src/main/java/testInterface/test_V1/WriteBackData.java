package testInterface.test_V1;

public class WriteBackData {
     String SheetName;
     String CaseId;
     String cellName;
     String result;
    
	public WriteBackData(String sheetName, String caseId, String cellName, String result) {
		super();
		SheetName = sheetName;
		CaseId = caseId;
		this.cellName = cellName;
		this.result = result;
	}
	public String getSheetName() {
		return SheetName;
	}
	public void setSheetName(String sheetName) {
		SheetName = sheetName;
	}
	public String getCaseId() {
		return CaseId;
	}
	public void setCaseId(String caseId) {
		CaseId = caseId;
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
}
