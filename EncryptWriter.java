import java.io.IOException;
import java.io.Writer;

public class EncryptWriter extends Writer {
    private final Writer out;
    private final EncryptionStrategy strategy;

    public EncryptWriter(Writer out, EncryptionStrategy strategy) {
        this.out = out;
        this.strategy = strategy;
    }

    private void encryptAndWrite(char[] cbuf, int off, int len) throws IOException {
        char[] buf = new char[len];
        for (int i = 0; i < len; i++) {
            buf[i] = strategy.encrypt(cbuf[off + i]);
        }
        out.write(buf, 0, len);
    }

    @Override public void write(char[] cbuf, int off, int len) throws IOException { encryptAndWrite(cbuf, off, len); }
    @Override public void write(int c) throws IOException { char[] one = {(char)c}; encryptAndWrite(one, 0, 1); }
    @Override public void write(String str, int off, int len) throws IOException {
        char[] buf = new char[len];
        str.getChars(off, off + len, buf, 0);
        encryptAndWrite(buf, 0, len);
    }
    @Override public void flush() throws IOException { out.flush(); }
    @Override public void close() throws IOException { out.close(); }
}
