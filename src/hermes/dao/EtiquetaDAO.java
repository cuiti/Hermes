package hermes.dao;

import java.sql.*;
import java.util.LinkedList;
import java.util.List;

import hermes.db.BaseDeDatos;
import hermes.model.Etiqueta;

public class EtiquetaDAO implements IEtiquetaDAO {

	@Override
	public List<Etiqueta> listarEtiquetas() {
		BaseDeDatos db = new BaseDeDatos();
	    ResultSet resultados; 
	    List<Etiqueta> lista = new LinkedList<Etiqueta>();
	    
	    String consulta = "SELECT * FROM etiqueta";
	    db.open();
	    resultados = db.ejecutarConsulta(consulta);
	    
	    try {
			while (resultados.next()){
				Etiqueta etiqueta = new Etiqueta(
						resultados.getInt("id"),
						resultados.getString("texto")
				);
				lista.add(etiqueta);
			}
			resultados.close();
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    db.close();
	    return lista;
	}

	@Override
	public boolean guardarEtiqueta(Etiqueta etiqueta) {
		boolean resul;
		BaseDeDatos db = new BaseDeDatos();
	    String query = "INSERT INTO etiqueta (texto) "
	    		+ "VALUES ('"		
	    		+ etiqueta.getTexto()
	    		+ "');";
	    db.open();
		resul = db.ejecutarABM(query);
		db.close();
		return resul;
	}

	@Override
	public boolean eliminarEtiqueta(Etiqueta etiqueta) {
		boolean resul;
		BaseDeDatos db = new BaseDeDatos();
	    String query = "DELETE FROM etiqueta WHERE id = "		
	    		+ etiqueta.getId()
	    		+";";
	    db.open();
		resul = db.ejecutarABM(query);
		db.close();
		return resul;
	}

	@Override
	public boolean renombrarEtiqueta(Etiqueta etiqueta, Etiqueta nuevaEtiqueta) {
		boolean resul;
		BaseDeDatos db = new BaseDeDatos();
	    String query = "UPDATE etiqueta SET texto = '"		
	    		+ nuevaEtiqueta.getTexto()+ "' "
	    		+ "WHERE id = "
	    		+ etiqueta.getId()+";";
	    db.open();
		resul = db.ejecutarABM(query);
		db.close();
		return resul;	}

	@Override
	public boolean asignarEtiqueta(int id_notificacion, int id_etiqueta) {		
		boolean resul;
		BaseDeDatos db = new BaseDeDatos();
	    String query = "INSERT INTO notificacion_etiqueta (id_notificacion, id_etiqueta) "
	    		+ "VALUES ("+id_notificacion + "," + id_etiqueta + ");";
	    db.open();
		resul = db.ejecutarABM(query);
		db.close();
		return resul;	}

	@Override
	public boolean desasignarEtiqueta(int id_notificacion, int id_etiqueta) {
		boolean resul;
		BaseDeDatos db = new BaseDeDatos();
	    String query = "DELETE FROM notificacion_etiqueta "
	    		+ "WHERE id_notificacion = "+ id_notificacion +" AND id_etiqueta = "+ id_etiqueta +";";
	    db.open();
		resul = db.ejecutarABM(query);
		db.close();
		return resul;	}

}
