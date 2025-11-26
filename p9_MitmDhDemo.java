import java.math.BigInteger;
import java.security.SecureRandom;
import java.util.Scanner;

public class p9_MitmDhDemo {

    private static final SecureRandom random = new SecureRandom();

    // Read BigInteger safely
    private static BigInteger getBigInt(Scanner sc, String prompt) {
        while (true) {
            System.out.print(prompt);
            String s = sc.nextLine().trim();
            try {
                return new BigInteger(s);
            } catch (Exception e) {
                System.out.println("Invalid number! Try again.");
            }
        }
    }

    // Compute a^x mod q
    private static BigInteger pub(BigInteger a, BigInteger x, BigInteger q) {
        return a.modPow(x, q);
    }

    // Compute shared secret
    private static BigInteger shared(BigInteger pubOther, BigInteger x, BigInteger q) {
        return pubOther.modPow(x, q);
    }

    private static BigInteger randomInRangeTwoToQminus2(BigInteger q) {
        BigInteger two = BigInteger.valueOf(2);
        BigInteger max = q.subtract(two); // q-2

        if (max.compareTo(two) < 0)
            return two;

        BigInteger bound = max.subtract(two).add(BigInteger.ONE);
        BigInteger r;
        do {
            r = new BigInteger(bound.bitLength(), random);
        } while (r.compareTo(bound) >= 0);

        return r.add(two); // range [2 ... q-2]
    }

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        System.out.println("===== MITM Diffie-Hellman Attack Demo =====\n");

        BigInteger q = getBigInt(sc, "Enter q (prime modulus): ");
        BigInteger a = getBigInt(sc, "Enter a (base): ");
        BigInteger xa = getBigInt(sc, "Enter Alice's private key xa: ");
        BigInteger xb = getBigInt(sc, "Enter Bob's private key xb: ");

        System.out.print("Enter attacker's private xm (leave blank = random): ");
        String xmInput = sc.nextLine().trim();
        BigInteger xm;

        if (xmInput.isEmpty()) {
            xm = randomInRangeTwoToQminus2(q);
            System.out.println("Attacker XM chosen randomly = " + xm + "\n");
        } else {
            try {
                xm = new BigInteger(xmInput);
            } catch (Exception e) {
                xm = randomInRangeTwoToQminus2(q);
                System.out.println("Invalid XM! Random XM used = " + xm + "\n");
            }
        }

        // Calculate public values
        BigInteger A_pub = pub(a, xa, q);
        BigInteger B_pub = pub(a, xb, q);
        BigInteger M_pub = pub(a, xm, q);

        // Shared secrets
        BigInteger S_alice = shared(M_pub, xa, q);
        BigInteger S_bob = shared(M_pub, xb, q);

        BigInteger S_attackA = shared(A_pub, xm, q);
        BigInteger S_attackB = shared(B_pub, xm, q);

        System.out.println("===== MITM Attack Results =====");
        System.out.println("Alice's Public: " + A_pub);
        System.out.println("Bob's Public  : " + B_pub);
        System.out.println("Attacker Sends: " + M_pub);

        System.out.println("\nAlice Computes: " + S_alice);
        System.out.println("Bob Computes  : " + S_bob);

        System.out.println("\nAttacker Secret with Alice: " + S_attackA);
        System.out.println("Attacker Secret with Bob  : " + S_attackB);

        System.out.println("\nCheck:");
        System.out.println(" Attacker <-> Alice match: " + S_attackA.equals(S_alice));
        System.out.println(" Attacker <-> Bob match  : " + S_attackB.equals(S_bob));

        System.out.println("\nIf both are TRUE → MITM Attack successful.");
        sc.close();
    }
}

/*
 * EXPLANATION:
 * - Simulates a Man-In-The-Middle (MITM) attack on Diffie-Hellman Key Exchange.
 * - Alice and Bob exchange public keys.
 * - Attacker intercepts and sends their own public key to both.
 * - Attacker establishes separate shared secrets with Alice and Bob.
 * 
 * SAMPLE INPUT 1:
 * Enter q (prime modulus): 23
 * Enter a (base): 5
 * Enter Alice's private key xa: 6
 * Enter Bob's private key xb: 15
 * Enter attacker's private xm (leave blank = random):
 * 
 * SAMPLE OUTPUT 1:
 * Attacker XM chosen randomly = [Random Value]
 * ...
 * Attacker <-> Alice match: true
 * Attacker <-> Bob match : true
 * 
 * SAMPLE INPUT 2:
 * Enter q (prime modulus): 29
 * Enter a (base): 2
 * Enter Alice's private key xa: 7
 * Enter Bob's private key xb: 11
 * Enter attacker's private xm (leave blank = random): 9
 * 
 * SAMPLE OUTPUT 2:
 * ...
 * Attacker <-> Alice match: true
 * Attacker <-> Bob match : true
 */