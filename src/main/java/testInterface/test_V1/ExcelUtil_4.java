package testInterface.test_V1;

import java.io.File;
import java.lang.reflect.Method;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil_4 {
	//��3�Ļ����ϸ���load������CaseUtil�İ汾ΪCaseUtil_2��
	//�޸�load����ʹ��ɼ��ؽӿ���Ϣ��
  public static Object[][] getDatas(String excelPath,String Sheetname ,int[] rowNum,int[] cellNum){
	  
	  //�½���ά����洢��ȡ�����ݣ���ά���鴴����object[][] datas=new Object[7][2];
	  Object[][] datas=null;
	  try {
		//��ȡworkbook����
		Workbook workbook=WorkbookFactory.create(new File(excelPath));
		//��ȡSheet����
		Sheet sheet=workbook.getSheet(Sheetname);
		datas=new Object[rowNum.length][cellNum.length];
		for (int i =0; i <rowNum.length; i++) {//ע��ȥ��=
			//��ȡ��
			Row row=sheet.getRow(rowNum[i]);//��ȡ���������е�������
			for (int j =0; j <cellNum.length; j++) {
			//��ȡ�У���ָ����Ԫ������Ϊ�յĲ��ԣ�����Ԫ��Ϊ��ʱ���ᱨ��ָ���쳣��
			Cell cell=row.getCell(cellNum[j],MissingCellPolicy.CREATE_NULL_AS_BLANK);
			//��������Ϊ�ַ�������
			cell.setCellType(CellType.STRING);
			//��ȡ��Ԫ������
			String value=cell.getStringCellValue();
			//����Ԫ�����ݴ洢��������
			datas[i][j]=value;			
			}	
		}	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return datas;  
  }
  
  public static void load(String excelPath,String sheetName,Class clazz) {
	  
	  try {
		Workbook workbook=WorkbookFactory.create(new File(excelPath));
		Sheet sheet=workbook.getSheet(sheetName);
		//��ȡ��һ�еı���
		Row rowtitle=sheet.getRow(0);
		int lastCellNum=rowtitle.getLastCellNum(); //��ȡ���������ݵ��ܵ�����
		String[] fild=new String[lastCellNum];
		for(int  i=0;i<lastCellNum;i++) {
			Cell cell=rowtitle.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String title=cell.getStringCellValue();
			title=title.substring(0,title.indexOf("("));
			fild[i]=title;
		}
		int lastRowNum=sheet.getLastRowNum();//��getLastCellNum��ͬ����ȡ�������һ�е�������ѭ��ʱ��Ҫ��=
		for(int i=1;i<=lastRowNum;i++) {
			Object obj= clazz.newInstance();
			Row datarow=sheet.getRow(i);
			if(datarow==null||isEmpty(datarow)) {
				continue;
			}
			for(int j=0;j<lastCellNum;j++) {
			Cell cell=datarow.getCell(j, MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String value=cell.getStringCellValue();
			String methodName="set"+fild[j];
			Method method=clazz.getMethod(methodName, String.class);
			method.invoke(obj,value);
			}
			if(obj instanceof Case) { //instanceof:�ж϶����Ƿ����ض���
			Case cs=(Case) obj;
			CaseUtil_2.cases.add(cs);
			}else if(obj instanceof Rest) { 
				Rest rest=(Rest) obj;
				RestUtil_1.rests.add(rest);
			}
		}
	} catch (Exception e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}	  
  }

private static boolean isEmpty(Row datarow) {
	int lastCellNum=datarow.getLastCellNum();
	for(int  i=0;i<lastCellNum;i++) {
		Cell cell=datarow.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
		cell.setCellType(CellType.STRING);
		String value=cell.getStringCellValue();
        if(value!=null&&value.trim().length()>0) {
        	return false;
        }
	}
	return true;
}
  
  //��֤��ȡ�����Ƿ���Գɹ���ȡ��
	/*
	  public static void main(String[] args){
		  String excelPath="src/test/resources/��������_V1.xlsx";
		  int[] rowNum= {1,2,3,4,5,6,7};
		  int[] cellNum= {5,6};
	  Object[][] data=getDatas(excelPath,rowNum,cellNum);
	   for(Object[] objects : data) {
	   // System.out.print("["+objects+"]\n");
	     for (Object object : objects) { 
	     System.out.print("["+object+"]\n");
	     
	     }
	    }
	 }
	*/
}
