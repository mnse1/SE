import java.io.*;

public class DecoratorTest {
    public static void main(String[] args) throws Exception {
        EncryptionStrategy caesar = new CaesarEncryptionStrategy();

        try (Writer w = new EncryptWriter(new BufferedWriter(new FileWriter("encrypted_p1.txt")), caesar)) {
            w.write("Hello World!");
        }

        try (Reader r = new DecryptReader(new BufferedReader(new FileReader("encrypted_p1.txt")), caesar)) {
            int ch;
            while ((ch = r.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println();
        }
    }
}
