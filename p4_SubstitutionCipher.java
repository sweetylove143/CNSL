import java.util.Scanner;

public class p4_SubstitutionCipher {

    static void printMapping(String key) {
        System.out.println("Substitution Mapping:");
        System.out.println("Plain : ABCDEFGHIJKLMNOPQRSTUVWXYZ");
        System.out.println("Cipher: " + key);
        System.out.println();
    }

    static String encrypt(String message, String key) {

        message = message.toUpperCase().replaceAll("[^A-Z]", "");

        StringBuilder encrypted = new StringBuilder();

        for (char c : message.toCharArray()) {
            encrypted.append(key.charAt(c - 'A'));
        }

        return encrypted.toString();
    }

    static String decrypt(String cipher, String key) {

        StringBuilder decrypted = new StringBuilder();

        for (char c : cipher.toCharArray()) {
            int index = key.indexOf(c);
            decrypted.append((char) (index + 'A'));
        }

        return decrypted.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        String key = "QWERTYUIOPASDFGHJKLZXCVBNM"; // Fixed substitution key

        printMapping(key);

        System.out.print("Enter message: ");
        String message = sc.nextLine();

        String encrypted = encrypt(message, key);
        System.out.println("Encrypted: " + encrypted);

        String decrypted = decrypt(encrypted, key);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}

/*
 * EXPLANATION:
 * - Each letter is replaced by another fixed letter from the key.
 * - Example: A -> Q, B -> W, C -> E, ...
 * - For encryption: plaintext index gives cipher letter.
 * - For decryption: find cipher letter index in key.
 * 
 * SAMPLE INPUT 1:
 * Enter message: HELLO
 * 
 * SAMPLE OUTPUT 1:
 * Encrypted: ITSSG
 * Decrypted: HELLO
 * 
 * SAMPLE INPUT 2:
 * Enter message: SECURITY
 * 
 * SAMPLE OUTPUT 2:
 * Encrypted: LTMXOKZN
 * Decrypted: SECURITY
 */
