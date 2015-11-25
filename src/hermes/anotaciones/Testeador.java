package hermes.anotaciones;

import java.util.List;

public class Testeador {

	public static void main(String[] args) {
		try {
			List<NotificacionDTO> lista = MockGenerator.createMockInstances(NotificacionDTO.class, 10);
			for (NotificacionDTO n: lista) {
				System.out.println(n.getNombre()+" "+n.getApellido()+" "+n.getFecha_envio());
			}
		} catch (Exception e) {
			System.out.println("Error en el Testeador");
			e.printStackTrace();
		}
	}

}
