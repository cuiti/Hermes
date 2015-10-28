package hermes.model;

import java.util.Date;

public class Notificacion {
	private int id;
	private String categoria;
	private String contenido;
	private String contexto;
	private String nombre;
	private Date fecha_recepcion;
	private Date fecha_envio;
	
	public Notificacion(int id, String categoria, String contenido, String contexto, String nombre,
			Date fecha_recepcion, Date fecha_envio) {
		this.id = id;
		this.categoria = categoria;
		this.contenido = contenido;
		this.contexto = contexto;
		this.nombre = nombre;
		this.fecha_recepcion = fecha_recepcion;
		this.fecha_envio = fecha_envio;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getCategoria() {
		return categoria;
	}

	public void setCategoria(String categoria) {
		this.categoria = categoria;
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

	public String getNombre() {
		return nombre;
	}

	public void setNombre(String nombre) {
		this.nombre = nombre;
	}

	public Date getFecha_recepcion() {
		return fecha_recepcion;
	}

	public void setFecha_recepcion(Date fecha_recepcion) {
		this.fecha_recepcion = fecha_recepcion;
	}

	public Date getFecha_envio() {
		return fecha_envio;
	}

	public void setFecha_envio(Date fecha_envio) {
		this.fecha_envio = fecha_envio;
	}
	



}
