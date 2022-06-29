package DateInsert;

import java.sql.Connection;
import java.sql.Driver;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Enumeration;
import java.util.Scanner;

public class DateInsert {

	private static final String INSERT_DATE="INSERT INTO PERSON_DATE VALUES(?,?,?,?,?)";
	public static void main(String[] args) throws Exception {
	
		Scanner scn=null;
		int no=0;
		String name=null;
		String dob=null;
		String doj=null;
		String dom=null;
		SimpleDateFormat sdf=null;
		java.util.Date udob=null, udoj=null,udom=null;
		
		long ms=0;
		java.sql.Date sqdob=null, sqdoj=null, sqdom=null;
		Connection con=null;
		PreparedStatement ps=null;
		int result=0;
		try {
			scn=new Scanner(System.in);
			if(scn!=null) {
				System.out.println("Enter No:");
			    no=scn.nextInt();
			    System.out.println("Enter Name:");
			    name=scn.next();
			    System.out.println("Enter DOB(dd-MM-yyyy:");
			    dob=scn.next();
			    System.out.println("Enter DOJ (MM-dd-yyyy)");
			    doj=scn.next();
			    System.out.println("Enter DOM (yyyy-MM-dd)");
			    dom=scn.next();
			}//if
			
//converting String date values to java.sql.Date class object
			 
			//FOR DOB
			sdf=new SimpleDateFormat("dd-MM-yyyy");
			if(sdf!=null) {
			udob=sdf.parse(dob);//gives java.util.Date Object
			}//if
			if(udob!=null) {
				ms=udob.getTime();
				sqdob=new java.sql.Date(ms);
			}//if
			
		
			
			//FOR DOJ
			sdf=new SimpleDateFormat("MM-dd-yyyy");
			if(sdf!=null) {
			udoj=sdf.parse(dob);//gives java.util.Date Object
			}//if
			if(udoj!=null) {
				ms=udoj.getTime();
				sqdoj=new java.sql.Date(ms);
			}//if
			
			//FOR DOM
			sqdom=java.sql.Date.valueOf(dom);
			
			
			
//register JDBC Driver
			
	 Class.forName("oracle.jdbc.driver.OracleDriver");
//Establish connection
	 con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");
System.out.println(con);

Enumeration e=DriverManager.getDrivers();
  while(e.hasMoreElements()) {
	  Driver d=(Driver)e.nextElement();
	  System.out.println(d.getClass().getName());
  }
//create PreparedStement
	 if(con!=null) {
		 ps=con.prepareStatement(INSERT_DATE);//set SQL param values
	 }//if
	 if(ps!=null) {
		 ps.setInt(1,no);
		 ps.setString(2,name);
		 ps.setDate(3,sqdob);
		 ps.setDate(4,sqdoj);
		 ps.setDate(5, sqdom);
		 
	 }//if
	 
//execute the Query
	 if(ps!=null)
		 result=ps.executeUpdate();
//process the result
	 if(result==0)
		 System.out.println("Record not Inserted");
	 else
		 System.out.println("Record Inserted");
			
		}//try
		catch(SQLException se) {
			se.printStackTrace();
			System.out.println("Record Insertion Failed");
		}
		catch(ClassNotFoundException cne) {
			cne.printStackTrace();
			System.out.println("Record Insertion Failed");
		}
		
		catch(Exception e) {
			e.printStackTrace();
			System.out.println("Record Insertion Failed");
		}
		finally {
			
			//close jdbc objects
				
				
				
			    try {
					
					if(con!=null)
						con.close();
					
				}catch(Exception se) {
					se.printStackTrace();
				}//catch
				
				
			     try {
					
					if(scn!=null)
						scn.close();
					
				}catch(Exception se) {
					se.printStackTrace();
				}//catch
			     
			}//finally
		

	}//main method

}//class
