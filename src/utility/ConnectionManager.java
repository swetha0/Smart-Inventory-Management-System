package utility;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionManager {

	public static Connection getConnection() throws Exception {
		Class.forName("oracle.jdbc.OracleDriver");
		Connection con = null;
		con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","SYSTEM", "Swetha@24989");
		
		if(con != null)
			System.out.println("Connection Established");
		
		return con;
		
	}
}
