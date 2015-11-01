package hermes.db;

import java.sql.*;

public class BaseDeDatos {

	public BaseDeDatos() {
	}
	
	public Connection getConnection(){
		
		Connection c= null;
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:C:/dev/workspace/Hermes-desktop/hermes.sqlite");
		      System.out.println("Opened database successfully");
		 } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		 }
		 
		return c;
		
	}
}
