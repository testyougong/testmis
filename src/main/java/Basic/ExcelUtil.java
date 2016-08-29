package Basic;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;;

public class ExcelUtil {

	 //本类主要实现文件扩展为.xlsx的Excel文件操作
	private static XSSFSheet ExcelWSheet;
	private static XSSFWorkbook ExcelWBook;
	private static XSSFCell Cell;
	private static XSSFRow Row;
	//设定要操作的文件路径和Excel文件中的sheet名称
	//在读写Excel的时候，均需要先调用此方法，设定要操作的Excel文件路径和要操作的sheet名称
	public static void setExcelFile(String Path,String SheetName)throws Exception{
		
		FileInputStream ExcelFile;
		try{
			//实例化Excel文件的FileInputStream对象
			ExcelFile=new FileInputStream(Path);
			//实例化Excel文件的XSSFWorkbook对象
			ExcelWBook=new XSSFWorkbook(ExcelFile);
			/*
			 * 实例化XSSFSheet对象，指定Excel文件中的sheet名称，后续用于sheet中行、列和单元格的操作
			 * */
			ExcelWSheet =ExcelWBook.getSheet(SheetName);
		}catch(Exception e){
			throw e;
		}
		
		
		}
	//读取Excel文件指定单元格函数
	public static String getCellData(int RowNum,int ColNum)throws Exception{
		try{
			//通过函数参数指定单元格的行号和列号，获取指定单元格对象
			Cell=ExcelWSheet.getRow(RowNum).getCell(ColNum);
			/*
			 * 如更单元格的内容为字符串类型，则使用getStringCellValue方法获取单元格的内容
			 * 如果单元格的内容为数字类型，则使用getNumberCellValue()方法获取单元格内容
			 * 注意getNumberCellValue方法返回值为double类型，转换字符串类型必须在Cell.getNumberValue()
			 * 前面加“”，用于强制double类型到String类型，不加“”则抛出double类型无法转换到String类型的异常*/
			String CellData=Cell.getCellType()==XSSFCell.CELL_TYPE_STRING?Cell.getStringCellValue()+""
					:String.valueOf(Math.round(Cell.getNumericCellValue()));
			return CellData;
		}catch(Exception e){
			return "";
		}
	}
	
	//在Excel文件的执行单元格中写入数据
	@SuppressWarnings("static-access")
	public static void setCellData(int RowNum,int ColNum,String Result)throws Exception{
		try {
			//获取Excel文件中 的行对象
			Row=ExcelWSheet.getRow(RowNum);
			//如果单元格为空，则返回null
			Cell=Row.getCell(ColNum,Row.RETURN_BLANK_AS_NULL);
			if (Cell==null) {
				//当单元格对象时null的时候，则创建单元格
				//如果单元格为空，无法直接用单元格对象的setCellValue方法设定单元格的值
				//创建单元格号可以调用setCellValue方法设定单元格的值
				Cell.setCellValue(Result);
				
				
			}
			else{
				//单元格中有内容，则可以直接调用单元格对象的setCellValue方法设定单元格的值
				Cell.setCellValue(Result);
				
			}
			//实例化写入Excel文件中的文件输入流对象
			FileOutputStream fileOut=new FileOutputStream(
					Constant.TestDataExcelFilePath);
			//将内容写入Excel文件中
			ExcelWBook.write(fileOut);
			//调用flush方法强制刷新写入文件
			fileOut.flush();
			//关闭文件输出流对象
			fileOut.close();
		} catch (Exception e) {
			throw e;

		}
	}
	//从Excel文件获取测试数据的静态方法
	public static Object[][] getTestData(String excelFilePath,String sheetName) throws IOException{
		//根据参数传入的数据文件路径和文件名称，组合出Excel数据文件的绝对路径
		//声明一个file对象
		File file=new File(excelFilePath);
		//创建FileInputStream 对象用于读取Excel文件
		FileInputStream inputStream=new FileInputStream(file);
		//声明Workbook 对象
		Workbook Workbook=null;
		//获取文件名参数的扩展名，判断是.xlsx文件还是xls文件
		String fileExtensionName=excelFilePath.substring(excelFilePath.indexOf(","));
		//判断文件类型如果是.xlsx，则使用XSSFWorkbook对象进行实例化
		//判断文件类型如果是.xls，则使用SSFWorkbook对象进行实例化
		if (fileExtensionName.equals(".xlsx")) {
			Workbook=new XSSFWorkbook(inputStream);
			
			
		}else if(fileExtensionName.equals(".xls")){
		Workbook=new HSSFWorkbook(inputStream);
		
		}
		//通过sheetName参数，生成sheet对象
		Sheet sheet=Workbook.getSheet(sheetName);
		//获取Excel数据文件sheet中的数据的行数，getLastRowNum方法获取数据的最后行号
		//getFirstRowNum方法获取数据的第一行行号，相减之后计算出数据的行数
		//注意：Excel文件的行号和列号都是从0开始
		int rowCount=sheet.getLastRowNum()-sheet.getFirstRowNum();
		//创建名为records的list对象来存储Excel数据文件读取的数据
		List<Object[]> records=new ArrayList<Object[]>();
		//使用两个for循环遍历Excel数据文件的所有数据（初了第一行，第一行是数据列名称）
		//所有i从1开始，而不是从0开始
		for (int i = 1; i < rowCount+1; i++) {
			//使用getRow方法获取行对象
			Row row=sheet.getRow(i);
			/*
			 声明一个数组，用来存储Excel数据文件每行中的测试数据和用例，数组大小用
			 getLastCellNum-2来进行动态声明，实现测试数据个数和数组大小数组一致，因为Excel
			 数据文件中的测试数据行的最后一个单元格为参数执行结果，倒数第二个单元格为此测试数据行是否
			 运行的状态位。所以最后两列的单元格数据并不需要传入测试方法中，所以使用getLastCell-2的方式
			 去掉每行中的最后两个单元格数据，计算出需要存储的参测试数据个数，并作为测试数据数组的初始化大小
			 
			 */
			String []fileds=new String[row.getLastCellNum()-2];
			/*
			 if用于判断数据行是否要参与测试的执行，Excel文件的倒数第二列为数据行的状态位
			 标记为表示此数据行要参与脚本执行，标记为n 表示不执行，跳过*/
			if (row.getCell(row.getLastCellNum()-2).getStringCellValue().equals("y")) {
				
				for(int j=0;j<row.getLastCellNum()-2;j++){
					//判断excel的单元格字段是否还是字符，字符串格式调用getStringCellValue方法获取
					//数字格式调用getNumerCellValue方法获取
					fileds[j]=(String)(row.getCell(j).getCellType()==XSSFCell.CELL_TYPE_STRING ?
							row.getCell(j).getStringCellValue():""+row.getCell(j).getNumericCellValue());
					
				}
				//fields的数据对象存储到records的list中
				records.add(fileds);
				
			}
			
		}
		//定义函数返回值，即Object[][]
		//将存储测试数据的list转换为一个object的Object的二维数组
		Object[][] results=new Object[records.size()][];
		//设置二维数组每行的值，每行是一个Object对象
		for (int i = 0; i < records.size(); i++) {
			results[i]=records.get(i);
			
		}
		return results;
		
	}
	
	public static int getLastColumnNUm(){
		//返回数据文件最后一列的行号，如果有12列，则结果返回11
		return ExcelWSheet.getRow(0).getLastCellNum()-1;
	}
	

}
