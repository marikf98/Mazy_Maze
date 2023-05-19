package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }
    @Override
    public int read() throws IOException
    {
        return in.read();
    }

    @Override
    public int read(byte[] bytes) throws IOException
    {
        return 0;
    }

    public byte[] decompress(byte[] compressed) throws IOException, DataFormatException {
        ByteArrayOutputStream decompresedArray = null;
        Inflater decompressor = new Inflater();
        decompressor.setInput(compressed);
        decompresedArray = new ByteArrayOutputStream();
        byte[] tmp = new byte[4*1024];
        while(!decompressor.finished())
        {
            int size = decompressor.inflate(tmp);
            decompresedArray.write(tmp, 0, size);
        }
        decompresedArray.close();
        return decompresedArray.toByteArray();
    }
}
