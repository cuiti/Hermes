package hermes.anotaciones;

import java.util.*;
import java.lang.reflect.*;

public class MockGenerator {
	public static <T> List<T> createMockInstances(Class<T> elClass, int cant) throws Exception {
		List<T> lista = new ArrayList<T>();
		for (int i=0; i < cant; i++) {
			Constructor<T> constructor = elClass.getConstructor();
			T notificacion = (T) constructor.newInstance();
			Field[] fields =  elClass.getDeclaredFields();
			for (Field f : fields){
				if (f.isAnnotationPresent(MockStringAttribute.class)) {
					String[] valores = f.getAnnotation(MockStringAttribute.class).value();
					String valor = valores[random(0, valores.length)];
					String method = "set" + (f.getName().charAt(0)+"").toUpperCase() + f.getName().substring(1);
					Method m = elClass.getMethod(method, String.class);
					m.invoke(notificacion, valor);
				} else {
					if (f.isAnnotationPresent(MockTodayAttribute.class)) {
						String method = "set" + (f.getName().charAt(0)+"").toUpperCase() + f.getName().substring(1);
						Date date = new Date();
						Method m = elClass.getMethod(method, String.class);
						m.invoke(notificacion, date.toString());
					}
				}
			}			
			lista.add(notificacion);
		}
		return lista;
	}

	
	public static int random(int low, int high) {
		Random r = new Random();
		return r.nextInt(high-low) + low;
	}
}

