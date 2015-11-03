package hermes.db;

import java.io.*;
import java.util.List;

import com.google.gson.Gson;

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
			Gson gson = new Gson();
			
			List<Notificacion> lista = FactoriaDAO.getNotificacionDAO().listarNotificaciones();
			
			System.out.println(gson.toJson(lista.get(0)));
			
			while ((lineaActual = br.readLine()) != null) {
				System.out.println(lineaActual);
				
			}
			
			br.close();
			return true;
		}catch (IOException e){
			e.printStackTrace();
			return false;
		}
	}

}
