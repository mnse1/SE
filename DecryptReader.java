import java.io.IOException;
import java.io.Reader;

public class DecryptReader extends Reader {
    private final Reader in;

    public DecryptReader(Reader in) {
        this.in = in;
    }

    // encrypt의 반대: 오른쪽으로 3글자 이동 (x->a, y->b, z->c, a->d ...)
    private char decryptChar(char c) {
        if ('a' <= c && c <= 'z') {
            return (char) ('a' + (c - 'a' + 3) % 26);
        } else if ('A' <= c && c <= 'Z') {
            return (char) ('A' + (c - 'A' + 3) % 26);
        } else {
            return c;
        }
    }

    @Override
    public int read(char[] cbuf, int off, int len) throws IOException {
        int n = in.read(cbuf, off, len);
        if (n == -1) return -1;

        for (int i = 0; i < n; i++) {
            cbuf[off + i] = decryptChar(cbuf[off + i]);
        }
        return n;
    }

    @Override
    public void close() throws IOException {
        in.close();
    }
}