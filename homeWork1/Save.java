package homeWork1;

import java.lang.annotation.*;

/**
 * Created by macbookair on 13.02.16.
 */
@Inherited
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
@interface Save {
}
