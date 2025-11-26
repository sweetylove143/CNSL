import java.util.Scanner;

public class p4_CaesarCipherSimple {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the text: ");
        String text = sc.nextLine();

        System.out.print("Enter the shift value: ");
        int shift = sc.nextInt();

        String encrypted = "";
        String decrypted = "";

        // Encryption
        for (char c : text.toCharArray()) {

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                encrypted += (char) ((c - base + shift) % 26 + base);
            } else {
                encrypted += c;
            }
        }

        // Decryption
        for (char c : encrypted.toCharArray()) {

            if (Character.isLetter(c)) {
                char base = Character.isUpperCase(c) ? 'A' : 'a';
                decrypted += (char) ((c - base - shift + 26) % 26 + base);
            } else {
                decrypted += c;
            }
        }

        System.out.println("\nOriginal: " + text);
        System.out.println("Encrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}

/*
 * EXPLANATION:
 * - Caesar Cipher shifts each letter by a fixed value.
 * - For encryption: (char - base + shift) % 26
 * - For decryption: (char - base - shift + 26) % 26
 * - Non-letters remain unchanged.
 * 
 * SAMPLE INPUT 1:
 * Enter the text: HELLO
 * Enter the shift value: 3
 * 
 * SAMPLE OUTPUT 1:
 * Original: HELLO
 * Encrypted: KHOOR
 * Decrypted: HELLO
 * 
 * SAMPLE INPUT 2:
 * Enter the text: ATTACKATDAWN
 * Enter the shift value: 5
 * 
 * SAMPLE OUTPUT 2:
 * Original: ATTACKATDAWN
 * Encrypted: FYYFHPFYIFBS
 * Decrypted: ATTACKATDAWN
 */
