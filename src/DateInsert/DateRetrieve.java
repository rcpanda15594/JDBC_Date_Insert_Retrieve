package DateInsert;

import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

public class DateRetrieve {
private static final String DATE_SELECT_QUERY="SELECT PNO,PNAME,DOB,DOJ,DOM FROM PERSON_DATE";
	public static void main(String[] args) throws Exception {
		Connection con=null;
		PreparedStatement ps=null;
		ResultSet rs=null;
		Boolean flag=false;
		
		int pno=0;
		String pname=null;
		java.sql.Date sqdob=null,sqdoj=null,sqdom=null;
		java.util.Date udob=null,udoj=null,udom=null;
		SimpleDateFormat sdf=null;
		String dob=null, doj=null,dom=null;
		try {
		//Register Driver
		Class.forName("oracle.jdbc.driver.OracleDriver");
		
		//establish connection
		
		con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","tiger");

		//create PreparedStatement
		if(con!=null)
			ps=con.prepareStatement(DATE_SELECT_QUERY);
		
		//execute query
		if(ps!=null) {
			rs=ps.executeQuery();
			
		}
		
		//process the ResultSet
		if(rs!=null) {
		while(rs.next()) {
			flag=true;
			pno=rs.getInt(1);
			pname=rs.getString(2);
			sqdob=rs.getDate(3);
			sqdoj=rs.getDate(4);
			sqdom=rs.getDate(5);
	
		
		//convert java.sql.Date class object to java.util.Date class object
			udob=(java.util.Date)sqdob;
			udoj=(java.util.Date)sqdoj;
			udom=(java.util.Date)sqdom;
		
		//convert java.util.Date class object to String date values
			sdf=new SimpleDateFormat("dd-MM-yyyy");
			dob=sdf.format(udob);
			doj=sdf.format(udoj);
			dom=sdf.format(udom);
			
			System.out.println(pno+"  "+pname+"  "+dob+"  "+doj+"  "+dom);
		}//while
		}//if
		if(flag==false)
			System.out.println("Records not Found");
	
		}//try
		catch(SQLException se) {
			se.printStackTrace();
		}
		catch(Exception e) {
			e.printStackTrace();
		}finally {
			
			//close jdbc objects

		     try {
				
				if(rs!=null)
					rs.close();
				
			}catch(Exception se) {
				se.printStackTrace();
			}//catch

		     try {
				
				if(ps!=null)
					ps.close();
				
			}catch(Exception se) {
				se.printStackTrace();
			}//catch
		     
				
				
			    try {
					
					if(con!=null)
						con.close();
					
				}catch(Exception se) {
					se.printStackTrace();
				}//catch
				
				
			}//finally
		

	}//main method

}//class
