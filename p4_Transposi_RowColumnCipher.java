import java.util.Scanner;

public class p4_Transposi_RowColumnCipher {

    static String encrypt(String text, int key) {

        text = text.replaceAll("\\s+", "").toUpperCase();

        int rows = (int) Math.ceil((double) text.length() / key);

        char[][] matrix = new char[rows][key];

        int index = 0;

        // Fill row-wise
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < key; j++) {

                if (index < text.length())
                    matrix[i][j] = text.charAt(index++);
                else
                    matrix[i][j] = 'X'; // padding
            }
        }

        // Read column-wise
        StringBuilder encrypted = new StringBuilder();

        for (int j = 0; j < key; j++) {
            for (int i = 0; i < rows; i++) {
                encrypted.append(matrix[i][j]);
            }
        }

        return encrypted.toString();
    }

    static String decrypt(String cipher, int key) {

        int rows = (int) Math.ceil((double) cipher.length() / key);

        char[][] matrix = new char[rows][key];

        int index = 0;

        // Fill column-wise for decryption
        for (int j = 0; j < key; j++) {
            for (int i = 0; i < rows; i++) {
                matrix[i][j] = cipher.charAt(index++);
            }
        }

        // Read row-wise
        StringBuilder decrypted = new StringBuilder();

        for (int i = 0; i < rows; i++)
            for (int j = 0; j < key; j++)
                decrypted.append(matrix[i][j]);

        return decrypted.toString();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter text: ");
        String text = sc.nextLine();

        System.out.print("Enter key (number of columns): ");
        int key = sc.nextInt();

        String encrypted = encrypt(text, key);
        String decrypted = decrypt(encrypted, key);

        System.out.println("\nEncrypted: " + encrypted);
        System.out.println("Decrypted: " + decrypted);

        sc.close();
    }
}

/*
 * EXPLANATION:
 * - Write plaintext row-wise into a matrix.
 * - Read column-wise → cipher text.
 * - Decryption reverses the process.
 * 
 * SAMPLE INPUT 1:
 * Enter text: HELLO WORLD
 * Enter key (number of columns): 4
 * 
 * Matrix fill row-wise:
 * H E L L
 * O W O R
 * L D X X
 * 
 * Cipher (column-wise):
 * H O L E W D L O O R X X
 * 
 * SAMPLE OUTPUT 1:
 * Encrypted: HOLEWDLOXLRX
 * Decrypted: HELLOWORLDXX
 * 
 * SAMPLE INPUT 2:
 * Enter text: ATTACK AT DAWN
 * Enter key (number of columns): 3
 * 
 * SAMPLE OUTPUT 2:
 * Encrypted: AAAATCTWTKDN
 * Decrypted: ATTACKATDAWN
 */
