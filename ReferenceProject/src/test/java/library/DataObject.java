package library;

import java.util.HashMap;
import java.util.LinkedHashMap;
/**********************************************************************************************
/*Script Developed by :- Deepak Kumar
 *Script Name :- Dropnet List and Search.
 *Script Type :- Testng
 *Platform:- Minimum requirement java SE7 to run this script
 */
//*********************************************************************************************/
/**********************************************************************************************
 * Description:This method will store the data of excel.
 *********************************************************************************************/

public class DataObject {
	public String key;
	public static HashMap<String, LinkedHashMap<String, String>> ExcelObject = new LinkedHashMap<String, LinkedHashMap<String, String>>();

	public static HashMap<String, LinkedHashMap<String, String>> getExcelObject() {
		return ExcelObject;
	}

	public void setExcelObject(HashMap<String, LinkedHashMap<String, String>> excelObject) {
		ExcelObject = excelObject;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public static String getVariable(String ColumnName, String testcase) {

		String value = DataObject.getExcelObject().get(testcase).get(ColumnName).toString();
		return value;
	}
	

	
	
	
	
}
