package hermes.dao;

import java.util.List;

import hermes.model.Categoria;

public interface ICategoriaDAO {
	public void asignarCategoria(Categoria categoria);
	public List<Categoria> listarCategorias();
}
