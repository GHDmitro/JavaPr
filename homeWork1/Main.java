package homeWork1;

/**
 * Created by macbookair on 15.02.16.
 */
public class Main {
    public static void main(String[] args) throws IllegalAccessException {
        TestClass testClass = new TestClass();


        String save = Serializer.serialize(testClass);

        System.out.println("Serialized : " + save);

        try {
            testClass = Serializer.deserialize(save, TestClass.class);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        }
        System.out.println("Number : " + testClass.getNumber() + ";  Str : " + testClass.getStr());
    }
}
