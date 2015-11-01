package hermes.dao;

import hermes.db.BaseDeDatos;
import hermes.model.Nino;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

public class NinoDAO implements INinoDAO {

	@Override
	public List<Nino> listarNinos() {
		BaseDeDatos database = new BaseDeDatos();
		Connection c;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    List<Nino> lista = new LinkedList<Nino>();
	    
	    c = database.getConnection();
	    
	    try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
	    try {
			rs = stmt.executeQuery("SELECT * FROM nino");
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}
	    
	    try {
			while (rs.next()){
				Nino nino = new Nino(
						rs.getInt("id"),
						rs.getString("nombre"),
						rs.getString("apellido")
				);
				lista.add(nino);
			}
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    return lista;
	}

}
