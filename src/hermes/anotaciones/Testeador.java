package hermes.anotaciones;

import java.util.List;

import com.google.gson.*;

public class Testeador {

	public static void main(String[] args) {
		
		Gson gson = new GsonBuilder().setDateFormat("dd/MM/yyyy HH:mm").create();
		
		try {
			List<NotificacionDTO> lista = MockGenerator.createMockInstances(NotificacionDTO.class, 10);
			for (NotificacionDTO n: lista) {
				System.out.println(n.getNombre()+" "+n.getApellido()+" "+n.getFecha_envio());
				
				String notiJson = gson.toJson(n);
				System.out.println(notiJson);
				//enviar notiJson
			}
		} catch (Exception e) {
			System.out.println("Error en el Testeador");
			e.printStackTrace();
		}
	}

}
