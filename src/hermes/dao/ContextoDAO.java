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
		db.open();
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
	    db.close();
	    return lista;
	}
	
	public Contexto getContextoByNombre(String nombre) {
		BaseDeDatos db = new BaseDeDatos();
	    ResultSet rs = null;	    
	    Contexto contexto = new Contexto(-1, "contexto inexistente");	
	    
		String consulta = "SELECT * FROM contexto "
						+ "WHERE texto = '"+ nombre +"'";
		db.open();
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
	    db.close();
	    return contexto;
	}

	@Override
	public boolean guardarContexto(Contexto c) {
		boolean resul;
		BaseDeDatos db = new BaseDeDatos();
	    String query = "INSERT INTO contexto (texto) "
	    		+ "VALUES ('"		
	    		+ c.getTexto()
	    		+ "');";
	    db.open();
		resul = db.ejecutarABM(query);
		db.close();
		return resul;
	}

}
