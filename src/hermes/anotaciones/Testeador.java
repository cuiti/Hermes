package hermes.anotaciones;

import java.util.List;
import org.json.simple.JSONObject;

public class Testeador {

	public static void main(String[] args) {
/*		try {
			List<NotificacionDTO> lista = MockGenerator.createMockInstances(NotificacionDTO.class, 10);
			for (NotificacionDTO n: lista) {
				System.out.println(n.getNombre()+" "+n.getApellido()+" "+n.getFecha_envio());
			}
		} catch (Exception e) {
			System.out.println("Error en el Testeador");
			e.printStackTrace();
		}*/
		try {
			List<NotificacionDTO> lista = MockGenerator.createMockInstances(NotificacionDTO.class, 10);
			for (NotificacionDTO n: lista) {
				JSONObject jsonNotificacion = new JSONObject();
				jsonNotificacion.put("fecha_envio", n.getFecha_envio());
				jsonNotificacion.put("contenido", n.getContenido());
				jsonNotificacion.put("contexto", n.getContexto());
				jsonNotificacion.put("categoria", n.getCategoria());
				jsonNotificacion.put("nombre", n.getNombre());
				jsonNotificacion.put("apellido", n.getApellido());
				//System.out.println(jsonNotificacion);
				//requestMonitor(jsonNotificacion.toJSONString());
			}
		} catch (Exception e) {
			System.out.println("Error en Test");
			e.printStackTrace();
		}
	}

}
