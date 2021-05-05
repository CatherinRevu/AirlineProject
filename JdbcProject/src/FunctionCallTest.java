import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Types;
import java.util.Scanner;

public class FunctionCallTest{
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
		
		CallableStatement cst=conn.prepareCall("{?= call myemp.calc_sal(?) }");
		System.out.println("callable stmt made");
		
		Scanner scan=new Scanner(System.in);
		System.out.println("Enter empno");
		int eno=scan.nextInt();
		
		
		cst.setInt(2, eno);
		cst.registerOutParameter(1, Types.DOUBLE);
		
		cst.execute();
		
		double salary=cst.getDouble(1);
		System.out.println("Block executed");
		System.out.println(" SALARY : "+salary);
		
		System.out.println("trying to close");
		
		cst.close();
		conn.close();
		System.out.println("End of Main");
		
		}
		catch(SQLException e)
		{
			System.out.println("Some SQL problem"+e);	
		}
	}
}