import java.util.Scanner;

public class p4_Transposi_RailFenceCipher {

    // Encrypt using rail fence logic
    static String encrypt(String text, int rails) {

        text = text.replaceAll("\\s+", "").toUpperCase();

        char[][] fence = new char[rails][text.length()];

        boolean down = false;
        int row = 0;

        for (int i = 0; i < text.length(); i++) {

            fence[row][i] = text.charAt(i);

            if (row == 0 || row == rails - 1)
                down = !down;

            row += down ? 1 : -1;
        }

        StringBuilder encrypted = new StringBuilder();

        for (char[] r : fence)
            for (char c : r)
                if (c != 0)
                    encrypted.append(c);

        return encrypted.toString();
    }

    // Decrypt rail fence
    static String decrypt(String cipher, int rails) {

        char[][] fence = new char[rails][cipher.length()];
        boolean down = false;
        int row = 0;

        // Mark positions with '*'
        for (int i = 0; i < cipher.length(); i++) {

            fence[row][i] = '*';

            if (row == 0 || row == rails - 1)
                down = !down;

            row += down ? 1 : -1;
        }

        // Fill characters
        int index = 0;
        for (int i = 0; i < rails; i++)
            for (int j = 0; j < cipher.length(); j++)
                if (fence[i][j] == '*')
                    fence[i][j] = cipher.charAt(index++);

        // Read in zig-zag order
        StringBuilder result = new StringBuilder();

        down = false;
        row = 0;

        for (int i = 0; i < cipher.length(); i++) {

            result.append(fence[row][i]);

            if (row == 0 || row == rails - 1)
                down = !down;

            row += down ? 1 : -1;
        }

        return result.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter number of rails: ");
        int rails = sc.nextInt();

        String encrypted = encrypt(text, rails);
        String decrypted = decrypt(encrypted, rails);

        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}

/*
 * EXPLANATION:
 * - Letters are written in a zig-zag across rails.
 * - Read row-wise to get cipher.
 * - Decryption reconstructs the zig-zag and reads it properly.
 * 
 * SAMPLE INPUT 1:
 * Enter text: HELLO WORLD
 * Enter number of rails: 3
 * 
 * SAMPLE OUTPUT 1:
 * Encrypted: HOLELWRDLO
 * Decrypted: HELLOWORLD
 * 
 * SAMPLE INPUT 2:
 * Enter text: DEFEND THE EAST WALL
 * Enter number of rails: 4
 * 
 * SAMPLE OUTPUT 2:
 * Encrypted: DTTEDHSWFNEAALEEL
 * Decrypted: DEFENDTHEEASTWALL
 */
