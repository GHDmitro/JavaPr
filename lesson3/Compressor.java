package lesson3;

import java.io.*;
import java.util.List;
import java.util.zip.*;

//сжатие файлов
public class Compressor implements Processor {

    public static final int ALG_DEFLATE = 0;//первый алгорит сжатия
    public static final int ALG_GZIP = 1;//второй алгоритм сжатия

    private int compLevel; //0 - 9
    private int compAlg = ALG_GZIP;

    public Compressor(int compLevel) {
        this.compLevel = compLevel;
    }

    public byte[] process(byte[] data, List<String> headers) {
        try {
            ByteArrayOutputStream os = new ByteArrayOutputStream();

            if (compAlg == ALG_DEFLATE) {
                //как в паттерне декоратор

                //data сжимается по алгоритму Deflater и записывается в os
                DeflaterOutputStream ds = new DeflaterOutputStream(os, new Deflater(compLevel));
                ds.write(data);
                ds.finish();//обязательно
                //обязательно
                headers.add("Content-Encoding: deflated\r\n");
            } else if (compAlg == ALG_GZIP) {
                GZIPOutputStream ds = new GZIPOutputStream(os);
                ds.write(data);
                ds.finish();

                headers.add("Content-Encoding: gzip\r\n");
            }

            return os.toByteArray();
        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }

    public int getCompressionLevel() {
        return compLevel;
    }

    public void setCompressionLevel(int value) {
        compLevel = value;
    }

    public int getCompressionAlg() {
        return compAlg;
    }

    public void setCompressionAlg(int value) {
        compAlg = value;
    }
}