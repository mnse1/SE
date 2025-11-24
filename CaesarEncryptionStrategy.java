public class CaesarEncryptionStrategy implements EncryptionStrategy {

    // 왼쪽으로 3칸 이동 (P1과 동일)
    @Override
    public char encrypt(char c) {
        if ('a' <= c && c <= 'z') {
            return (char) ('a' + (c - 'a' + 26 - 3) % 26);
        } else if ('A' <= c && c <= 'Z') {
            return (char) ('A' + (c - 'A' + 26 - 3) % 26);
        } else {
            return c;
        }
    }

    // encrypt의 반대: 오른쪽으로 3칸 이동
    @Override
    public char decrypt(char c) {
        if ('a' <= c && c <= 'z') {
            return (char) ('a' + (c - 'a' + 3) % 26);
        } else if ('A' <= c && c <= 'Z') {
            return (char) ('A' + (c - 'A' + 3) % 26);
        } else {
            return c;
        }
    }
}
