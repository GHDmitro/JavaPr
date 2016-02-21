package lesson1;

import java.lang.annotation.*;

/**
 * Created by macbookair on 09.02.16.
 */

@Inherited
@Target(value = ElementType.METHOD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface Saver {
}
