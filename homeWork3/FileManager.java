package homeWork3;

import java.io.*;
import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;

//
public class FileManager {

    private int number = 1;
    private long time = 2000;
    private String path;
    //потобезопастный hashmap
    //key = url , [] значения
    private static ConcurrentHashMap<String, byte[]> map = new ConcurrentHashMap<String, byte[]>();

    public FileManager(String path) {
//        // "c:\folder\" --> "c:\folder"
        if (path.endsWith("/") || path.endsWith("\\"))
            path = path.substring(0, path.length() - 1);


        this.path = path;
    }

    public byte[] get(String url) {

        try {
            byte[] buf = map.get(url);
            if (buf != null) {// in cache
                return buf;
            }

            // "c:\folder" + "/index.html" -> "c:/folder/index.html"
            String fullPath = path.replace('\\', '/') + url; // // путь к файлу
            //вычитываем его в память  "r" read
            RandomAccessFile f = new RandomAccessFile(fullPath, "r"); //подается файл для чтения  в f
            try {

                buf = new byte[(int) f.length()];
                f.read(buf, 0, buf.length);
            } finally {
                f.close();
            }

            //подачча в кеш
            map.put(url, buf); // put to cache

//------------Task 1 : Control time life of url objects in cash --------------------------
            final byte[] finalBuf = buf;
            System.out.println("\nTime when alive " + number + " : " + System.currentTimeMillis());
            System.out.println("Size map while alive  : " + FileManager.map.size());
            Thread t = new Thread(() -> {
                try {
                    Thread.currentThread();
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    System.out.println("\nRemoving object " + number + " : " + FileManager.map.remove(url).toString());
                    System.out.println("Size map while die  : " + FileManager.map.size());
                    System.out.println("Time after removing  : " + System.currentTimeMillis());
                }
                Thread.currentThread().interrupt();
            });
            t.start();
//-----------------------------------------------------------------------------------------
            number++;
            return buf;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

}
