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
	
	/**
	 * Hace todo lo necesario para acceder a la db, conexion, statement y consulta
	 * @param consulta el string con la consulta literal en SQL
	 * @return un ResultSet con todos los resultados
	 */
	public ResultSet ejecutarConsulta(String consulta){
		Connection conexion = this.getConnection();
		Statement stmt=null;
		ResultSet resultados=null;
		
		try {
			stmt = conexion.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
			
		try {
			resultados = stmt.executeQuery(consulta);
			
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}
		
		return resultados;	
	}
	
	/**
	 * M�todo que realiza las inserciones en la base de datos
	 * @param query es la consulta de insercion en SQL literal
	 */
	public boolean ejecutarInsercion(String query){
		Connection conexion = this.getConnection();
	    Statement stmt = null;
	    
	    try {
	      stmt = conexion.createStatement();
	      stmt.executeUpdate(query);
	      stmt.close();
	      // conexion.commit();		// java.sql.SQLException: database in auto-commit mode 
	      conexion.close();
	      return true;
	    } catch ( Exception e ) {
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	}
}
