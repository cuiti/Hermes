package hermes.dao;

import hermes.model.Contexto;

import java.util.List;

public interface IContextoDAO {
	public List<Contexto> listarContextos();
	public Contexto getContextoByNombre(String s);
	public boolean guardarContexto(Contexto c);
}
