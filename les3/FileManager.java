package les3;

import java.io.*;
import java.util.concurrent.ConcurrentHashMap;

//
public class FileManager {
//    private long firstTime;
//    private long lastTime;


    private long time = 3000;
    private String path;
    //потобезопастный hashmap
    //key = url , [] значения
    private static ConcurrentHashMap<String, byte[]> map = new ConcurrentHashMap<String, byte[]>();

    public FileManager(String path) {
        // "c:\folder\" --> "c:\folder"
        if (path.endsWith("/") || path.endsWith("\\"))
            path = path.substring(0, path.length() - 1);

        this.path = path;
    }

    public FileManager(String path, long mill) {
        if (path.endsWith("/") || path.endsWith("\\"))
            path = path.substring(0, path.length() - 1);

        this.path = path;
        this.time = time;
    }

    public byte[] get(String url) {
//        firstTime = (long) System.currentTimeMillis();
        try {
            byte[] buf = map.get(url);
            if (buf != null) {// in cache
//                lastTime = System.currentTimeMillis();
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
            System.out.println(System.currentTimeMillis());
            System.out.println("Size url while alive : " + FileManager.map.size());
            Thread t = new Thread(() -> {
                try {
                    Thread.currentThread();
                    Thread.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (this) {
                    FileManager.map.remove(url);
                    System.out.println("Size url while die : " + FileManager.map.size());
                    System.out.println(System.currentTimeMillis());
                }

            });
            t.start();
            System.out.println(buf);
//-----------------------------------------------------------------------------------------

            return buf;
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

//    private void handler()
}
