package homeWork3.task2;

import java.io.IOException;
import java.io.InputStream;
import java.security.InvalidParameterException;
import java.util.Arrays;

/**
 * Created by macbookair on 21.02.16.
 */
public class ChunkDecoder extends InputStream {

    private InputStream is;

    ChunkDecoder(InputStream inputStream) {
        this.is = inputStream;
    }

    @Override
    public int read() throws IOException {
        return is.read();
    }

    public int read(byte[] data, int capasity) throws IOException {

        int amountNow = 0; //количество данных считаное наданные момент
        byte[] avaliableData = new byte[is.available()];

//        StringBuilder exitData = new StringBuilder(data.length);

        Integer privDataSize; //длинна информации в каждом блоке
        StringBuilder sb = new StringBuilder();
        String chunkSize; //длинна информации в блоке  в 16 ой системе

        if (data.length < capasity) {
            throw new InvalidParameterException("capasity");
        }
        int flug = 0;
        if (is.read(avaliableData) != -1) {
            for (byte anAvaliableData : avaliableData) {
                sb.append(anAvaliableData);
            }
        }
//        System.out.println(sb.toString());
        while (flug < capasity) {
            int fgb = sb.indexOf("1310");
            chunkSize = sb.substring(flug, fgb);//длинна текущего блока в 16 ричной системе
            privDataSize = Integer.parseInt(chunkSize.trim(), 16); // в 10 ричной системе

            int privatFlug = chunkSize.length() + "1310".length(); //
            int privBlockSize = privDataSize + "13101310".length() + privatFlug;
//            char[] chars = sb.toString().toCharArray();
//            System.out.println(chars.length);
            System.out.println(sb.toString().getBytes().length);
            int counter = 1;
            for (int i = privatFlug; i < (privBlockSize - 7); i++) { //до конца блока проверим наличие завершения блока

                int differ = capasity - amountNow;//количество информации которое еще надо считать
                if (privDataSize <= (differ)) {
                    data[flug] = avaliableData[i];
                    flug++;
                    amountNow++;
                    //переписывать информацию в отдельный массив и потом его же и отдать
//                        String s = sb.substring(privatFlug, (privatFlug + privDataSize));
//                        exitData.append(s);
//                        amountNow += s.getBytes().length;
                } else {
                    if (counter < differ) {
                        data[flug] = avaliableData[i];
                        counter++;
                        amountNow++;
                    }
                    flug++;
//                        String s = sb.substring(privatFlug, (differ + privatFlug));
//                        exitData.append(s);
//                        amountNow += s.getBytes().length;
                }
//
            }

        }

//        data = exitData.toString().getBytes();

        if (data.length != 0) {
            return 1;
        } else {
            return -1;
        }
    }
}
//flug += privBlockSize; //индекс начала следующего блока
//                if ((chars[i] == 13) && (chars[i + 1] == 10) && (chars[i + 2] == 13) &&
//                        (chars[i + 3] == 10) && (i == privBlockSize - 4)) {
//                    int differ = capasity - amountNow;
//                    if (privDataSize <= (differ)) {
//                        String s = sb.substring(privatFlug, (privatFlug + privDataSize));
//                        exitData.append(s);
//                        amountNow += s.getBytes().length;
//                    } else {
//                        String s = sb.substring(privatFlug, (differ + privatFlug));
//                        exitData.append(s);
//                        amountNow += s.getBytes().length;
//                    }
//                    flug += privBlockSize; //индекс начала следующего блока
//                }
