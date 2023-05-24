package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.zip.Deflater;
/**
 * This is one of the compression classes **/
public class MyCompressorOutputStream extends OutputStream
{
    private OutputStream out;

    public MyCompressorOutputStream(OutputStream out)
    {
        this.out = out;
    }

    /**This method writes a single byte**/
    @Override
    public void write(int b) throws IOException
    {
        out.write(b);
    }
    /**This method write an array of bytes after they were compressed **/
    @Override
    public void write(byte[] bytes) throws IOException
    {
        byte[] arrayToCompress = compress(bytes);
        for (byte toCompress : arrayToCompress) // we iterate other each byte of the compressed array and write them
        {
            write(toCompress);
        }
    }
    /**This is the compress function
     * It uses the Deflater class to compress the byte array **/
    public byte[] compress(byte[] bytes)
    {
        Deflater compressor = new Deflater(Deflater.BEST_COMPRESSION); // a bit slower compression time but a better compression
        compressor.setInput(bytes); // we set the input for the deflater
        compressor.finish();
        ByteArrayOutputStream bao = new ByteArrayOutputStream(); // this will store the compressed data
        byte[] readBuffer = new byte[1024]; // this is the buffer for read the compressed data from the compressor
        int readCount = 0;
        while (!compressor.finished()) {
            readCount = compressor.deflate(readBuffer); // this compresses the data and returns the number of bytes that were produced
            if (readCount > 0) {
                bao.write(readBuffer, 0, readCount); // and here we write the compressed bytes to the array
            }
        }
        compressor.end(); // here we close the compresor
        return bao.toByteArray();
    }
}
