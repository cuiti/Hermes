package hermes.dao;

import hermes.db.BaseDeDatos;
import hermes.model.Contenido;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class ContenidoDAO implements IContenidoDAO {

	@Override
	public List<Contenido> listarContenidos() {
		BaseDeDatos database = new BaseDeDatos();
		Connection c;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    List<Contenido> lista = new LinkedList<Contenido>();
	    
	    c = database.getConnection();
	    
	    try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
	    try {
			rs = stmt.executeQuery("SELECT * FROM contenido");
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}
	    
	    try {
			while (rs.next()){
				Contenido contenido = new Contenido(
						rs.getInt("id"),
						rs.getString("texto")
				);
				lista.add(contenido);
			}
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    return lista;
	}

}
