package hermes.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import hermes.db.BaseDeDatos;
import hermes.model.*;

public class NotificacionDAO implements INotificacionDAO {

	@Override
	public List<Notificacion> listarNotificaciones() {
		BaseDeDatos db = new BaseDeDatos();
	    ResultSet rs = null;    
	    List<Notificacion> lista = new ArrayList<Notificacion>();
	    
	    String consulta = "SELECT notificacion.id, "
					+ "notificacion.fecha_envio, notificacion.fecha_recepcion, "
					+ "notificacion.id_contexto, contexto.texto AS contexto, "
					+ "notificacion.id_contenido, contenido.texto AS contenido, "
					+ "notificacion.id_categoria, categoria.texto AS categoria, "
					+ "notificacion.id_nino, nino.nombre, nino.apellido "  
					+ "FROM notificacion "
					+ "INNER JOIN contexto ON notificacion.id_contexto=contexto.id "
					+ "INNER JOIN categoria ON notificacion.id_categoria=categoria.id "
					+ "INNER JOIN contenido ON notificacion.id_contenido=contenido.id "
					+ "INNER JOIN nino ON notificacion.id_nino=nino.id ";
		
		rs = db.ejecutarConsulta(consulta);
	    
	    try {
			while (rs.next()){
				
				List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();		
				etiquetas = getEtiquetasByIdNotificacion(rs.getInt("id"));

				Notificacion notificacion = new Notificacion(
										rs.getInt("id"),
										new Categoria (rs.getInt("id_categoria"),rs.getString("categoria")),
										new Contenido(rs.getInt("id_contenido"),rs.getString("contenido")),
										new Contexto(rs.getInt("id_contexto"),rs.getString("contexto")),
										new Nino(rs.getInt("id_nino"),rs.getString("nombre"),rs.getString("apellido")),
										rs.getString("fecha_recepcion"),
										rs.getString("fecha_envio"), etiquetas
							);
							
				lista.add(notificacion);				
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
		BaseDeDatos db = new BaseDeDatos();
	    ResultSet rs = null;
	    
	    List<Etiqueta> lista = new ArrayList<Etiqueta>();
	    
	    String consulta = "SELECT notificacion.id, "
	    		+ "notificacion_etiqueta.id_etiqueta, etiqueta.texto AS etiqueta "   
				+ "FROM notificacion "
				+ "INNER JOIN notificacion_etiqueta ON notificacion.id=notificacion_etiqueta.id_notificacion "
				+ "INNER JOIN etiqueta ON notificacion_etiqueta.id_etiqueta=etiqueta.id "
				+ "WHERE notificacion.id = " + id + ";";

	    rs = db.ejecutarConsulta(consulta);
	    
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

	@Override
	public List<Notificacion> filtrarNotificaciones(String fecha_desde, String fecha_hasta, Contenido contenido,
			Contexto contexto, Categoria categoria, Nino nino, Etiqueta etiqueta) {
		
		List<Notificacion> notificaciones = listarNotificaciones();
		List<Notificacion> lista = new ArrayList<Notificacion>();
		
		for (Notificacion n: notificaciones) {
			boolean encontre = false; 
			
			for (Etiqueta e: n.getEtiquetas()) //FIXME :)
				if (e.equals(etiqueta))
					encontre = true;
			
			if (encontre 
					& n.getContenido().equals(contenido) 
					& n.getContexto().equals(contexto) 
					& n.getCategoria().equals(categoria) 
					& n.getNino().equals(nino)) { 
				lista.add(n);
			}
		}
		return lista;			
	}
		
}

