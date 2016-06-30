package Basic;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import jxl.Sheet;
import jxl.Workbook;
/*
* 获取Excel文件的内容,使用Workbook方式来读取excel
*/
public class ExcelWorkBook {
	//利用list集合来存放数据，其类型为String
	private List<String> list=new ArrayList<String>();
	//通过Workbook方式来读取excel
	Workbook book;
	String item_id;
	String sale_id;
	String name;
	/*
	 * 获取excel文件第一列的值，这里取得值为sku_id
	 */
	public List<String> readSkuId(String sourceString) throws IOException,Exception{
		List<String> skuList = new ArrayList<String>();
		try {
			Workbook book =Workbook.getWorkbook(new File(sourceString));
			Sheet sheet=book.getSheet(0);
			//获取文件的行数
			int rows=sheet.getRows();
			//获取文件的列数
			int cols=sheet.getColumns();
			//获取第一行的数据，一般第一行为属性值，所以这里可以忽略
			/*
			String col1=sheet.getCell(0,0).getContents().trim();
			String col2=sheet.getCell(1,0).getContents().trim();
			System.out.println(col1+","+col2);
			*/
			//把第一列的值放在skulist中
			for(int z=1;z<rows ;z++){
				String item_id=sheet.getCell(0,z).getContents();
				skuList.add(item_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		//把获取的值放回出去，方便调用
		return skuList;
	}
	
	/*
	 * 获取excel文件第二列的值，这里取得值为sale_id
	 */
	public List<String> readSaleId(String sourceString) throws IOException,Exception{
		List<String> saleList = new ArrayList<String>();
		try {
			Workbook book =Workbook.getWorkbook(new File(sourceString));
			Sheet sheet=book.getSheet(0);
			int rows=sheet.getRows();
			for(int z=1;z<rows ;z++){
				String sale_id=sheet.getCell(1,z).getContents();
				saleList.add(sale_id);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return saleList;
	}
	
	/*
	 * 获取excel文件第四列的值，这里取得值为name
	 */
	public List<String> readName(String sourceString) throws IOException,Exception{
		List<String> nameList = new ArrayList<String>();
		try {
			Workbook book =Workbook.getWorkbook(new File(sourceString));
			Sheet sheet=book.getSheet(0);
			int rows=sheet.getRows();
			for(int z=1;z<rows ;z++){
				String name=sheet.getCell(3,z).getContents();
				nameList.add(name);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return nameList;
	}
	public List<String> getList(){
		return list;
	}
}