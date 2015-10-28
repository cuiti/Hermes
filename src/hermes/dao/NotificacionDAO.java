package hermes.dao;

import java.sql.*;

public class NotificacionDAO implements INotificacionDAO {

	public NotificacionDAO() {
		// TODO Auto-generated constructor stub
	}

	

	public static void main( String args[] ) {
		    Connection c = null;
		    Statement stmt = null;
		    ResultSet rs = null;
		    
		    try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:C:/dev/workspace/Hermes-desktop/hermes.sqlite");
		    } catch ( Exception e ) {
		      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
		      System.exit(0);
		    }
		    System.out.println("Opened database successfully");
		    
		    try {
				stmt = c.createStatement();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				rs = stmt.executeQuery( "SELECT * FROM NOTIFICACION;" );
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		    try {
				System.out.println(rs.getString("descripcion"));
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	}
		
}

