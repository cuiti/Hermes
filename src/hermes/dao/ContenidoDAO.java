package hermes.dao;

import hermes.db.BaseDeDatos;
import hermes.model.Contenido;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContenidoDAO implements IContenidoDAO {

	@Override
	public List<Contenido> listarContenidos() {
		BaseDeDatos db = new BaseDeDatos();
	    ResultSet rs = null;    
	    List<Contenido> lista = new LinkedList<Contenido>();
	    
	    String consulta = "SELECT * FROM contenido";	    
	   
	    rs = db.ejecutarConsulta(consulta);
	    
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
