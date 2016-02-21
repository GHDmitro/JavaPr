package les3;

import java.lang.Exception;
import java.io.*;
import java.net.*;
import java.util.ArrayList;
import java.util.List;
import java.lang.Thread;

public class Client implements Runnable {
    private Socket socket;
    private FileManager fm;

    public Client(Socket socket, String path) {
        this.socket = socket;
        fm = new FileManager(path);//записывается путь к файлу например "index.html"
    }

    private void returnStatusCode(int code, OutputStream os) throws IOException {
        String msg = null;

        switch (code) {
            case 400:
                msg = "HTTP/1.1 400 Bad Request";
                break;
            case 404:
                msg = "HTTP/1.1 404 Not Found";
                break;
            case 500:
                msg = "HTTP/1.1 500 Internal Server Error";
                break;
        }

        byte[] resp = msg.concat("\r\n\r\n").getBytes();
        os.write(resp);
    }

    private byte[] getBinaryHeaders(List<String> headers) {
        StringBuilder res = new StringBuilder();

        for (String s : headers)
            res.append(s);

        return res.toString().getBytes();
    }

    private void process(String request, OutputStream os) throws IOException {
        System.out.println(request);
        System.out.println("---------------------------------------------");

        int idx = request.indexOf("\r\n");//отбрасываем первый конец строки (это заголовки)
        request = request.substring(0, idx);

        String[] parts = request.split(" ");//get url версия ; доолжно быть три штуки
        if (parts.length != 3) {
            returnStatusCode(400, os);
            return;
        }

        String method = parts[0], url = parts[1], version = parts[2];

        if ((!version.equalsIgnoreCase("HTTP/1.0")) && (!version.equalsIgnoreCase("HTTP/1.1"))) {
            returnStatusCode(400, os);
            return;
        }
        if (!method.equalsIgnoreCase("GET")) {
            returnStatusCode(400, os);
            return;
        }
        if ("/".equals(url))//
            url = "/index.html"; //дописываем url странички

        List<String> headers = new ArrayList<String>();
        headers.add("HTTP/1.1 200 OK\r\n");


        byte[] content = fm.get(url); // возвращает url если есть в map то из него если нет то из диска
        if (content == null) {
            returnStatusCode(404, os);
            return;
        }

        ProcessorsList pl = new ProcessorsList();
        pl.add(new Compressor(6));
        pl.add(new Chunker(30)); // comment
        content = pl.process(content, headers);//content данные headers место куда добавляются заголовки при использоавании методод

        if (content == null) {
            returnStatusCode(500, os);
            return;
        }

        // uncomment next line
        // headers.add("Content-Length: " + content.length + "\r\n");
        headers.add("Connection: close\r\n\r\n");

        os.write(getBinaryHeaders(headers));
        os.write(content);
    }

    public void run() {
        try {
            InputStream is = socket.getInputStream();
            OutputStream os = socket.getOutputStream();

            ByteArrayOutputStream bs = new ByteArrayOutputStream();
            byte[] buf, temp;
            int len, b;

            try {
                do {
                    //возвращает сколько данных сейчас есть для чтения
                    len = is.available();
                    buf = new byte[len];

                    if (is.read(buf) > 0)
                        bs.write(buf);//записываем

                    temp = bs.toByteArray();
                    //-3 т к последние 4 символа это 13 10 13 10 как конец байтов в куске
                    for (int i = 0; i < temp.length - 3; i++) {
                        if ((temp[i] == (byte) 13) && (temp[i + 1] == (byte) 10) &&
                                (temp[i + 2] == (byte) 13) && (temp[i + 3] == (byte) 10)) {
                            String request = new String(temp, 0, i);
                            process(request, os);
                        }
                    }
                } while (!Thread.currentThread().isInterrupted());
            } finally {
                socket.close();
            }
        } catch (Exception ex) {
            ex.printStackTrace();
            return;
        }
    }
}