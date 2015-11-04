package hermes.dao;

import java.util.*;

import hermes.model.*;

public interface INotificacionDAO {
	
	public List<Notificacion> listarNotificaciones();
	public boolean guardarNotificacion(Notificacion n);
	public List<Etiqueta> getEtiquetasByIdNotificacion(int id);
	public List<Notificacion> filtrarNotificaciones(Date fecha_desde, Date fecha_hasta, 
			Contenido contenido, Contexto contexto, Categoria categoria, Nino nino, Etiqueta etiqueta);


}
