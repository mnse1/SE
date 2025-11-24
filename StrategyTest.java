import java.io.*;

public class StrategyTest {
    public static void main(String[] args) throws Exception {
        EncryptionStrategy caesar = new CaesarEncryptionStrategy();
        EncryptionStrategy toggle = new ToggleCaseEncryptionStrategy();

        // 1) Caesar 전략으로 암호화
        try (Writer w = new EncryptWriter(new BufferedWriter(new FileWriter("caesar_p2.txt")), caesar)) {
            w.write("Hello World!");
            w.write('\n');
            w.write("Abc xyz!");
        }

        // 2) ToggleCase 전략으로 암호화
        try (Writer w = new EncryptWriter(new BufferedWriter(new FileWriter("toggle_p2.txt")), toggle)) {
            w.write("Hello World!");
            w.write('\n');
            w.write("Abc xyz!");
        }

        // 3) Caesar 암호화된 파일 복호화해서 콘솔에 출력
        System.out.println("=== Caesar decrypted ===");
        try (Reader r = new DecryptReader(new BufferedReader(new FileReader("caesar_p2.txt")), caesar)) {
            int ch;
            while ((ch = r.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println();
        }

        // 4) ToggleCase 암호화된 파일 복호화해서 콘솔에 출력
        System.out.println("=== ToggleCase decrypted ===");
        try (Reader r = new DecryptReader(new BufferedReader(new FileReader("toggle_p2.txt")), toggle)) {
            int ch;
            while ((ch = r.read()) != -1) {
                System.out.print((char) ch);
            }
            System.out.println();
        }
    }
}