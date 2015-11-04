package hermes.dao;

import java.util.List;

import hermes.model.Categoria;
import hermes.model.Contenido;
import hermes.model.Contexto;
import hermes.model.Etiqueta;
import hermes.model.Nino;
import hermes.model.Notificacion;

public interface INotificacionDAO {
	
	public List<Notificacion> listarNotificaciones();
	public boolean guardarNotificacion(Notificacion n);
	public List<Etiqueta> getEtiquetasByIdNotificacion(int id);
	public List<Notificacion> filtrarNotificaciones(String fecha_desde, String fecha_hasta, 
			Contenido contenido, Contexto contexto, Categoria categoria, Nino nino, Etiqueta etiqueta);


}
