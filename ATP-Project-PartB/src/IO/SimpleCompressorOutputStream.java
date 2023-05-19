package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;



public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
    }

    @Override
    public void write(int b) throws IOException {
        out.write(b);
    }

    @Override
    public void write(byte[] bytes) throws IOException {
        byte[] compressedBytes = compress(bytes);
        out.write(compressedBytes);
    }

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
