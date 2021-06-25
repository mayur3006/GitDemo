import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class TransactDemo {
	static final String JDBC_DRIVER ="oracle.jdbc.OracleDriver";  
    static final String DB_URL ="jdbc:oracle:thin:@//localhost/xe";

	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		
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
	        conn.setAutoCommit(false);
			
	        stmt = conn.createStatement();
	        stmt.executeUpdate("update counter set count=count+1");
	        
	        rs = stmt.executeQuery("select count from counter");
			rs.next();
			eno = rs.getInt(1);
			
			pstmt = conn.prepareStatement("insert into emp (empno,ename,sal,deptno) values(?,?,?,?)");
			
			
			name = args[0];
			sal = Double.parseDouble(args[1]);
			deptno = Integer.parseInt(args[2]);
			
			pstmt.setInt(1, eno);
			pstmt.setString(2, name);
			pstmt.setDouble(3, sal);
			pstmt.setInt(4, deptno);
			
			n = pstmt.executeUpdate();
			
			if (n > 0)
			{
				System.out.printf("%d rows Inserted%n", n);
				conn.commit();
						//rs.close();
			}
			pstmt.close();
			conn.close();
		
		}
		catch(SQLException ex){
			conn.rollback();
			ex.printStackTrace();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
}
