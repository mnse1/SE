import java.io.IOException;
import java.io.Writer;

public class EncryptWriter extends Writer {
    private final Writer out;

    public EncryptWriter(Writer out) {
        this.out = out;
    }

    // 알파벳을 왼쪽으로 3글자 이동 (a->x, b->y, c->z, d->a ...)
    private char encryptChar(char c) {
        if ('a' <= c && c <= 'z') {
            return (char) ('a' + (c - 'a' + 26 - 3) % 26);
        } else if ('A' <= c && c <= 'Z') {
            return (char) ('A' + (c - 'A' + 26 - 3) % 26);
        } else {
            return c; // 알파벳 아니면 그대로
        }
    }

    private void encryptAndWrite(char[] cbuf, int off, int len) throws IOException {
        char[] buf = new char[len];
        for (int i = 0; i < len; i++) {
            buf[i] = encryptChar(cbuf[off + i]);
        }
        out.write(buf, 0, len);
    }

    @Override
    public void write(char[] cbuf, int off, int len) throws IOException {
        encryptAndWrite(cbuf, off, len);
    }

    @Override
    public void write(int c) throws IOException {
        char[] one = { (char) c };
        encryptAndWrite(one, 0, 1);
    }

    @Override
    public void write(String str, int off, int len) throws IOException {
        char[] buf = new char[len];
        str.getChars(off, off + len, buf, 0);
        encryptAndWrite(buf, 0, buf.length);
    }

    @Override
    public void flush() throws IOException {
        out.flush();
    }

    @Override
    public void close() throws IOException {
        out.close();
    }
}