package testInterface.test_V1;

import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil_1 {
  public static Object[][] getDatas(){
	  String excelPath="src/test/resources/��������_V1.xlsx";
	  //�½���ά����洢��ȡ�����ݣ���ά���鴴����object[][] datas=new Object[7][2];
	  Object[][] datas=null;
	  try {
		//��ȡworkbook����
		Workbook workbook=WorkbookFactory.create(new File(excelPath));
		//��ȡSheet����
		Sheet sheet=workbook.getSheet("Sheet1");
		datas=new Object[7][2];
		for (int i =1; i <=7; i++) {
			//��ȡ��
			Row row=sheet.getRow(i);
			for (int j = 5; j <=6; j++) {
			//��ȡ�У���ָ����Ԫ������Ϊ�յĲ��ԣ�����Ԫ��Ϊ��ʱ���ᱨ��ָ���쳣��
			Cell cell=row.getCell(j,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			//��������Ϊ�ַ�������
			cell.setCellType(CellType.STRING);
			//��ȡ��Ԫ������
			String value=cell.getStringCellValue();
			//����Ԫ�����ݴ洢��������
			datas[i-1][j-5]=value;			
			}	
		}	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return datas;  
  }
  
  //��֤��ȡ�����Ƿ���Գɹ���ȡ��
	/*
	 * public static void main(String[] args) { 
	 * Object[][] data=getDatas();
	 *  for(Object[] objects : data) {
	 *   System.out.print("["+objects+"]\n");
	 *    for (Object object : objects) { 
	 *    System.out.print("["+object+"]\n");
	 *     }
	 *   }
	 * }
	 */
}
