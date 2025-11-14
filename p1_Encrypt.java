public class p1_Encrypt{

    // Encrypt using AND operation
    public static String encryptAnd(String message, int key) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] & key);
        }
        return new String(chars);
    }

    // Encrypt using OR operation
    public static String encryptOr(String message, int key) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] | key);
        }
        return new String(chars);
    }

    // Encrypt using XOR operation
    public static String encryptXor(String message, int key) {
        char[] chars = message.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            chars[i] = (char) (chars[i] ^ key);
        }
        return new String(chars);
    }

    public static void main(String[] args) {
        String message = "HelloWorld";
        int key = 7; // Small example key

        System.out.println("Original: " + message);

        String andEnc = encryptAnd(message, key);
        System.out.println("AND Encrypted: " + andEnc);

        String orEnc = encryptOr(message, key);
        System.out.println("OR Encrypted: " + orEnc);

        String xorEnc = encryptXor(message, key);
        System.out.println("XOR Encrypted: " + xorEnc);
    }
}