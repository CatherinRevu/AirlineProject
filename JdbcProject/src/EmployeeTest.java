import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class EmployeeTest {
	public static void main(String arga[]) {
		
		//Find all employee
        
        List<Employee> empList = null;    
		
		EmployeeDAOImpl ddiObj = new EmployeeDAOImpl();
		
		empList = ddiObj.findEmployee(); // select * from dept hidden in it

		Iterator<Employee> iter = empList.iterator();
		
		while(iter.hasNext()) {
			Employee empObj = iter.next();
			System.out.println("emp number   : "+empObj.getEmployeeNumber());
			System.out.println("emp name     : "+empObj.getEmployeeName());
			System.out.println("emp job      : "+empObj.getEmployeejob());
			System.out.println("emp Mgg      : "+empObj.getEmployeemgr());
			System.out.println("emp HireDate : "+empObj.getHiredate());
			System.out.println("emp sal      : "+empObj.getEmployeesal());
			System.out.println("emp comm     : "+empObj.getEmployeecomm());
			System.out.println("emp deptno   : "+empObj.getEmployeedeptno());
			System.out.println("--------------------");
		}
		
	
      
        
		
		//add employee
		
		/*Employee emp1= new Employee();  
		emp1.setEmployeeNumber(1234);
		emp1.setEmployeeName("ABCDE");
		emp1.setEmployeejob("Devloper");
		emp1.setHiredate(null);
		emp1.setEmployeesal(300000.00);
		emp1.setEmployeecomm(28000.00);
		emp1.setEmployeedeptno(30);
		
		EmployeeDaoImpl ddiObj = new EmployeeDaoImpl(); // driver loaded, connected too
		
		ddiObj.addEmployee(emp1);
		*/
		
		
		//Delete employee from emp table
		
	/*	 
	   Employee empObj1 = new Employee();
		
		empObj1.setEmployeeNumber(7789);
		
		DepartmentDAOImpl eeiObj = new DepartmentDAOImpl();
		eeiObj.removeEmployee(empObj1);
	*/
		
		//update emp detail 
		
	/*	Employee empObj1 = new Employee();
		
		empObj1.setEmployeeNumber(7789);
		
		EmployeeDAOImpl eeiObj = new EmployeeDAOImpl(); // driver loaded, connected too
		
		eeiObj.modifyEmployee(empObj1);
	*/
		
		
		//find employee with empno
		
	/*	Employee empObj = null;
		EmployeeDAOImpl ddiObj = new EmployeeDAOImpl();
		empObj = ddiObj.findEmployee(7369);
		System.out.println("emp number   : "+empObj.getEmployeeNumber());
		System.out.println("emp name     : "+empObj.getEmployeeName());
		System.out.println("emp job      : "+empObj.getEmployeejob());
		System.out.println("emp Mgg      : "+empObj.getEmployeemgr());
		System.out.println("emp HireDate : "+empObj.getHiredate());
		System.out.println("emp sal      : "+empObj.getEmployeesal());
		System.out.println("emp comm     : "+empObj.getEmployeecomm());
		System.out.println("emp deptno   : "+empObj.getEmployeedeptno());
		System.out.println("--------------------");
		*/
		
		
		
	}	
		
}
class Employee  // pojo is same as dept table structure empno,name,job,hiredate,mgr,sal, comm,deptno
{
	private int employeeNumber; //same as deptno column
	private String employeeName; // same as dname column
	private String employeejob;
	private Date hiredate;//same as loc column
	private int employeemgr;
	private double employeesal;
	private double employeecomm;
	private int employeedeptno;
	//setter and getter to set and fetch information 
	public int getEmployeeNumber() {
		return employeeNumber;
	}
	public void setEmployeeNumber(int employeeNumber) {
		this.employeeNumber = employeeNumber;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getEmployeejob() {
		return employeejob;
	}
	public void setEmployeejob(String employeejob) {
		this.employeejob = employeejob;
	}
	public Date getHiredate() {
		return hiredate;
	}
	public void setHiredate(Date hiredate) {
		this.hiredate = hiredate;
	}
	public int getEmployeemgr() {
		return employeemgr;
	}
	public void setEmployeemgr(int employeemgr) {
		this.employeemgr = employeemgr;
	}
	public double getEmployeesal() {
		return employeesal;
	}
	public void setEmployeesal(double employeesal) {
		this.employeesal = employeesal;
	}
	public double getEmployeecomm() {
		return employeecomm;
	}
	public void setEmployeecomm(double employeecomm) {
		this.employeecomm = employeecomm;
	}
	public int getEmployeedeptno() {
		return employeedeptno;
	}
	public void setEmployeedeptno(int employeedeptno) {
		this.employeedeptno = employeedeptno;
	}
	
	
}
interface EmployeeDAO {
   // void addEmployee(Employee eRef);
	
	Employee findEmployee(int eno);
	
	List<Employee> findEmployee();
	
	//List<Employee> findEmployee(String dno);
	
	
    void removeEmployee(Employee eRef);
    
    void modifyEmployee(Employee eRef);
    
   // void removeEmployee(String job);
   // void removeEmployee(int empno);
}
class EmployeeDAOImpl implements EmployeeDAO{

	Connection conn;
	ResultSet rs;
	Statement st;
	PreparedStatement pst;
	
