package hermes.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
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
		BaseDeDatos db = new BaseDeDatos();
	    ResultSet rs = null;	    
	    List<Categoria> lista = new LinkedList<Categoria>();
	    
		String consulta = "SELECT * FROM categoria";
		
	    rs = db.ejecutarConsulta(consulta);
		
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
