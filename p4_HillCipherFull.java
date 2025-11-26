import java.util.*;

public class p4_HillCipherFull {

    // Encrypt using 2x2 Hill Matrix
    static String encrypt(String msg, int[][] key) {

        msg = msg.toUpperCase().replaceAll("[^A-Z]", "");

        if (msg.length() % 2 != 0)
            msg += "X"; // Padding

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < msg.length(); i += 2) {

            int p1 = msg.charAt(i) - 'A';
            int p2 = msg.charAt(i + 1) - 'A';

            int c1 = (key[0][0] * p1 + key[0][1] * p2) % 26;
            int c2 = (key[1][0] * p1 + key[1][1] * p2) % 26;

            res.append((char) (c1 + 'A'))
                    .append((char) (c2 + 'A'));
        }

        return res.toString();
    }

    // Modular inverse
    static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++)
            if ((a * x) % m == 1)
                return x;
        return -1;
    }

    // Inverse matrix for decryption
    static int[][] inverseKey(int[][] key) {

        int det = (key[0][0] * key[1][1] - key[0][1] * key[1][0]) % 26;

        if (det < 0)
            det += 26;

        int detInv = modInverse(det, 26);

        if (detInv == -1)
            throw new IllegalArgumentException("Key not invertible!");

        int[][] inv = new int[2][2];

        inv[0][0] = (key[1][1] * detInv) % 26;
        inv[0][1] = ((-key[0][1] * detInv) % 26 + 26) % 26;
        inv[1][0] = ((-key[1][0] * detInv) % 26 + 26) % 26;
        inv[1][1] = (key[0][0] * detInv) % 26;

        return inv;
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        int[][] key = new int[2][2];

        System.out.println("Enter 2x2 key matrix (numbers 0-25):");

        for (int i = 0; i < 2; i++)
            for (int j = 0; j < 2; j++)
                key[i][j] = sc.nextInt();

        sc.nextLine();

        System.out.print("Enter text to encrypt: ");
        String text = sc.nextLine();

        String encrypted = encrypt(text, key);
        System.out.println("Encrypted Text: " + encrypted);

        int[][] invKey = inverseKey(key);
        String decrypted = encrypt(encrypted, invKey);

        System.out.println("Decrypted Text: " + decrypted);

        sc.close();
    }
}

/*
 * EXPLANATION:
 * - Hill Cipher uses matrix multiplication with modulo 26.
 * - Each pair of letters = vector of size 2.
 * - Encryption: C = K × P mod 26
 * - Decryption: P = K⁻¹ × C mod 26
 * - Must compute inverse matrix mod 26.
 * 
 * SAMPLE INPUT 1:
 * Enter 2x2 key matrix (numbers 0-25):
 * 3 3
 * 2 5
 * Enter text to encrypt: HELP
 * 
 * SAMPLE OUTPUT 1:
 * Encrypted Text: HIAT
 * Decrypted Text: HELP
 * 
 * SAMPLE INPUT 2:
 * Enter 2x2 key matrix (numbers 0-25):
 * 5 8
 * 17 3
 * Enter text to encrypt: ATTACK
 * 
 * SAMPLE OUTPUT 2:
 * Encrypted Text: WFRLMM
 * Decrypted Text: ATTACK
 */
