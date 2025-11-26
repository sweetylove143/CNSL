import java.math.BigInteger;
import java.util.*;

public class p8_RSAUserDefined {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first prime p: ");
        BigInteger p = sc.nextBigInteger();

        System.out.print("Enter second prime q: ");
        BigInteger q = sc.nextBigInteger();

        BigInteger n = p.multiply(q);
        BigInteger phi = p.subtract(BigInteger.ONE)
                .multiply(q.subtract(BigInteger.ONE));

        BigInteger e = new BigInteger("2");
        while (e.compareTo(phi) < 0) {
            if (e.gcd(phi).equals(BigInteger.ONE))
                break;
            e = e.add(BigInteger.ONE);
        }

        BigInteger d = e.modInverse(phi);

        System.out.println("\nPublic Key:  (" + e + ", " + n + ")");
        System.out.println("Private Key: (" + d + ", " + n + ")");

        System.out.print("\nEnter numeric plaintext: ");
        BigInteger msg = sc.nextBigInteger();

        BigInteger cipher = msg.modPow(e, n);
        BigInteger plain = cipher.modPow(d, n);

        System.out.println("Ciphertext: " + cipher);
        System.out.println("Decrypted : " + plain);
    }
}

/*
 * EXPLANATION:
 * 1) User gives primes p and q.
 * 2) Compute:
 * n = p*q
 * φ(n) = (p-1)(q-1)
 * 3) Choose smallest e such that gcd(e, φ)=1.
 * 4) Compute d = modular inverse of e mod φ.
 * 5) Encryption: C = M^e mod n
 * 6) Decryption: M = C^d mod n
 * 
 * SAMPLE INPUT 1:
 * Enter first prime p: 61
 * Enter second prime q: 53
 * Enter numeric plaintext: 65
 * 
 * SAMPLE OUTPUT 1:
 * Public Key: (7, 3233)
 * Private Key: (1783, 3233)
 * Ciphertext: 1317
 * Decrypted : 65
 * 
 * SAMPLE INPUT 2:
 * Enter first prime p: 11
 * Enter second prime q: 17
 * Enter numeric plaintext: 88
 * 
 * SAMPLE OUTPUT 2:
 * Public Key: (3, 187)
 * Private Key: (107, 187)
 * Ciphertext: 44
 * Decrypted : 88
 */
