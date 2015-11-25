package hermes.db;

import java.sql.*;

public class BaseDeDatos {
	
	private Statement stmt = null;
	private Connection conexion = null;

	public BaseDeDatos() {
	}
	
	public void open()  {
		conexion = this.getConnection();
		try {
			stmt = conexion.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void close()  {
		try {
			stmt.close();
			conexion.close();	
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public Connection getConnection(){
		
		Connection c = null;
		try {
		      Class.forName("org.sqlite.JDBC");
		      c = DriverManager.getConnection("jdbc:sqlite:"+System.getProperty("user.dir")+"/hermes.sqlite");
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
		ResultSet resultados=null;
		
			
		try {
			resultados = stmt.executeQuery(consulta);
			
		} catch (SQLException e) {
			System.out.println(consulta);
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}
		
		return resultados;	
	}
	
	/**
	 * Método que realiza los ABM en la base de datos
	 * @param query es la consulta de insercion, eliminacion o update en SQL literal
	 */
	public boolean ejecutarABM(String query){
	    
	    try {
	      stmt.executeUpdate(query);
	      return true;
	    } catch ( Exception e ) {
	    	System.out.println(query);
	      System.err.println( e.getClass().getName() + ": " + e.getMessage() );
	      return false;
	    }
	}
}
