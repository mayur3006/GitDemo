import java.sql.*;
public class DBQueryTest {
	static final String JDBC_DRIVER ="oracle.jdbc.OracleDriver";  
    static final String DB_URL ="jdbc:oracle:thin:@//localhost/xe";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		ResultSet rs = null;
		String sql;
		int eno, dno;
		String name;
		double sal;
		Class.forName(JDBC_DRIVER); // Register Driver
		                            //Establish Connection
		try{
			conn = DriverManager.getConnection(DB_URL, "mayur", "mayur");
			stmt = conn.createStatement(); //Create Statement Object
			sql = "Select empno, ename, sal, deptno from emp";
			
			rs = stmt.executeQuery(sql);
			
			System.out.println("Employee Details");
			System.out.println("=======================================");
			
			while (rs.next()) {
				eno = rs.getInt(1);
				name = rs.getString("ename");
				sal = rs.getDouble(3);
				dno = rs.getInt(4);
				
				System.out.printf("%d\t%s\t%.2f\t%d%n", eno, name, sal, dno);
				
			}
			rs.close();
			stmt.close();
			conn.close();
		
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		

	}

}
