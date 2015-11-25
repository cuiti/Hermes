package hermes.anotaciones;

import java.io.*;
import java.net.*;
import java.util.List;

import com.google.gson.*;

public class Testeador {

	static int CANT_NOTIFICACIONES = 3;
	static String PORT = "8000";
	
	public static void main(String[] args) {
		
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create();
		
		
		String url = "http://localhost:"+PORT+"/hermes";
		
		OutputStream os = null;
		
		
		try {
			URL urlObject = new URL(url);
			HttpURLConnection conn;
			conn = (HttpURLConnection) urlObject.openConnection();
			conn.setReadTimeout(10000);
			
			os = conn.getOutputStream();
			
		} catch (IOException e1) {
			System.out.println("Error de IO en el testeador");
			e1.printStackTrace();
		}

		
		try {
			
			List<NotificacionDTO> lista = MockGenerator.createMockInstances(NotificacionDTO.class, CANT_NOTIFICACIONES);
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
			
			for (NotificacionDTO n: lista) {
				System.out.println(n.getNombre()+" "+n.getApellido()+" "+n.getFecha_envio());
				
				String notiJson = gson.toJson(n);
				System.out.println(notiJson);
				writer.write(notiJson);
			}
			
		} catch (Exception e) {
			System.out.println("Error en el Testeador");
			e.printStackTrace();
		}
	}

}
