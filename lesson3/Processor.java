package lesson3;

import java.util.List;

public interface Processor {
    byte[] process(byte[] data, List<String> headers);
}