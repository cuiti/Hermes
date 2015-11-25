package hermes.http;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.sun.net.httpserver.*;

import hermes.anotaciones.NotificacionDTO;
import hermes.dao.*;
import hermes.model.*;

public class RequestHandler implements HttpHandler{

	public RequestHandler() {
		
	}
	
	Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create();
	
	@Override
	public void handle(HttpExchange http) throws IOException {
		IContextoDAO contextoDAO = FactoriaDAO.getContextoDAO();
		ICategoriaDAO categoriaDAO = FactoriaDAO.getCategoriaDAO();
		INinoDAO ninoDAO = FactoriaDAO.getNinoDAO();
		IContenidoDAO contenidoDAO = FactoriaDAO.getContenidoDAO();
		INotificacionDAO notiDAO = FactoriaDAO.getNotificacionDAO();
		
		Notificacion nuevaNotificacion;
		
		InputStream is = http.getRequestBody();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String json = br.readLine();

		
		NotificacionDTO notiDTO = gson.fromJson(json, NotificacionDTO.class);
		
		Contexto contexto = contextoDAO.getContextoByNombre(notiDTO.getContexto());
		if (contexto.getId() == -1){
			Contexto nuevoContexto = new Contexto(0,contexto.getTexto());
			contextoDAO.guardarContexto(nuevoContexto);
			contexto = nuevoContexto;
		}
		
		Categoria categoria = categoriaDAO.getCategoriaByNombre(notiDTO.getCategoria());
		if (categoria.getId()==-1){
			Categoria nuevaCategoria = new Categoria(0,categoria.getTexto());
			categoriaDAO.guardarCategoria(nuevaCategoria);
			categoria = nuevaCategoria;
		}
		
		Nino nino = ninoDAO.getNinoByNombre(notiDTO.getNombre(), notiDTO.getApellido());
		if (nino.getId()==-1){
			Nino nuevoNino = new Nino(0,nino.getNombre(),nino.getApellido());
			ninoDAO.guardarNino(nuevoNino);
			nino = nuevoNino;
		}
		
		Contenido contenido = contenidoDAO.getContenidoByNombre(notiDTO.getContenido());
		if (contenido.getId() == -1){
			Contenido nuevoContenido = new Contenido(0,contenido.getTexto());
			contenidoDAO.guardarContenido(nuevoContenido);
			contenido = nuevoContenido;
		}
		
		List<Etiqueta> etiquetas = new ArrayList<Etiqueta>(); //las notificaciones nuevas empiezan con una lista vacia de etiquetas
		
		nuevaNotificacion = new Notificacion(0, categoria, contenido, contexto, nino, new Date(notiDTO.getFecha_envio()), new Date(), etiquetas);
		notiDAO.guardarNotificacion(nuevaNotificacion);
		
		String response = "Esta es la respuesta";
		http.sendResponseHeaders(200, response.length());
		OutputStream os = http.getResponseBody();
		os.write(response.getBytes());
		os.close();
	}

}