	EmployeeDAOImpl() {
		try
		{
		
			System.out.println("Trying to load the driver.......");
			DriverManager.registerDriver(new oracle.jdbc.OracleDriver());
			System.out.println("Driver loaded....");
		
			
			System.out.println("Trying to connect to the database");
	
			conn= DriverManager.getConnection("jdbc:oracle:thin:"+"@localhost:1521:ORCL","system","catherin_1");
			System.out.println("Connected to the database");
		}
		catch(Exception e) {
			System.out.println("Some Problem : "+e);
		}
	}
	
	
	
	public 	void modifyEmployee(Employee eRef) {
		try {
			System.out.println("Trying to make a PreparedStatement for DML(update)");	//3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
			PreparedStatement pst = conn.prepareStatement("update emp set ename=?, job=? where empno=?"); // this is for DML
            Scanner sc=new Scanner(System.in);
            System.out.println("Enter the updated Employee name :");
            String name=sc.next();
            System.out.println("Enter the updated designation :");
            String job=sc.next();
			pst.setString(1,name); 
			pst.setString(2, job);
			pst.setInt(3,  eRef.getEmployeeNumber());
			
			System.out.println("PrepareStatement made....for DML update");
			System.out.println("Trying to fire it... ");	//4th step : fire the statement and acquire result if any
			int rows = pst.executeUpdate();
			System.out.println("Record updated : "+rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	
	
	/*public void addEmployee(Employee eRef) { //insert query
		try {
			PreparedStatement pst = conn.prepareStatement("insert into emp values (?,?,?,?,null,?,?,?)"); // this is for DML
			pst.setInt(1, eRef.getEmployeeNumber());	
			pst.setString(2, eRef.getEmployeeName()); 
			pst.setString(3, eRef.getEmployeejob());
			
			pst.setInt(4, eRef.getEmployeemgr());
			//pst.setDate(5, eRef.getHiredate());
			pst.setDouble(6,eRef.getEmployeesal());
			pst.setDouble(7, eRef.getEmployeecomm());
			pst.setDouble(8, eRef.getEmployeedeptno());
			
			System.out.println("PrepareStatement made....for DML");
			
			System.out.println("Trying to fire it... ");	//4th step : fire the statement and acquire result if any
			int rows = pst.executeUpdate(); //fire the pst associated insert query
			System.out.println("Record inserted..."+rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}*/
	
	public Employee findEmployee(int eno) {
		Employee empObj = null;
		
		try {
			st = conn.createStatement();
			System.out.println("Statment made .... ");
			
			rs = st.executeQuery("select * from emp where empno="+eno); // type the query here
			System.out.println("Query fired...and got the result....");
			System.out.println("Now processing the result....."); //5th step : process the result
			if(rs.next()) { // process the result set like a cursor program
				int a = rs.getInt(1); 	
				String b = rs.getString(2); // dname
				String c = rs.getString(3);
				int d=rs.getInt(4);
				Date e=rs.getDate(5);
				int  f=rs.getInt(6);
				int g=rs.getInt(7);
				int h=rs.getInt(8);
				
				empObj = new Employee(); //empty single object
				empObj.setEmployeeNumber(a);
				empObj.setEmployeeName(b);
				empObj.setEmployeejob(c);
				empObj.setEmployeemgr(d);
				empObj.setHiredate(e);
				empObj.setEmployeesal(f);
				empObj.setEmployeecomm(g);
				empObj.setEmployeedeptno(h);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empObj;
	}
	
	
	
	
	public List<Employee> findEmployee() {
		List<Employee> empList = new ArrayList<Employee>(); //empty
		
		try {
			Statement st = conn.createStatement();
			System.out.println("Statement made...");
			rs = st.executeQuery("select * from emp"); // type the query here
			System.out.println("Query fired...and got the result....");
			System.out.println("Now processing the result....."); //5th step : process the result
			while(rs.next()) { // process the result set like a cursor program
				int a = rs.getInt(1); 	
				String b = rs.getString(2); // dname
				String c = rs.getString(3);
				int d=rs.getInt(4);
				Date e=rs.getDate(5);
				int  f=rs.getInt(6);
				int g=rs.getInt(7);
				int h=rs.getInt(8);
				
				Employee empObj = new Employee(); //empty single object
				empObj.setEmployeeNumber(a);
				empObj.setEmployeeName(b);
				empObj.setEmployeejob(c);
				empObj.setEmployeemgr(d);
				empObj.setHiredate(e);
				empObj.setEmployeesal(f);
				empObj.setEmployeecomm(g);
				empObj.setEmployeedeptno(h);
				
				
				empList.add(empObj);
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return empList;
	}
	
	
	public 	void removeEmployee(Employee eRef) {
		try {
			System.out.println("Trying to make a PreparedStatement for DML(delete)");	//3rd step : create statement of your choice select(DQL)/DML/PL-SQL(proce/funs)
			PreparedStatement pst = conn.prepareStatement("delete from emp where emptno=?"); // this is for DML
			pst.setInt(1, eRef.getEmployeeNumber()); //set the question mark with dno
			
			System.out.println("PrepareStatement made....for DML delete");
			System.out.println("Trying to fire it... ");	//4th step : fire the statement and acquire result if any
			int rows = pst.executeUpdate(); //fire the pst associated insert query
			System.out.println("Record deleted..."+rows);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	
}