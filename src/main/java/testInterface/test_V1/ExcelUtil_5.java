package testInterface.test_V1;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;



import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Row.MissingCellPolicy;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.ss.usermodel.WorkbookFactory;

public class ExcelUtil_5 {
	//在4的基础上删除getDatas函数，增加caseId行索引获取和cellName列索引获取，以及回写函数
	public static Map<String,Integer> CaseIdRowIndexMapping=new HashMap<String,Integer> ();
	public static Map<String,Integer> CellNameCellIndexMapping=new HashMap<String,Integer> ();
	public static List<WriteBackData> writeBackDatas =new ArrayList<WriteBackData> ();
	static {
		loadRowIndexCellIndexMapping("src/test/resources/测试用例_V2.xlsx","用例");

	}
	//获取CaseId对应的行索引，获取cellName对应的列索引。
  public static void loadRowIndexCellIndexMapping(String excelPath,String Sheetname ){
	 
	  InputStream inputStream=null;
	  try {
			inputStream=new FileInputStream(new File(excelPath));
			Workbook workbook=WorkbookFactory.create(inputStream);
			Sheet sheet=workbook.getSheet(Sheetname);
			Row rowtitle=sheet.getRow(0);
        	if(rowtitle!=null&&!isEmpty(rowtitle)) {
				int lastCellNum=rowtitle.getLastCellNum(); //获取的是有数据的总的列数
				for(int  i=0;i<lastCellNum;i++) {
					Cell cell=rowtitle.getCell(i,MissingCellPolicy.CREATE_NULL_AS_BLANK);
					cell.setCellType(CellType.STRING);
					String title=cell.getStringCellValue();
					title=title.substring(0,title.indexOf("("));
					int cellIndex=cell.getAddress().getColumn();
					CellNameCellIndexMapping.put(title,cellIndex);
			}
			//从第二行开始获取所有数据行
			int lastRowNum=sheet.getLastRowNum();//与getLastCellNum不同，获取的是最后一行的索引，循环时需要加=
			//循环拿到每一个数据行
			for(int i=1;i<=lastRowNum;i++) {
				Row datarow=sheet.getRow(i);
			    int rowIndex=datarow.getRowNum();
				Cell firstCellOfRow=datarow.getCell(0, MissingCellPolicy.CREATE_NULL_AS_BLANK);
				firstCellOfRow.setCellType(CellType.STRING);
				String CaseId=firstCellOfRow.getStringCellValue();
				CaseIdRowIndexMapping.put(CaseId, rowIndex);
	      }
		}
	} catch (Exception e) {
		e.printStackTrace();
	}finally {
		try {
			if(inputStream!=null) {
					inputStream.close();
				}
		}catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
		}
	}
  }
 
  //回写测试结果

   public static void writeBack(String excelPath,String Sheetname,String CaseId,String cellName,String result) {
	   InputStream inputStream=null;
	   OutputStream outputStream=null;
		  try {
				inputStream=new FileInputStream(new File(excelPath));
				Workbook workbook=WorkbookFactory.create(inputStream);
				Sheet sheet=workbook.getSheet(Sheetname);
				int rowIndex=CaseIdRowIndexMapping.get(CaseId);
				System.out.print(rowIndex);
				int cellIndex=CellNameCellIndexMapping.get(cellName);
				Row rowtitle=sheet.getRow(rowIndex);
				Cell cell=rowtitle.getCell(cellIndex,MissingCellPolicy.CREATE_NULL_AS_BLANK);
				cell.setCellType(CellType.STRING);
				cell.setCellValue(result);
                outputStream= new FileOutputStream(excelPath);
				workbook.write(outputStream);

		} catch (Exception e) {
			e.printStackTrace();
		}finally {
			try {
				if(inputStream!=null) {
						inputStream.close();
					}
				if(outputStream!=null) {
					outputStream.close();
				}
			}catch (IOException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
			}
		}
   }
  //一次性加载表格中的所有数据
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
  public static void main(String[] args) {

	  Iterator<String> it=CellNameCellIndexMapping.keySet().iterator();//等价于上面两句代码。
      while(it.hasNext()){
          Object key=it.next();
          int value=CellNameCellIndexMapping.get(key);
      	System.out.println("CellNameCellIndexMapping"+key+"---"+value);
      }
      Iterator<String> it1=CaseIdRowIndexMapping.keySet().iterator();//等价于上面两句代码。
      while(it1.hasNext()){
          Object key=it1.next();
          int value=CaseIdRowIndexMapping.get(key);
      	System.out.println("CaseIdRowIndexMapping遍历:"+key+"---"+value);
      }
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
