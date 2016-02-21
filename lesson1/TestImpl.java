package lesson1;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * Created by macbookair on 09.02.16.
 */

public class TestImpl {
    public static void main(String[] args) throws InvocationTargetException, IllegalAccessException {
        Class<?> src = test1.class;
        Method[] meth = src.getMethods();

        for (Method m : meth) {
            if (m.isAnnotationPresent(Test.class)) {

                Test n = m.getAnnotation(Test.class);
                test1 test = new test1();
                m.invoke(test, n.a(), n.b());
            }

        }
    }
}


class test1 {
    @Test(a = 2, b = 3)
    public void test123(int a, int b) {
        System.out.println(a + "  " + b);
    }
}
