package hermes.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import hermes.db.BaseDeDatos;
import hermes.model.*;

public class NotificacionDAO implements INotificacionDAO {

	public NotificacionDAO() {
		
	}
 
	@Override
	public List<Notificacion> listarNotificaciones() {
		BaseDeDatos database = new BaseDeDatos();
		Connection c;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    List<Notificacion> lista = new ArrayList<Notificacion>();
	    
	    c = database.getConnection();
	    
	    try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
	    try {
			rs = stmt.executeQuery( "SELECT notificacion.id, "
					+ "notificacion.fecha_envio, notificacion.fecha_recepcion, "
					+ "notificacion.id_contexto, contexto.texto AS contexto, "
					+ "notificacion.id_contenido, contenido.texto AS contenido, "
					+ "notificacion.id_categoria, categoria.texto AS categoria, "
					+ "notificacion.id_nino, nino.nombre, nino.apellido "
//					+ "notificacion_etiqueta.id_etiqueta, etiqueta.texto AS etiqueta "   
					+ "FROM notificacion "
					+ "INNER JOIN contexto ON notificacion.id_contexto=contexto.id "
					+ "INNER JOIN categoria ON notificacion.id_categoria=categoria.id "
					+ "INNER JOIN contenido ON notificacion.id_contenido=contenido.id "
					+ "INNER JOIN nino ON notificacion.id_nino=nino.id ");
/*					+ "INNER JOIN notificacion_etiqueta ON notificacion.id=notificacion_etiqueta.id_notificacion "
					+ "INNER JOIN etiqueta ON notificacion_etiqueta.id_etiqueta=etiqueta.id;" );*/
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}

	    
	    try {
			while (rs.next()){
				
				List<Etiqueta> listaEtiquetas = new ArrayList<Etiqueta>();		
				listaEtiquetas = getEtiquetasByIdNotificacion(rs.getInt("id"));

				Notificacion noti = new Notificacion(
										rs.getInt("id"),
										new Categoria (rs.getInt("id_categoria"),rs.getString("categoria")),
										new Contenido(rs.getInt("id_contenido"),rs.getString("contenido")),
										new Contexto(rs.getInt("id_contexto"),rs.getString("contexto")),
										new Nino(rs.getInt("id_nino"),rs.getString("nombre"),rs.getString("apellido")),
										rs.getString("fecha_recepcion"),
										rs.getString("fecha_envio"), listaEtiquetas
							);
							

				lista.add(noti);				
			}
			
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    return lista;
	}


	@Override
	public boolean guardarNotificacion(Notificacion n) {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public List<Etiqueta> getEtiquetasByIdNotificacion(int id) {
		BaseDeDatos database = new BaseDeDatos();
		Connection c;
	    Statement stmt = null;
	    ResultSet rs = null;
	    
	    List<Etiqueta> lista = new ArrayList<Etiqueta>();
	    
	    c = database.getConnection();
	    
	    try {
			stmt = c.createStatement();
		} catch (SQLException e) {
			System.out.println("Error al crear el statement");
			e.printStackTrace();
		}
	    try {
			rs = stmt.executeQuery( "SELECT notificacion.id, "
					+ "notificacion_etiqueta.id_etiqueta, etiqueta.texto AS etiqueta "   
					+ "FROM notificacion "
					+ "INNER JOIN notificacion_etiqueta ON notificacion.id=notificacion_etiqueta.id_notificacion "
					+ "INNER JOIN etiqueta ON notificacion_etiqueta.id_etiqueta=etiqueta.id "
					+ "WHERE notificacion.id = " + id + ";");
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}

	    
	    try {
			while (rs.next()){

				Etiqueta etiqueta = new Etiqueta(
							rs.getInt("id"), rs.getString("etiqueta")
							);
				lista.add(etiqueta);
			}
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    return lista;
	}
		
}

