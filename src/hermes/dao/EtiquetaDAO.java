package hermes.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;

import hermes.db.BaseDeDatos;
import hermes.model.Categoria;
import hermes.model.Etiqueta;

public class EtiquetaDAO implements IEtiquetaDAO {

	@Override
	public List<Etiqueta> listarEtiquetas() {
		BaseDeDatos database = new BaseDeDatos();
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    List<Etiqueta> lista = new LinkedList<Etiqueta>();
	    
	    Connection c = database.getConnection();
	    
	    try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
	    try {
			rs = stmt.executeQuery("SELECT * FROM etiqueta");
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}
	    
	    try {
			while (rs.next()){
				Etiqueta etiqueta = new Etiqueta(
						rs.getInt("id"),
						rs.getString("texto")
				);
				lista.add(etiqueta);
			}
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    return lista;
	}

	@Override
	public boolean guardarEtiqueta(Etiqueta etiqueta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean eliminarEtiqueta(Etiqueta etiqueta) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public boolean renombrarEtiqueta(Etiqueta etiqueta, Etiqueta nuevaEtiqueta) {
		// TODO Auto-generated method stub
		return false;
	}

}
