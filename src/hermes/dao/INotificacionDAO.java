package hermes.dao;

import java.util.List;

import hermes.model.Notificacion;

public interface INotificacionDAO {
	
	public List<Notificacion> listarNotificaciones();
	public boolean guardarNotificacion(Notificacion n);


}
