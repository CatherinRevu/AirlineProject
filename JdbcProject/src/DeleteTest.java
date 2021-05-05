import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class DeleteTest{
	public static void main(String[] args) {
		try
		{
			//1st step : load the driver
			System.out.println("Trying to load the driver.......");
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver loaded....");
		
			//2nd step : connect to the database 
			System.out.println("Trying to connect to the database");
//jdbc:oracle:thin:@localhost:1521:yourInstanceName XE/ORCL/OSE whaterver name found in tnsnames.ora file
			Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","catherin_1");
			System.out.println("Connected to the database");
			
			//by default all DML statements are autocommited...in JDBC
			
			// if you want disable the autocommit of jdbc, then use below line
			conn.setAutoCommit(false); // now auto commit is off
			// DDL statement performs autocommit the data
			System.out.println("Trying to make a PreparedStatement for DML(delete)");	//3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
			PreparedStatement pst = 
			conn.prepareStatement("delete from dept where deptno=?"); // this is for DML
			Scanner scan1 = new Scanner(System.in);	
			Scanner scan4 = new Scanner(System.in);

		    System.out.println("Which dept number to delete : "); 
		    int dno = scan1.nextInt(); //90
		    pst.setInt(1, dno); //set the question mark with dno
		    
			System.out.println("PrepareStatement made....for DML delete");
			System.out.println("Trying to fire it... ");	//4th step : fire the statement and acquire result if any
			int rows = pst.executeUpdate(); //fire the pst associated insert query
			System.out.println("Do you want to delete this record (yes/no) ? ");
			String ans = scan4.nextLine();
			if(ans.equalsIgnoreCase("yes")) {		conn.commit();
				System.out.println("Rows deleted..."+rows);
			}
			else if(ans.equalsIgnoreCase("no")) {	conn.rollback();
				System.out.println("Rows deletion failed...."+rows);
			} 
			System.out.println("Trying to close PrepareStatement,Connection....");
			pst.close();  conn.close();  // close DB resources
			System.out.println("PrepareStatement,Connection closed....");
			System.out.println("End of main...");
		}
		catch(SQLException e) {
			System.out.println("Some SQL problem : "+e);
		}
	}
}
