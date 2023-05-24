package IO;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.DataFormatException;
import java.util.zip.GZIPInputStream;
import java.util.zip.Inflater;

/**This method decompresses the data that was compressed in MyCompressorOutPutStream**/
public class MyDecompressorInputStream extends InputStream {
    private InputStream in;

    public MyDecompressorInputStream(InputStream in) {
        this.in = in;
    }
    /**This function reads a single byte**/
    @Override
    public int read() throws IOException
    {
        return in.read();
    }

    /**This function reads an array of bytes**/
    @Override
    public int read(byte[] bytes) throws IOException
    {
        byte[] readBytes = in.readAllBytes();
        byte[] decompressed = null;
        try {

            decompressed = decompress(readBytes); //First we decompress the input array
        }
        catch (DataFormatException e)
        {
            throw new RuntimeException(e);
        }

        for(int i = 0; i < decompressed.length; i ++)
        {
            bytes[i] = decompressed[i]; // then we deep copy the decompressed bytes to the input array
        }


        return bytes.length;
    }

    /**
     * This is the decompression method it receives an array of bytes that represents the compressed data and returns
     * an array of bytes that is the decompressed data**/
    public byte[] decompress(byte[] compressed) throws IOException, DataFormatException {
        ByteArrayOutputStream decompresedArray = null;
        Inflater decompressor = new Inflater(); // we use the deflater class to decmpress the data
        decompressor.setInput(compressed);
        decompresedArray = new ByteArrayOutputStream();
        byte[] tmp = new byte[4*1024];
        while(!decompressor.finished())
        {
            int size = decompressor.inflate(tmp); // the inflate function decompresses the given bytes and returns the number of bytes that are decompressed
            decompresedArray.write(tmp, 0, size); // and here we write the decompressed data to the output array
        }
        decompresedArray.close();
        return decompresedArray.toByteArray();
    }
}
