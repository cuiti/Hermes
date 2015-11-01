package hermes.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import hermes.db.BaseDeDatos;
import hermes.model.Notificacion;

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
			rs = stmt.executeQuery( "SELECT * FROM notificacion"
					+ " INNER JOIN contexto ON notificacion.id_contexto=contexto.id"
					+ "INNER JOIN categoria ON notificacion.id_categoria=categoria.id"
					+ "INNER JOIN contenido ON notificacion.id_contenido=contenido.id"
					+ "INNER JOIN nino ON notificacion.id_nino=nino.id"
					+ "INNER JOIN notificacion_etiqueta ON notificacion.id=notificacion_etiqueta.id_notificacion"
					+ "INNER JOIN etiqueta ON notificacion_etiqueta.id_etiqueta=etiqueta.id;" );
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}

	    
	    try {
			while (rs.next()){
				Notificacion noti = new Notificacion(
										rs.getInt("id"),
										rs.getString("categoria.texto"),
										rs.getString("contenido.texto"),
										rs.getString("contexto.texto"),
										(rs.getString("nino.nombre")+rs.getString("nino.apellido")),
										rs.getDate("fecha_recepcion"),
										rs.getDate("fecha_envio")
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
		
}

