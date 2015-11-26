package hermes.http;

import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;
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
		
		System.out.println("entro al handler");
		InputStream is = http.getRequestBody();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String json = br.readLine();
		//br.close();
		
		is.close();
		
		List<NotificacionDTO> listaNotiDTO = gson.fromJson(json,new TypeToken<List<NotificacionDTO>>(){}.getType());
		
		for (NotificacionDTO notiDTO : listaNotiDTO) {
		
			Contexto contexto = contextoDAO.getContextoByNombre(notiDTO.getContexto());
			if (contexto.getId() == -1){
				Contexto nuevoContexto = new Contexto(0,notiDTO.getContexto());
				contextoDAO.guardarContexto(nuevoContexto);
				contexto = nuevoContexto;
			}
			System.out.println(contexto.getTexto());
			
			Categoria categoria = categoriaDAO.getCategoriaByNombre(notiDTO.getCategoria());
			if (categoria.getId() == -1){
				Categoria nuevaCategoria = new Categoria(0,notiDTO.getCategoria());
				categoriaDAO.guardarCategoria(nuevaCategoria);
				categoria = nuevaCategoria;
			}
			System.out.println(categoria.getTexto());
			
			Nino nino = ninoDAO.getNinoByNombre(notiDTO.getNombre(), notiDTO.getApellido());
			if (nino.getId() == -1){
				Nino nuevoNino = new Nino(0,notiDTO.getNombre(),notiDTO.getApellido());
				ninoDAO.guardarNino(nuevoNino);
				nino = nuevoNino;
			}
			System.out.println(nino.getNombre());
			
			Contenido contenido = contenidoDAO.getContenidoByNombre(notiDTO.getContenido());
			if (contenido.getId() == -1){
				Contenido nuevoContenido = new Contenido(0,notiDTO.getContenido());
				contenidoDAO.guardarContenido(nuevoContenido);
				contenido = nuevoContenido;
			}
			System.out.println(contenido.getTexto());
			
			List<Etiqueta> etiquetas = new ArrayList<Etiqueta>(); //las notificaciones nuevas empiezan con una lista vacia de etiquetas
			
			
			nuevaNotificacion = new Notificacion(0, categoria, contenido, contexto, nino, new Date(notiDTO.getFecha_envio()), new Date(), etiquetas);
			System.out.println(gson.toJson(nuevaNotificacion));
			notiDAO.guardarNotificacion(nuevaNotificacion);
		}
		
		String response = "Esta es la respuesta";
		
		http.sendResponseHeaders(200, response.length());
		OutputStream os = http.getResponseBody();
		os.write(response.getBytes());
		os.close();
		

	}

}
