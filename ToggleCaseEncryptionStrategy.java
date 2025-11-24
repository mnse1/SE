public class ToggleCaseEncryptionStrategy implements EncryptionStrategy {

    @Override
    public char encrypt(char c) {
        if ('a' <= c && c <= 'z') {
            return (char) (c - 'a' + 'A'); // 소문자 -> 대문자
        } else if ('A' <= c && c <= 'Z') {
            return (char) (c - 'A' + 'a'); // 대문자 -> 소문자
        } else {
            return c;
        }
    }

    @Override
    public char decrypt(char c) {
        // 토글은 자기 자신이 역함수
        return encrypt(c);
    }
}
