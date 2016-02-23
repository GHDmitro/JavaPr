package homeWork3.task2;

import jdk.internal.util.xml.impl.ReaderUTF16;

import java.io.*;
import java.math.BigInteger;
import java.util.Arrays;

/**
 * Created by macbookair on 22.02.16.
 */
public class main {
    public static void main(String[] args) throws IOException {
        File file = new File("/users/macbookair/ideaprojects/javapro/src/homework3/task2/file1.txt");

        String s = "It is my own test for my work.";
        ChunkEncoder chunkEncoder = new ChunkEncoder(new FileOutputStream(file));
        byte[] bytes;
        bytes = s.getBytes();
        System.out.println(bytes.length);
        int d = 0;
//        while (d < 26){

        chunkEncoder.write(bytes, d, 30);
//            d += 5;
//        }


        ChunkDecoder chunkDecoder = new ChunkDecoder(new FileInputStream(file));
//        byte[] bytes = new byte[55];

        int flug = chunkDecoder.read(bytes, 30);
        System.out.println("flug " + flug);

        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < 15; i++) {
            sb.append((char) bytes[i]);
        }
        System.out.println(sb.toString());

    }
}

//        System.out.println(hexInt);
//        int n = 34;
//        System.out.println(n);
//        String str = Integer.toHexString(n);
////        System.out.println(Integer.toBinaryString(n));
////        System.out.println(Integer.parseUnsignedInt(str));
////        System.out.println(Integer.valueOf(str));
////        String i = Integer.toString();
////        n = Integer.decode(str);
////        System.out.println(i);
////        System.out.println(Arrays.deepToString(str.getBytes()));
//
////        ByteArrayInputStream ir = new ByteArrayInputStream(str.getBytes());
////        byte[] r = new byte[0];
////        while (ir.read(r) >0){
////
//        Integer emp1 = Integer.parseInt(str.trim(), 16);
////        }
//
//        StringBuilder as = new StringBuilder();
//        as.append(
//                "sdbfvjsdbsdjlfknvkdsvljkdsn"
//        );
//        System.out.println(as.subSequence(0, 6));
//        System.out.println(as.substring(0, 6));
//        System.out.println(as.subSequence(0, as.length()));
//        System.out.println(emp1);
//
////
////        System.out.println(Integer.parseInt(str , 10));
////        String ser = new String(str.getBytes(), "UTF-16");
////        System.out.println("ds : "+ser);
////        for (int i = 0; i <r.length ; i++) {
////
////            System.out.println(new BigInteger(String.valueOf(r[i]) , 10));
////        }
//str = n + "\r\n";
//        byte[] x = str.getBytes();
//
//        for (int i = 0; i < x.length; i++) {
//            System.out.println(x[i]);
//            char c = (char) x[i];
//            System.out.println(c);
//
//
////        int hexInt = Long.decode(str).intValue();
//        System.out.println(String.valueOf(hexInt));


