package hermes.dao;

public class FactoriaDAO {
	public static ICategoriaDAO getCategoriaDAO() {
		return new CategoriaDAO();
	}
	
	public static IContenidoDAO getContenidoDAO() {
		return new ContenidoDAO();
	}
	
	public static IContextoDAO getContextoDAO() {
		return new ContextoDAO();
	}
	
	public static INinoDAO getNinoDAO() {
		return new NinoDAO();
	}
}
