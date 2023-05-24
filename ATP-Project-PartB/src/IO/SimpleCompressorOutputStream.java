package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;

/**
 * This is another compressor class that can compress an array of bytes**/
public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out)
    {
        this.out = out;
    }

    /**This function lets us write a single byte**/
    @Override
    public void write(int b) throws IOException
    {
        out.write(b);
    }
    /**This function lets us write an array of bytes**/

    @Override
    public void write(byte[] bytes) throws IOException
    {
        byte[] compressedBytes = compress(bytes); // here we compress the bytes
        out.write(compressedBytes); // and here we write the compressed bytes
    }

    /**This is the compression algorithm
     * it works by counting the number of a repeating byte and counts the number of appearances
     * and then write the repeating byte and after it the number of its appearances**/
    public byte[] compress(byte[] bytes) {
        ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();

        int count = 1;
        byte currentByte = bytes[0];

        for (int i = 1; i < bytes.length; i++) {
            if (bytes[i] == currentByte) {
                count++;
            } else {
                compressedStream.write(count);
                compressedStream.write(currentByte);
                count = 1;
                currentByte = bytes[i];
            }
        }

        compressedStream.write(count);
        compressedStream.write(currentByte);

        return compressedStream.toByteArray();
    }

}
