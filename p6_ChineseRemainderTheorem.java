import java.util.Scanner;

public class p6_ChineseRemainderTheorem {

    // Function to find modular inverse using simple search (valid for CRT small
    // inputs)
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1)
                return x;
        }
        return 1;
    }

    // Function to compute CRT
    public static int findX(int[] mod, int[] rem, int k) {
        int prod = 1;
        for (int i = 0; i < k; i++)
            prod *= mod[i];

        int result = 0;

        for (int i = 0; i < k; i++) {
            int pp = prod / mod[i]; // partial product
            int inv = modInverse(pp, mod[i]);
            result += rem[i] * inv * pp;
        }

        return result % prod;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        int k = 2; // exactly 2 congruences required
        int[] rem = new int[k];
        int[] mod = new int[k];

        System.out.println("Enter 2 congruences (a mod m):");

        for (int i = 0; i < k; i++) {
            System.out.print("Enter remainder a" + (i + 1) + ": ");
            rem[i] = sc.nextInt();

            System.out.print("Enter modulus m" + (i + 1) + ": ");
            mod[i] = sc.nextInt();
        }

        int x = findX(mod, rem, k);
        int prod = mod[0] * mod[1];

        System.out.println("\nFinal Answer: x = " + x + " (mod " + prod + ")");
    }
}

/*
 * EXPLANATION:
 * CRT solves:
 * x ≡ a1 (mod m1)
 * x ≡ a2 (mod m2)
 * 
 * Steps:
 * 1) Compute total product: M = m1 * m2
 * 2) For each equation:
 * PP = M / mi
 * Inverse = modular inverse of PP mod mi
 * Contribution = ai * Inverse * PP
 * 3) Sum contributions and take mod M.
 * 
 * This program handles EXACTLY 2 inputs as requested.
 * 
 * SAMPLE INPUT 1:
 * Enter 2 congruences (a mod m):
 * Enter remainder a1: 2
 * Enter modulus m1: 3
 * Enter remainder a2: 3
 * Enter modulus m2: 5
 * 
 * SAMPLE OUTPUT 1:
 * Final Answer: x = 8 (mod 15)
 * 
 * SAMPLE INPUT 2:
 * Enter 2 congruences (a mod m):
 * Enter remainder a1: 1
 * Enter modulus m1: 5
 * Enter remainder a2: 4
 * Enter modulus m2: 7
 * 
 * SAMPLE OUTPUT 2:
 * Final Answer: x = 11 (mod 35)
 */
