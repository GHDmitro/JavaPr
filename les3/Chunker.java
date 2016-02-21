package les3;

import java.io.*;
import java.util.List;
//мы отдаем файл и знаем его размер

//чанк кодирование
public class Chunker implements Processor {
    private int chunkSize;//фиксированный рзмер блока

    public Chunker(int chunkSize) {
        this.chunkSize = chunkSize;
    }

    public byte[] process(byte[] data, List<String> headers) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            int n = data.length / chunkSize;
            int tail = data.length % chunkSize;
            int offset = 0;
            String head = Integer.toHexString(chunkSize) + "\r\n";//перевод в 16 разрядную систему

            for (int i = 0; i < n; i++) {
                os.write(head.getBytes());
                os.write(data, offset, chunkSize);
                os.write("\r\n".getBytes());
                offset += chunkSize;
            }
            if (tail > 0) {
                head = Integer.toHexString(tail) + "\r\n";
                os.write(head.getBytes());
                os.write(data, offset, tail);
                os.write("\r\n".getBytes());
            }

            os.write("0\r\n\r\n".getBytes());
            //чан кодирование обзательно добавить заголовок при использовании этого
            headers.add("Transfer-Encoding: chunked\r\n");//заголовок

            return os.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getChunkSize() {
        return chunkSize;
    }

    public void setChunkSize(int value) {
        chunkSize = value;
    }
}
