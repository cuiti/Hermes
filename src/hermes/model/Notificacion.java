package hermes.model;

import java.util.Date;

public class Notificacion {
	private int id;
	private Categoria categoria;
	private Contenido contenido;
	private Contexto contexto;
	private Nino nino;
	private Date fecha_recepcion;
	private Date fecha_envio;
	


	public Notificacion(int id, Categoria categoria, Contenido contenido, Contexto contexto, Nino nino,
			Date fecha_recepcion, Date fecha_envio) {
		super();
		this.id = id;
		this.categoria = categoria;
		this.contenido = contenido;
		this.contexto = contexto;
		this.nino = nino;
		this.fecha_recepcion = fecha_recepcion;
		this.fecha_envio = fecha_envio;
	}

	public Notificacion() {
		// constructor vacío por ahora
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Categoria getCategoria() {
		return categoria;
	}

	public void setCategoria(Categoria categoria) {
		this.categoria = categoria;
	}

	public Contenido getContenido() {
		return contenido;
	}

	public void setContenido(Contenido contenido) {
		this.contenido = contenido;
	}

	public Contexto getContexto() {
		return contexto;
	}

	public void setContexto(Contexto contexto) {
		this.contexto = contexto;
	}

	public Nino getNino(){
		return nino;
	}
	
	public void setNino(Nino nino) {
		this.nino = nino;
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
