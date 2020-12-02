package library;

import java.sql.*;

/**********************************************************************************************
/*Script Developed by :- Deepak Kumar
 *Script Name :- Dropnet List and Search.
 *Script Type :- Testng
 *Platform:- Minimum requirement java SE7 to run this script
 */
//*********************************************************************************************/

public class DatabaseConnectivity {
		
	public void database(String Locationcd,String effective_dt_database) throws ClassNotFoundException, SQLException{

		String dbUrl = "URL";
	    String username = "User";
	    String password = "Pasasword";
   

	
		try{
		Class.forName("oracle.jdbc.driver.OracleDriver");
		Connection con = DriverManager.getConnection(dbUrl, username, password);
		Statement stmt = con.createStatement();
		
		String Query ="";
		 System.out.println(Query);
		ResultSet rs1 = stmt.executeQuery(Query);
		while (rs1.next()) {
			
		}
		
			
		}
			catch(Exception E){
				E.printStackTrace();
				System.out.println(E.getMessage());
			}
		}


}