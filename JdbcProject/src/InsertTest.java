import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class InsertTest{
	public static void main(String[] args)
	{
		try
		{
		System.out.println("trying to load the driver");
		DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
		System.out.println("driver loaded");
		
		System.out.println("trying to connect to database");
		Connection conn= DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:ORCL","system","catherin_1");
		System.out.println("connected to Database");
		conn.setAutoCommit(false);
		
		PreparedStatement pst=conn.prepareStatement("insert into dept values (?,?,?)");
		Scanner scan=new Scanner(System.in);
		Scanner scan1=new Scanner(System.in);
		System.out.println("DEPTNO : "); 
		int dno = scan.nextInt();
		System.out.println("DNAME : "); 
		String dnm = scan.next();
		System.out.println("LOC : ");
		String dloc = scan.next();
		pst.setInt(1,dno);
		pst.setNString(2, dnm);
		pst.setNString(3, dloc);
		System.out.println("preparedstmt made ");
		System.out.println("trying to fire it ");
		int rows = pst.executeUpdate();
		System.out.println("do u want to save this record?");
		String ans= scan1.nextLine();
		if(ans.equalsIgnoreCase("Yes"))
		{
			conn.commit();
			System.out.println("query fired and rows inserted"+rows);
		}
		else if(ans.equalsIgnoreCase("No"))
		{
			conn.rollback();
			System.out.println("Rows discarded "+rows);
		}
		
		pst.close();
		conn.close();
		System.out.println("End of Main");
		
		}
		catch(SQLException e)
		{
			System.out.println("Some SQL problem"+e);	
		}
	}
}