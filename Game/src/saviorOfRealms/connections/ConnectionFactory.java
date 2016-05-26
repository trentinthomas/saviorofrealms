package saviorOfRealms.connections;

import java.sql.Connection;
import java.sql.DriverManager;

public class ConnectionFactory {

	
	public static Connection getConnection()
	{
		Connection con = null;
		try
		{
			Class.forName("org.sqlite.JDBC");
			con = DriverManager.getConnection("jdbc:sqlite:test.db");
			System.out.println("Connection opened successfully");
		} catch( Exception e )
		{
			e.printStackTrace();
			System.out.println("ConnectionFailed");
		}
		return con;
	}
}
