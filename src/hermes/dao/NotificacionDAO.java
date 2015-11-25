package hermes.dao;

import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;


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
		
	    db.open();
		rs = db.ejecutarConsulta(consulta);
	    
	    try {
			while (rs.next()){
				
				List<Etiqueta> etiquetas = new ArrayList<Etiqueta>();		
				etiquetas = getEtiquetasByIdNotificacion(rs.getInt("id"));
				
				SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");

				Notificacion notificacion;
				try {
					notificacion = new Notificacion(
											rs.getInt("id"),
											new Categoria (rs.getInt("id_categoria"),rs.getString("categoria")),
											new Contenido(rs.getInt("id_contenido"),rs.getString("contenido")),
											new Contexto(rs.getInt("id_contexto"),rs.getString("contexto")),
											new Nino(rs.getInt("id_nino"),rs.getString("nombre"),rs.getString("apellido")),
											formatter.parse(rs.getString("fecha_recepcion")),
											formatter.parse(rs.getString("fecha_envio")), etiquetas
								);				
					lista.add(notificacion);
					
				} catch (ParseException e) {
					System.out.println("Error al parsear fecha");
					e.printStackTrace();
				}
															
			}
			
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    
	    db.close();
	    return lista;
	}

	@Override
	public boolean guardarNotificacion(Notificacion n) {
		SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm");
		BaseDeDatos db = new BaseDeDatos();
	    String query = "INSERT INTO notificacion (id_contenido, id_contexto, id_categoria, fecha_envio, fecha_recepcion, id_nino) "
	    		+ "VALUES ("		
	    		+ n.getContenido().getId() + ","
	    		+ n.getContexto().getId() + ","
	    		+ n.getCategoria().getId() + ",'"
	    		+ formatter.format(n.getFecha_envio().toString()) + "','"
	    		+ formatter.format(n.getFecha_recepcion()) + "',"
	    		+ n.getNino().getId()
	    		+ ");";
		return db.ejecutarABM(query);
	}

	@Override
	public List<Etiqueta> getEtiquetasByIdNotificacion(int id) {
		BaseDeDatos db = new BaseDeDatos();
	    ResultSet rs = null;
	    
	    List<Etiqueta> lista = new ArrayList<Etiqueta>();
	    
	    String consulta = "SELECT * "
	    				+ "FROM notificacion_etiqueta "
	    				+ "INNER JOIN etiqueta ON notificacion_etiqueta.id_etiqueta = etiqueta.id "
	    				+ "WHERE notificacion_etiqueta.id_notificacion = " + id + "";
	    db.open();
	    rs = db.ejecutarConsulta(consulta);
	    
	    try {
			while (rs.next()){

				Etiqueta etiqueta = new Etiqueta(
							rs.getInt("id_notificacion"), rs.getString("texto")
							);
				lista.add(etiqueta);
			}
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    db.close();
	    return lista;
	}

	public List<Notificacion> filtrarNotificaciones(Date fecha_desde, Date fecha_hasta, Contenido contenido,
			Contexto contexto, Categoria categoria, Nino nino, Etiqueta etiqueta) {
		
		List<Notificacion> notificaciones= listarNotificaciones();
		
		if (etiqueta.getId()>0) notificaciones=filtroEtiquetas(notificaciones, etiqueta);
		
		if (contenido.getId()>0) notificaciones=filtroContenido(notificaciones, contenido);
		
		if (contexto.getId()>0) notificaciones=filtroContexto(notificaciones, contexto);
		
		if (categoria.getId()>0) notificaciones=filtroCategoria(notificaciones, categoria);
		
		if (nino.getId()>0) notificaciones=filtroNino(notificaciones, nino);
		
		notificaciones= filtroFechas(notificaciones, fecha_desde, fecha_hasta);
		
		return notificaciones;			
	}
	
	public List<Notificacion> filtroEtiquetas(List<Notificacion> notificaciones, Etiqueta etiqueta ){
		List<Notificacion> lista = new ArrayList<Notificacion>();
		
		boolean encontre = false;
		
		for (Notificacion n: notificaciones){
			for (Etiqueta e: n.getEtiquetas()) 
				if (e.equals(etiqueta))
					encontre = true;
			if (encontre){
				lista.add(n);
			}
		}
		
		return lista;		
	}
		
	public List<Notificacion> filtroContenido(List<Notificacion> notificaciones, Contenido contenido){
		List<Notificacion> lista = new ArrayList<Notificacion>();
		
		for (Notificacion n: notificaciones){
			if(n.getContenido().equals(contenido)){
				lista.add(n);
			}
		}
		
		return lista;
	}
	
	public List<Notificacion> filtroContexto(List<Notificacion> notificaciones, Contexto c){
		List<Notificacion> lista = new ArrayList<Notificacion>();
		
		for (Notificacion n: notificaciones){
			if(n.getContexto().equals(c)){
				lista.add(n);
			}
		}
		return lista;
	}
	
	public List<Notificacion> filtroCategoria(List<Notificacion> notificaciones, Categoria c){
		List<Notificacion> lista = new ArrayList<Notificacion>();
		
		for (Notificacion n: notificaciones){
			if(n.getCategoria().equals(c)){
				lista.add(n);
			}
		}
		return lista;
	}
	
	public List<Notificacion> filtroNino(List<Notificacion> notificaciones, Nino nino){
		List<Notificacion> lista = new ArrayList<Notificacion>();
		
		for (Notificacion n: notificaciones){
			if(n.getNino().equals(nino)){
				lista.add(n);
			}
		}
		return lista;
	}
	
	public List<Notificacion> filtroFechas(List<Notificacion> notificaciones, Date desde, Date hasta){
		List<Notificacion> lista = new ArrayList<Notificacion>();
		
		for (Notificacion n: notificaciones){
			if(n.getFecha_envio().after(desde) && n.getFecha_envio().before(hasta)){
				lista.add(n);
			}
		}
		return lista;
	}
}

