package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;



public class SimpleCompressorOutputStream extends OutputStream {
    private OutputStream out;
    private int count;
    private int currentBit;

    public SimpleCompressorOutputStream(OutputStream out) {
        this.out = out;
        this.count = 0;
        this.currentBit = -1;;
    }

    @Override
    public void flush() throws IOException {
        write_compress();
        out.flush();
    }

    @Override
    public void close() throws IOException {
        flush();
        out.close();
    }

    @Override
    public void write(int b) throws IOException {
        if (currentBit == -1) {
            currentBit = b;
            count = 1;
        } else if (currentBit == b) {
            count++;
        } else {
            write_compress();
            currentBit = b;
            count = 1;
        }
    }




    public void write_compress() throws IOException {
        if (currentBit == 1) {
            out.write('.');  // Represents 1
        } else {
            out.write(',');  // Represents 0
        }
        out.write(Integer.toString(count).getBytes());
    }

}
