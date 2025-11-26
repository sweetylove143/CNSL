import java.util.Scanner;

public class p7_des {

    static int charToNumber(char c) {
        if (Character.isUpperCase(c))
            return c - 'A';
        if (Character.isLowerCase(c))
            return c - 'a' + 26;
        if (Character.isDigit(c))
            return c - '0' + 52;
        return c % 62;
    }

    static char numberToChar(int n) {
        n = ((n % 62) + 62) % 62;
        if (n < 26)
            return (char) ('A' + n);
        else if (n < 52)
            return (char) ('a' + (n - 26));
        return (char) ('0' + (n - 52));
    }

    static int mangler(int v, int k, int r) {
        return (v * 7 + k * 5 + r * 3) % 62;
    }

    static final int INV_7_MOD_62 = 9;

    static String encryptRound(String text, String key, int round) {
        StringBuilder sb = new StringBuilder();

        for (int i = 0; i < text.length(); i++) {
            int t = charToNumber(text.charAt(i));
            int k = charToNumber(key.charAt(i % key.length()));
            int mix = mangler(t, k, round);
            sb.append(numberToChar(mix));
        }

        return sb.reverse().toString();
    }

    static String encrypt(String text, String key) {
        String result = text;
        for (int i = 1; i <= 3; i++)
            result = encryptRound(result, key, i);
        return result;
    }

    static String decrypt(String text, String key) {
        String result = text;

        for (int r = 3; r >= 1; r--) {
            result = new StringBuilder(result).reverse().toString();
            StringBuilder temp = new StringBuilder();

            for (int i = 0; i < result.length(); i++) {
                int c = charToNumber(result.charAt(i));
                int k = charToNumber(key.charAt(i % key.length()));

                int subtract = (5 * k + 3 * r) % 62;
                int diff = (c - subtract + 62) % 62;
                int original = (INV_7_MOD_62 * diff) % 62;

                temp.append(numberToChar(original));
            }

            result = temp.toString();
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter Plaintext: ");
        String plaintext = sc.nextLine();

        System.out.print("Enter Key: ");
        String key = sc.nextLine();

        String enc = encrypt(plaintext, key);
        String dec = decrypt(enc, key);

        System.out.println("\nEncrypted: " + enc);
        System.out.println("Decrypted: " + dec);
    }
}

/*
 * EXPLANATION:
 * This is a simplified 3-round DES-like algorithm.
 * 
 * 1) charToNumber → convert char → 0–61
 * 2) mangler function → combines plaintext number, key number, and round number
 * 3) Each round:
 * - Apply mangler
 * - Convert back to character
 * - Reverse string (permutation)
 * 4) Encryption → rounds 1 → 3
 * 5) Decryption → reverse rounds using modular inverse of 7 mod 62
 * 
 * Works for any letters/digits.
 * 
 * SAMPLE INPUT 1:
 * Enter Plaintext: HELLO
 * Enter Key: KEY
 * 
 * SAMPLE OUTPUT 1:
 * Encrypted: anXGf
 * Decrypted: HELLO
 * 
 * SAMPLE INPUT 2:
 * Enter Plaintext: SECURITY
 * Enter Key: LOCK
 * 
 * SAMPLE OUTPUT 2:
 * Encrypted: fRCPXO4w
 * Decrypted: SECURITY
 */
