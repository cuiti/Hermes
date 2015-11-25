package hermes.dao;

import hermes.model.Contenido;

import java.util.List;

public interface IContenidoDAO {
	public List<Contenido> listarContenidos();
	public Contenido getContenidoByNombre(String nombre);
	public boolean guardarContenido(Contenido c);
}
