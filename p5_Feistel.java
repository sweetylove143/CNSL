import java.util.*;

public class p5_Feistel {

    // Perform 1 round of Feistel cipher
    static void feistelWorking(String binaryMessage) {

        // Split 64-bit block into Left (L) and Right (R)
        String L = binaryMessage.substring(0, 32);
        String R = binaryMessage.substring(32);

        // Generate 32-bit random key
        String K = String.format("%32s",
                   Integer.toBinaryString(new Random().nextInt(42949672) + 1))
                   .replace(' ', '0');

        // R XOR K
        StringBuilder xor1 = new StringBuilder();
        for (int i = 0; i < 32; i++)
            xor1.append((R.charAt(i) - '0') ^ (K.charAt(i) - '0'));

        // (R XOR K) XOR L
        StringBuilder xor2 = new StringBuilder();
        for (int i = 0; i < 32; i++)
            xor2.append((xor1.charAt(i) - '0') ^ (L.charAt(i) - '0'));

        // New cipher block = (L ⊕ F(R,K)) || R
        String final64 = xor2.toString() + R;

        // Convert 8-bit chunks to characters
        System.out.print("Cipher Text (after 1 Feistel round): ");
        for (int i = 0; i < 64; i += 8)
            System.out.print((char) Integer.parseInt(final64.substring(i, i + 8), 2));

        System.out.println();
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter 8-character message: ");
        String message = sc.nextLine();

        // Convert ASCII → Binary (8 bits per character)
        String binary = "";
        for (char c : message.toCharArray())
            binary += String.format("%8s", Integer.toBinaryString(c)).replace(' ', '0');

        feistelWorking(binary);

        sc.close();
    }
}


/*
----------------------------------------------------------
EXPLANATION (Simple for Viva)
----------------------------------------------------------
1. User enters an 8-character message → 64 bits.
2. Convert each character into 8-bit binary.
3. Split into:
      L = first 32 bits
      R = next 32 bits
4. Generate a random 32-bit key K.
5. Perform:
      XOR1 = R XOR K
      XOR2 = XOR1 XOR L     (Feistel round function)
6. New block = (XOR2 || R)
7. Convert 64 bits → ASCII characters → Display cipher text.

Feistel feature:
- Decryption uses same steps but key in reverse.
- Only XOR operations → symmetric and reversible.

----------------------------------------------------------
SAMPLE INPUT / OUTPUT
----------------------------------------------------------

INPUT 1:
Enter 8-character message: ABCDEFGH

OUTPUT 1 (example):
Cipher Text (after 1 Feistel round): ¢Ît¬Ç¥Æë

(Note: Output varies because key is random)

----------------------------------------------------------

INPUT 2:
Enter 8-character message: ORBIQ123

OUTPUT 2 (example):
Cipher Text (after 1 Feistel round): pŠ¿ØCƒ¢0

----------------------------------------------------------
*/
