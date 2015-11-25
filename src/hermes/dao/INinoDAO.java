package hermes.dao;

import hermes.model.Nino;

import java.util.List;

public interface INinoDAO {
	public List<Nino> listarNinos();
	public Nino getNinoByNombre(String nombre, String apellido);
	public boolean guardarNino(Nino n);
}
