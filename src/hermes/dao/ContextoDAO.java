package hermes.dao;

import hermes.db.Conexion;
import hermes.model.Contexto;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ContextoDAO implements IContextoDAO {

	@Override
	public List<Contexto> listarContextos() {
		Conexion database = new Conexion();
		Connection c;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    List<Contexto> lista = new LinkedList<Contexto>();
	    
	    c = database.getConnection();
	    
	    try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
	    try {
			rs = stmt.executeQuery("SELECT * FROM contexto");
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}
	    
	    try {
			while (rs.next()){
				Contexto contexto = new Contexto(
						rs.getInt("id"),
						rs.getString("texto")
				);
				lista.add(contexto);
			}
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    return lista;
	}

}
