package IO;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
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
        for(int i = 0; i < compressedBytes.length; i++)
        {
            write(compressedBytes[i]);
        }
//        out.write(compressedBytes); // and here we write the compressed bytes
    }

    /**This is the compression algorithm
     * it works by counting the number of a repeating byte and counts the number of appearances
     * and then write the repeating byte and after it the number of its appearances**/

    public byte[] compress(byte[] bytes)
    {
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream(bytes.length);
        ArrayList<Byte> intArr = new ArrayList<>();
        ArrayList<Byte> byteLst = new ArrayList<>();

        byte counter;
        byte prevNum;
        boolean zeroFlag = false;

        if(bytes[10] == 1)
        {
            counter = 0;
            prevNum = 1;
            intArr.add((byte)0);
        }
        else
        {
            counter = 1;
            prevNum = 0;
        }
        int current = 11;

        while(current < bytes.length)
        {
            if(bytes[current] == prevNum)
            {
                if(counter == 126)
                {
                    intArr.add(counter);
                    intArr.add((byte)0);
                    counter = 0;
                }
                counter ++;
                prevNum = bytes[current];
                current ++;
            }
            else
            {
                intArr.add(counter);
                counter = 1;
                prevNum = bytes[current];
                current ++;
            }
        }

        intArr.add(counter);
        byte[] resArr = new byte[bytes.length + intArr.size()];
        for(int i = 0; i < 10; i ++)
        {
            resArr[i] = bytes[i];
        }
        int inx = 10;
        for(int i = 0; i < intArr.size(); i ++)
        {
            resArr[inx] = intArr.get(i);
            inx ++;
        }
        return resArr;
    }

//    public byte[] compress(byte[] bytes) {
//        ByteArrayOutputStream compressedStream = new ByteArrayOutputStream();
//
//        int count = 1;
//        byte currentByte = bytes[0];
//
//        for (int i = 1; i < bytes.length; i++) {
//            if (bytes[i] == currentByte) {
//                count++;
//            } else {
//                compressedStream.write(count);
//                compressedStream.write(currentByte);
//                count = 1;
//                currentByte = bytes[i];
//            }
//        }
//
//        compressedStream.write(count);
//        compressedStream.write(currentByte);
//
//        return compressedStream.toByteArray();
//    }



}
