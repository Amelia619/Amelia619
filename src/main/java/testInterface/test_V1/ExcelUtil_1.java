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
	  String excelPath="src/test/resources/测试用例_V1.xlsx";
	  //新建二维数组存储读取的数据，二维数组创建：object[][] datas=new Object[7][2];
	  Object[][] datas=null;
	  try {
		//获取workbook对象
		Workbook workbook=WorkbookFactory.create(new File(excelPath));
		//获取Sheet对象
		Sheet sheet=workbook.getSheet("Sheet1");
		datas=new Object[7][2];
		for (int i =1; i <=7; i++) {
			//获取行
			Row row=sheet.getRow(i);
			for (int j = 5; j <=6; j++) {
			//获取列，并指定单元格数据为空的策略，否则单元格为空时，会报空指针异常。
			Cell cell=row.getCell(j,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			//将列设置为字符串类型
			cell.setCellType(CellType.STRING);
			//获取单元格数据
			String value=cell.getStringCellValue();
			//将单元格数据存储到数组中
			datas[i-1][j-5]=value;			
			}	
		}	
	} catch (Exception e) {
		e.printStackTrace();
	}
	return datas;  
  }
  
  //验证读取函数是否可以成功读取。
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
