import java.io.*;

public class DecoratorTest {
    public static void main(String[] args) throws Exception {
        // 암호화해서 파일에 쓰기
        try (Writer w = new EncryptWriter(
                new BufferedWriter(new FileWriter("encrypted_p1.txt")))) {
            w.write("Hello World!");
        }

        // 복호화하면서 읽기
        try (Reader r = new DecryptReader(
                new BufferedReader(new FileReader("encrypted_p1.txt")))) {
            int ch;
            while ((ch = r.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println();
        }
    }
}