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
		
		HttpURLConnection conn=null;

		try {
			URL urlObject = new URL(url);
			conn = (HttpURLConnection) urlObject.openConnection();
			
				
			conn.setReadTimeout(100000);
			conn.setDoOutput(true);
			os = conn.getOutputStream();
			
			
			
		} catch (IOException e1) {
			System.out.println("Error de IO en el testeador");
			e1.printStackTrace();
		}

		
		try {
			
			List<NotificacionDTO> lista = MockGenerator.createMockInstances(NotificacionDTO.class, CANT_NOTIFICACIONES);
			
			BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os));
			
			System.out.println("Notificaciones generadas:");
			for (NotificacionDTO n: lista) {
				System.out.println(n.getNombre()+" "+n.getApellido()+" - "+n.getCategoria()+" - "+n.getContexto());
			}
			
			String notiJson = gson.toJson(lista);
			System.out.println(notiJson);
			writer.write(notiJson);
			
			
			writer.close();
			os.close();
			int code = conn.getResponseCode();
			System.out.println("code:"+code);
			//writer.flush();
			//BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
			//String inputLine;
			//while ((inputLine = in.readLine()) != null) {
			//	System.out.println(inputLine);
			//}
			//in.close();
			//System.out.println(conn.getResponseMessage());

			
		} catch (Exception e) {
			System.out.println("Error en el Testeador");
			e.printStackTrace();
		}

	}

}
