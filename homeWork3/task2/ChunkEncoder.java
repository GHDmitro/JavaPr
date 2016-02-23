package homeWork3.task2;

import java.io.IOException;
import java.io.OutputStream;

/**
 * Created by macbookair on 21.02.16.
 */
public class ChunkEncoder extends OutputStream {

    private OutputStream os;

    public ChunkEncoder(OutputStream os) {
        this.os = os;
    }

    @Override
    public void write(int b) throws IOException {

        os.write(b);
    }

    @Override
    public void write(byte[] data, int offset, int length) throws IOException {
        String head = Integer.toHexString(length) + "\r\n"; //кодирование head

        if (data == null) {
            throw new NullPointerException();
        } else if ((offset < 0) || (offset > data.length) || (length < 0) ||
                ((length - offset) > data.length) || ((offset + length) < 0)) {
            throw new IndexOutOfBoundsException();
        } else if (length == 0) {
            return;
        }
        StringBuilder sb = new StringBuilder();
//        for (int i = 0; i < length; i++) {
//            sb.append("0");
//        }
        sb.append("\r").append("\n").append("\r").append("\n");

        os.write(head.getBytes());
        os.write(data, offset, length);
        os.write(sb.toString().getBytes());

        os.flush();
    }

    @Override
    public void close() throws IOException {
        os.close();
    }

}

























