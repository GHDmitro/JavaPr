package lesson1;

import java.lang.annotation.*;

/**
 * Created by macbookair on 09.02.16.
 */

@Inherited
@Target(value = ElementType.TYPE)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SaveTo {
    String path();
}
