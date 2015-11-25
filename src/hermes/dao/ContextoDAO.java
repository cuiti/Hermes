package hermes.dao;

import hermes.db.BaseDeDatos;
import hermes.model.Contexto;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

public class ContextoDAO implements IContextoDAO {

	@Override
	public List<Contexto> listarContextos() {
		BaseDeDatos db = new BaseDeDatos();
	    ResultSet rs = null;
	    List<Contexto> lista = new LinkedList<Contexto>();
	    
		String consulta = "SELECT * FROM contexto";
		
		rs = db.ejecutarConsulta(consulta);
	    
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
	
	public Contexto getContextoByNombre(String nombre) {
		BaseDeDatos db = new BaseDeDatos();
	    ResultSet rs = null;	    
	    Contexto contexto = new Contexto(-1, "contexto inexistente");	
	    
		String consulta = "SELECT * FROM contexto "
						+ "WHERE texto = "+ nombre +"";
		
	    rs = db.ejecutarConsulta(consulta);
		
	    try {
			if (rs.next()){
				contexto = new Contexto(
						rs.getInt("id"),
						rs.getString("texto")
				);
			} 
			
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    return contexto;
	}

}
