package hermes.dao;

import java.util.List;

import hermes.model.Categoria;
import hermes.model.Contexto;

public interface ICategoriaDAO {
	public void asignarCategoria(Categoria categoria);
	public List<Categoria> listarCategorias();
	public Categoria getCategoriaByNombre(String s);
}
