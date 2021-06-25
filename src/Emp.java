import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
public class Emp {
	static final String JDBC_DRIVER ="oracle.jdbc.OracleDriver";  
    static final String DB_URL ="jdbc:oracle:thin:@//localhost/xe";
    
    public static void main(String[] args) throws Exception{
    	Connection conn = null;
		Statement stmt = null;
		PreparedStatement pstmt = null;
		ResultSet rs = null;
		String sql;
		int eno;
		String name;
		double sal;
		int deptno;
		double comm;
		int n;
		
		Class.forName(JDBC_DRIVER);
		
		try{
			conn = DriverManager.getConnection(DB_URL, "mayur", "mayur");
			pstmt = conn.prepareStatement("insert into employees (empno,ename,sal,comm,deptno) values(?,?,?,?,?)");
			
			eno = Integer.parseInt(args[0]);
			name = args[1];
			sal = Double.parseDouble(args[2]);
			comm=Double.parseDouble(args[3]);
			deptno = Integer.parseInt(args[4]);
			
			pstmt.setInt(1, eno);
			pstmt.setString(2, name);
			pstmt.setDouble(3, sal);
			pstmt.setDouble(4, comm);
			pstmt.setInt(5, deptno);
			
			n = pstmt.executeUpdate();
			
			if (n > 0)
				System.out.printf("%d rows Inserted%n", n);
						//rs.close();
			
			pstmt.close();
			conn.close();
		
		}
		catch(SQLException ex){
			ex.printStackTrace();
		}
		catch(Exception e) {
			System.out.println(e.getMessage());
		}
		

	}
}

