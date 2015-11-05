package hermes.db;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import hermes.dao.FactoriaDAO;
import hermes.dao.INotificacionDAO;
import hermes.model.*;


public class LecturaJSON {

	public LecturaJSON() {
	}
	
	public boolean cargarNotificaciones(String nombreArchivo){
		String lineaActual="";
		try{
			String dir = System.getProperty("user.dir");
			File archivo = new File(dir+"/"+nombreArchivo);
			BufferedReader br = new BufferedReader(new FileReader(archivo));
			Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create();
			
//			List<Notificacion> lista = new ArrayList<Notificacion>();
//			lista = FactoriaDAO.getNotificacionDAO().listarNotificaciones();
//			for (Notificacion n : lista){
//				System.out.println(gson.toJson(n));
//				
//			}
			
			Notificacion notificacion;
			while ((lineaActual = br.readLine()) != null) {
				
				notificacion = gson.fromJson(lineaActual, Notificacion.class);
				
				//FactoriaDAO.getNotificacionDAO().guardarNotificacion(notificacion);
			}
			
			br.close();
			return true;
		}catch (IOException e){
			System.out.println("Se debe tener el archivo notificaciones.txt en el mismo directorio donde se ejecute el programa");
			e.printStackTrace();
			return false;
		}
	}

}
