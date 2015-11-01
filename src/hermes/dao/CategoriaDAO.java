package hermes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import hermes.db.BaseDeDatos;
import hermes.model.Categoria;

public class CategoriaDAO implements ICategoriaDAO {

	@Override
	public void asignarCategoria(Categoria categoria) {
		// TODO Auto-generated method stub

	}
	
	public List<Categoria> listarCategorias() {
		BaseDeDatos database = new BaseDeDatos();
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    List<Categoria> lista = new LinkedList<Categoria>();
	    
	    Connection c = database.getConnection();
	    
	    try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
	    try {
			rs = stmt.executeQuery("SELECT * FROM categoria");
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}
	    
	    try {
			while (rs.next()){
				Categoria categoria = new Categoria(
						rs.getInt("id"),
						rs.getString("texto")
				);
				lista.add(categoria);
			}
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    return lista;
	}

}
