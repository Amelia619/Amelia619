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
	//在3的基础上更换load函数中CaseUtil的版本为CaseUtil_2。
	//修改load函数使其可加载接口信息表单
  public static Object[][] getDatas(String excelPath,String Sheetname ,int[] rowNum,int[] cellNum){
	  
	  //新建二维数组存储读取的数据，二维数组创建：object[][] datas=new Object[7][2];
	  Object[][] datas=null;
	  try {
		//获取workbook对象
		Workbook workbook=WorkbookFactory.create(new File(excelPath));
		//获取Sheet对象
		Sheet sheet=workbook.getSheet(Sheetname);
		datas=new Object[rowNum.length][cellNum.length];
		for (int i =0; i <rowNum.length; i++) {//注意去掉=
			//获取行
			Row row=sheet.getRow(rowNum[i]);//获取的是数组中的行索引
			for (int j =0; j <cellNum.length; j++) {
			//获取列，并指定单元格数据为空的策略，否则单元格为空时，会报空指针异常。
			Cell cell=row.getCell(cellNum[j],MissingCellPolicy.CREATE_NULL_AS_BLANK);
			//将列设置为字符串类型
			cell.setCellType(CellType.STRING);
			//获取单元格数据
			String value=cell.getStringCellValue();
			//将单元格数据存储到数组中
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
		//获取第一列的标题
		Row rowtitle=sheet.getRow(0);
		int lastCellNum=rowtitle.getLastCellNum(); //获取的是有数据的总的列数
		String[] fild=new String[lastCellNum];
		for(int  i=0;i<lastCellNum;i++) {
			Cell cell=rowtitle.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
			cell.setCellType(CellType.STRING);
			String title=cell.getStringCellValue();
			title=title.substring(0,title.indexOf("("));
			fild[i]=title;
		}
		int lastRowNum=sheet.getLastRowNum();//与getLastCellNum不同，获取的是最后一行的索引，循环时需要加=
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
			if(obj instanceof Case) { //instanceof:判断对象是否是特定类
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
  
  //验证读取函数是否可以成功读取。
	/*
	  public static void main(String[] args){
		  String excelPath="src/test/resources/测试用例_V1.xlsx";
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
