package homeWork1;

import java.io.*;
import java.lang.reflect.Field;

class Saver implements Serializable {
    @Save
    int d = 1;

    int b = 2;

    @Save
    int c = 3;


    public int getA() {
        return d;
    }

    public int getB() {
        return b;
    }

    public int getC() {
        return c;
    }
}

public class TesterSaving {


    public static void main(String[] args) throws IOException, IllegalAccessException {
        String fileName = "/Users/macbookair/JavaTests/t.txt";
        Class<?> cls = Saver.class;

        Field[] fields = cls.getDeclaredFields();

//        if (fields == null){
//            System.out.println("cnjpdof");
//        }
        Saver saver = new Saver();
        System.out.println("a = " + saver.getA() + "; " + "b = " + saver.getB() + "; c = " + saver.getC());
        FileWriter fw = new FileWriter(new File(fileName));
        for (Field f : fields) {

            if (f.isAnnotationPresent(Save.class)) {
//                Save s = f.getAnnotation(Save.class);
                f.setAccessible(true);
                String name = f.getName();
                System.out.println(name);
                fw.write(name + "=" + f.get(saver).toString() + "\n");

//                saveInFile(fileName, saver, f);
            } else System.out.println("false");
        }
        fw.flush();
        StringBuffer sb = new StringBuffer();
        ;
        try {

            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
                sb.append(" ");
            }
            fr.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (Field f : fields) {
            if (f.isAnnotationPresent(Save.class)) {
                f.setAccessible(true);
                String name = f.getName();

                int index = sb.indexOf(name);
//                System.out.println();
                System.out.println(sb.substring(index, sb.indexOf(" ", index) + 1));
            }
        }

//        getFromFile(fileName, cls , saver);
    }

    static void saveInFile(String fileName, Saver saver, Field f) {

        try {
            FileWriter fw = new FileWriter(new File(fileName));
            f.setAccessible(true);
            int n = (Integer) f.get(saver);
            n += 10;
            String name = f.getName();
            f.set(saver, n);
            fw.write(name + "=" + (Integer) f.get(saver));
            fw.close();

        } catch (IOException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }
    }

    static void getFromFile(String fileName, Class<?> cls, Saver saver) {
//        List<Integer> list = new LinkedList<>();
        StringBuffer sb = new StringBuffer();
        try {
            FileReader fr = new FileReader(fileName);
            BufferedReader br = new BufferedReader(fr);
            String s;
            while ((s = br.readLine()) != null) {
                sb.append(s);
                sb.append(" ");
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        Field[] fields = cls.getFields();
        for (Field f : fields) {
            if (f.isAnnotationPresent(Save.class)) {
                String name = f.getName();
                int index = sb.indexOf(name);
                System.out.println(sb.substring(index, sb.indexOf(" ", index)));
            }
        }


    }


}





