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

/**This is the decompressor class for the compression that is done in the SimpleCompressorOutputStream class**/
public class SimpleDecompressorInputStream extends InputStream {
    private InputStream in;

    public SimpleDecompressorInputStream(InputStream in) {
        this.in = in;
    }
    /**This function reads a single byte**/
    @Override
    public int read() throws IOException {
        return in.read();
    }
    /**This function reads an array of bytes**/
    public int read(byte[] bytes) throws IOException {
        byte[] decompressedBytes = decompress(bytes); // here it gets the decompressed array of bytes
        System.arraycopy(decompressedBytes, 0, bytes, 0, decompressedBytes.length); // and here we copy the decompressed array to the input array
        return decompressedBytes.length;
    }
    /**
     * This is the decompression method it reverses the algorithm that was done in the compression method
     * in SimpleCompressorOutputStream class
     * it uses the number of repetitions of each byte to recreate all the bytes that where in the compressed array**/
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


