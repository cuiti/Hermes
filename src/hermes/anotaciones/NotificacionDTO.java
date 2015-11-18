package hermes.anotaciones;

@Mock
public class NotificacionDTO {
	@MockStringAttribute ({"descriptivo1", "descriptivo2", "descriptivo3"})
	private String contenido;
	
	@MockStringAttribute ({"EstabloTerapia", "Pista", "Hogar"})
	private String contexto;
	
	@MockStringAttribute ({"Predeterminada", "Emociones", "Alimentos", "Actividades y Paseos"})
	private String categoria;
	
	@MockStringAttribute({"Juan", "Pedro", "Juana", "Manuela"})
	private String nombre;
	
	@MockStringAttribute({"Cuitino", "Rodriguez", "Gomez", "Olsowy"})
	private String apellido;
	
	@MockTodayAttribute
	private String fecha_envio;

	
	public NotificacionDTO() {
		// TODO Auto-generated constructor stub
	}

	public String getContenido() {
		return contenido;
	}

	public void setContenido(String contenido) {
		this.contenido = contenido;
	}

	public String getContexto() {
		return contexto;
	}

	public void setContexto(String contexto) {
		this.contexto = contexto;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
	}

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public String getApellido() {
		return apellido;
	}

	public void setApellido(String apellido) {
		this.apellido = apellido;
	}

	public String getFecha_envio() {
		return fecha_envio;
	}

	public void setFecha_envio(String fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	
}
