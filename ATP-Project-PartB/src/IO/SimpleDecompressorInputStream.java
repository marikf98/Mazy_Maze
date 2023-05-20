package IO;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;
import java.io.InputStream;

public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }
    @Override
    public int read() throws IOException {
        return in.read();
    }
    public int read(byte[] bytes) throws IOException {
        byte[] decompressedBytes = decompress(bytes);
        System.arraycopy(decompressedBytes, 0, bytes, 0, decompressedBytes.length);
        return decompressedBytes.length;
    }
    public byte[] decompress(byte[] bytes) throws IOException {
        List<Byte> decompressedList = new ArrayList<>();
        int count;
        byte value;

        while (true) {
            count = in.read();
            if (count == -1) {
                break;
            }
            value = (byte) in.read();

            for (int i = 0; i < count; i++) {
                decompressedList.add(value);
            }
        }

        byte[] decompressedBytes = new byte[decompressedList.size()];
        for (int i = 0; i < decompressedList.size(); i++) {
            decompressedBytes[i] = decompressedList.get(i);
        }

        return decompressedBytes;
    }
}


