package testInterface.test_V1;

import java.io.File;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil_2 {
	//��ȡ�������б���ļ������뿪ʼ����������
  public static Object[][] getDatas(String excelPath,int rowStartNum,int rowEndNum,int cellStartNum,int cellEndNum){
	  
	  //�½���ά����洢��ȡ�����ݣ���ά���鴴����object[][] datas=new Object[7][2];
	  Object[][] datas=null;
	  try {
		//��ȡworkbook����
		Workbook workbook=WorkbookFactory.create(new File(excelPath));
		//��ȡSheet����
		Sheet sheet=workbook.getSheet("Sheet1");
		datas=new Object[rowEndNum-rowStartNum+1][cellEndNum-cellStartNum+1];
		for (int i =rowStartNum; i <=rowEndNum; i++) {
			//������������ȡ��
			Row row=sheet.getRow(i); //�����д����������ֵ �����Դ˴�����Ҫ��1
			for (int j =cellStartNum; j <=cellEndNum; j++) {
			//������������ȡ�У���ָ����Ԫ������Ϊ�յĲ��ԣ�����Ԫ��Ϊ��ʱ���ᱨ��ָ���쳣��
			Cell cell=row.getCell(j,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			//��������Ϊ�ַ�������
			cell.setCellType(CellType.STRING);
			//��ȡ��Ԫ������
			String value=cell.getStringCellValue();
			//����Ԫ�����ݴ洢��������
			datas[i-rowStartNum][j-cellStartNum]=value;			
			}	
		}	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return datas;  
  }
  
  //��֤��ȡ�����Ƿ���Գɹ���ȡ��
	/*
	  public static void main(String[] args){ 
	  String excelPath="src/test/resources/��������_V1.xlsx";
	  Object[][] data=ExcelUtil_2.getDatas(excelPath,1,7,5,6);
	   for(Object[] objects : data) {
	   // System.out.print("["+objects+"]\n");
	     for (Object object : objects) { 
	     System.out.print("["+object+"]\n");
	     
	     }
	    }
	 }
	 */
}
