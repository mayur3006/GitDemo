import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class studentJDBC {
	static final String JDBC_DRIVER ="oracle.jdbc.OracleDriver";  
    static final String DB_URL ="jdbc:oracle:thin:@//localhost/xe";
    
    public static void main(String[] args) throws Exception {
    	Connection conn = null;
		PreparedStatement pstmt = null;
		String sql;
		
		int id;
		String name;
		String course;
		double fees;
		double percentage;
		int n;
		
		Class.forName(JDBC_DRIVER);
		try{
			conn = DriverManager.getConnection(DB_URL, "mayur", "mayur");
			pstmt = conn.prepareStatement("insert into students(id,name,course,fees,percentage) values(?,?,?,?,?)");
			
			id = Integer.parseInt(args[0]);
			name = args[1];
			course =args[2];
			fees = Double.parseDouble(args[3]);
			percentage = Double.parseDouble(args[4]);
			
			pstmt.setInt(1, id);
			pstmt.setString(2, name);
			pstmt.setString(3, course);
			pstmt.setDouble(4, fees);
			pstmt.setDouble(5, percentage);
			
			
			n = pstmt.executeUpdate();
			
			if (n > 0)
				System.out.printf("%d rows Inserted%n", n);
			
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

