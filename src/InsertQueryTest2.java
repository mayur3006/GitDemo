import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class InsertQueryTest2 {
	static final String JDBC_DRIVER ="oracle.jdbc.OracleDriver";  
    static final String DB_URL ="jdbc:oracle:thin:@//localhost/xe";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		int eno;
		String name;
		double sal;
		int deptno;
		int n;
		
		Class.forName(JDBC_DRIVER); // Register Driver
		                            //Establish Connection
		try{
			conn = DriverManager.getConnection(DB_URL, "mayur", "mayur");
			stmt = conn.createStatement(); //Create Statement Object
			
			eno = Integer.parseInt(args[0]);
			name = args[1];
			sal = Double.parseDouble(args[2]);
			deptno = Integer.parseInt(args[3]);
			sql = "Insert into emp(empno,ename,sal,deptno) values("
			+ eno + ",'" + name + "'," + sal + "," + deptno + ")" ;
			System.out.println(sql);
			n = stmt.executeUpdate(sql);
			
			if (n > 0)
				System.out.printf("%d rows Inserted%n", n);
						//rs.close();
			
			stmt.close();
			conn.close();
		
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		

	}
}
