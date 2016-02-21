package homeWork1;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.security.InvalidParameterException;

/**
 * Created by macbookair on 15.02.16.
 */
public class Serializer {

    public static String serialize(Object o) throws IllegalAccessException {
        Class<?> cls = o.getClass();
        StringBuffer sb = new StringBuffer();
        Field[] fields = cls.getDeclaredFields();
        for (Field f : fields) {
            if (!f.isAnnotationPresent(Save.class)) {
                continue;
            }
            if (Modifier.isPrivate(f.getModifiers())) {
                f.setAccessible(true);
            }
            if (f.getType() == int.class) {
                sb.append(f.getName()).append("=").append(f.getInt(o)).append(";");
            } else if (f.getType() == String.class) {
                sb.append(f.getName()).append("=").append(f.get(o)).append(";");
            }
        }
        return sb.toString();
    }

    public static <T> T deserialize(String s, Class<T> cls) throws ClassNotFoundException, IllegalAccessException, InstantiationException, NoSuchFieldException {
        T clT = (T) cls.newInstance();

        String[] pairs = s.split(";");

        for (String p : pairs) {
            String[] singlePair = p.split("=");
            if (singlePair.length != 2) {
                throw new InvalidParameterException();
            }
            String name = singlePair[0];
            String value = singlePair[1];

            Field f = cls.getDeclaredField(name);

            if (!f.isAnnotationPresent(Save.class)) {
                continue;
            }
            if (Modifier.isPrivate(f.getModifiers())) {
                f.setAccessible(true);
            }

            if (f.getType() == int.class) {
                f.setInt(clT, Integer.valueOf(value));
            } else if (f.getType() == String.class) {
                f.set(clT, value);
            }
        }
        return clT;
    }
}























