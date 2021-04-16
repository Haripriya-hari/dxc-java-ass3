import java.io.*;

public class Counter {
    public static void main(String[] args) throws IOException {
        CountReader cr = new CountReader(new FileReader("farrago.txt"), 'e');
        CountWriter cw = new CountWriter(new FileWriter("outagain.txt"), 'e');
        int c = 0;
        while ((c = cr.read()) != -1) {
            cw.write(c);
        }
        System.out.println(cr.getCount());   
        System.out.println(cw.getCount());   
	cr.close();
	cw.close();
    }
}

class CountReader extends FilterReader {

    private int count = 0;
    private char lookFor = 0;

    public CountReader(Reader in, char lookFor) {
        super(in);
        this.lookFor = lookFor;
    }
    public int read() throws IOException {
        int character = super.read();
        if ((char)character == lookFor) {
            count++;
        }
	return character;
    }
    public int read(char[] cbuf) throws IOException {
        int numChars = super.read(cbuf);
        int length = cbuf.length;
        for (int i = 0; i < length; i++) {
            if (cbuf[i] == lookFor) {
                count++;
            }
        }
	return numChars;
    }
    public int read(char[] cbuf, int off, int len) throws IOException {
        int numChars = super.read(cbuf, off, len);
        int length = cbuf.length;
        for (int i = 0; i < length; i++) {
            if (cbuf[i] == lookFor) {
                count++;
            }
        }
	return numChars;
    }
    public void reset() throws IOException {
        super.reset();
        count = 0;
    }
    public boolean markSupported() {
        return false;
    }
    public void mark(int readAheadLimit) throws IOException {
        throw new IOException("CountReader does not support the mark() operation.");
    }
    public int getCount() {
        return count;
    }
}


class CountWriter extends FilterWriter {

    private int count = 0;
    private char lookFor = 0;

    public CountWriter(Writer in, char lookFor) {
        super(in);
        this.lookFor = lookFor;
    }
    public void write(int character) throws IOException {
        super.write(character);
        if ((char)character == lookFor) {
            count++;
        }
    }
    public void write(char[] cbuf) throws IOException {
        super.write(cbuf);
        int length = cbuf.length;
        for (int i = 0; i < length; i++) {
            if (cbuf[i] == lookFor) {
                count++;
            }
        }
    }
    public void write(char[] cbuf, int off, int len) throws IOException {
        super.write(cbuf, off, len);
        int length = cbuf.length;
        for (int i = 0; i < length; i++) {
            if (cbuf[i] == lookFor) {
                count++;
            }
        }
    }
    public void write(String str) throws IOException {
        super.write(str);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == lookFor) {
                count++;
            }
        }
    }
    public void write(String str, int off, int len) throws IOException {
        super.write(str, off, len);
        int length = str.length();
        for (int i = 0; i < length; i++) {
            if (str.charAt(i) == lookFor) {
                count++;
            }
        }
    }
    public int getCount() {
        return count;
    }
}
