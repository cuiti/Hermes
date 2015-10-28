package hermes.dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import hermes.db.Conexion;
import hermes.model.Notificacion;

public class NotificacionDAO implements INotificacionDAO {

	public NotificacionDAO() {
		
	}
		    
//	public static void main (String[] args){
//		NotificacionDAO n = new NotificacionDAO();
//		n.listarNotificaciones();
//	}

	@Override
	public List<Notificacion> listarNotificaciones() {
		Conexion database = new Conexion();
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
			rs = stmt.executeQuery( "SELECT * FROM notificacion INNER JOIN contexto ON notificacion.id_contexto=contexto.id;" );
		} catch (SQLException e) {
			System.out.println("Error al ejecutar el statement");
			e.printStackTrace();
		}

	    
	    try {
			while (rs.next()){
				Notificacion noti = new Notificacion();
				noti.setFecha_recepcion(rs.getDate("fecha_recepcion"));
				System.out.println(noti.getFecha_recepcion());
				//noti.setCategoria(categoria);
				
				lista.add(noti);
			}
		} catch (SQLException e) {
			System.out.println("Error al acceder a la base de datos SQLite");
			e.printStackTrace();
		}
	    return null;
	}


	@Override
	public boolean guardarNotificacion(Notificacion n) {
		// TODO Auto-generated method stub
		return false;
	}
		
}

