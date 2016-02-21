package lesson1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by macbookair on 09.02.16.
 */

public class Test2 {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<?> src = MyTest.class;

        if (!src.isAnnotationPresent(SaveTo.class)) {
            System.out.println("Error!");
            return;
        }
        SaveTo path = src.getAnnotation(SaveTo.class);
        Method[] meth = src.getMethods();

        for (Method m : meth) {

            if (m.isAnnotationPresent(Saver.class)) {
                MyTest myTest = new MyTest();
                m.invoke(myTest, path.path());
            }
        }

    }


}

@SaveTo(path = "/Users/macbookair/JavaTests/JavaTest.txt")
class MyTest {
    String text = "Hallow world";

    @Saver
    public void save(String path) throws IOException {

        try {
            FileWriter writer = new FileWriter(new File(path));
            writer.write(text);
            writer.close();
            System.out.println("Well");
        } catch (IOException e) {
            e.printStackTrace();
        }


    }
}
