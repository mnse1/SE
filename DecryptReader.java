import java.io.IOException;
import java.io.Reader;

public class DecryptReader extends Reader {
    private final Reader in;
    private final EncryptionStrategy strategy;

    public DecryptReader(Reader in, EncryptionStrategy strategy) {
        this.in = in;
        this.strategy = strategy;
    }

    @Override public int read() throws IOException {
        int ch = in.read();
        return (ch == -1) ? -1 : strategy.decrypt((char) ch);
    }

    @Override public int read(char[] cbuf, int off, int len) throws IOException {
        int n = in.read(cbuf, off, len);
        if (n == -1) return -1;
        for (int i = 0; i < n; i++) cbuf[off + i] = strategy.decrypt(cbuf[off + i]);
        return n;
    }

    @Override public void close() throws IOException { in.close(); }
}
