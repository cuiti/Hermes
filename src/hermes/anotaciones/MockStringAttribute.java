package hermes.anotaciones;

import java.lang.annotation.ElementType;
import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME) 
@Target(ElementType.FIELD)
public @interface MockStringAttribute { 
	String[] value();
}
