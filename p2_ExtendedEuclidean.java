import java.util.Scanner;

public class p2_ExtendedEuclidean {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first number a: ");
        int a = sc.nextInt();
        System.out.print("Enter second number b: ");
        int b = sc.nextInt();

        int A = a, B = b;
        int t1 = 0, t2 = 1, t = 0;
        int s1 = 1, s2 = 0, s = 0;

        System.out.printf("%-5s%-8s%-8s%-8s%-8s%-8s%-8s%-8s%-8s%-8s%n", "q", "a", "b", "r", "s1", "s2", "s", "t1", "t2",
                "t");
        System.out.println("--------------------------------------------------------------------------------");

        while (B != 0) {
            int q = A / B;
            int r = A % B;

            t = t1 - q * t2;
            s = s1 - q * s2;

            System.out.printf("%-5d%-8d%-8d%-8d%-8d%-8d%-8d%-8d%-8d%-8d%n", q, A, B, r, s1, s2, s, t1, t2, t);

            A = B;
            B = r;
            t1 = t2;
            t2 = t;
            s1 = s2;
            s2 = s;
        }

        System.out.println("\ngcd = " + A);

        if (A == 1) {
            int inverse = (s1 % b + b) % b;
            System.out.println("Multiplicative Inverse of " + a + " mod " + b + " = " + inverse);
        } else {
            System.out.println("NO Multiplicative Inverse exists because gcd ≠ 1");
        }
    }
}

/*
 * EXPLANATION:
 * ------------
 * Goal:
 * Find gcd(a, b) and "s", "t" values such that:
 * a*s + b*t = gcd(a, b)
 * 
 * Also used to find multiplicative inverse of 'a mod b'.
 * Inverse of a mod b is 's'.
 * 
 * Working:
 * - Divide A by B every step.
 * - Record q = quotient, r = remainder.
 * - Update coefficients s1, s2, t1, t2 to maintain equation.
 * 
 * When B becomes 0:
 * - A = gcd
 * - If gcd = 1 → inverse exists.
 * 
 * SAMPLE INPUT 1:
 * Enter first number a: 26
 * Enter second number b: 11
 * 
 * SAMPLE OUTPUT 1:
 * q a b r s1 s2 s t1 t2 t
 * -----------------------------------------------------------------------------
 * ---
 * 2 26 11 4 1 0 1 0 1 -2
 * 2 11 4 3 0 1 -2 1 -2 5
 * 1 4 3 1 1 -2 3 -2 5 -7
 * 3 3 1 0 -2 3 -11 5 -7 26
 * 
 * gcd = 1
 * Multiplicative Inverse of 26 mod 11 = 3
 * 
 * SAMPLE INPUT 2:
 * Enter first number a: 10
 * Enter second number b: 6
 * 
 * SAMPLE OUTPUT 2:
 * q a b r s1 s2 s t1 t2 t
 * -----------------------------------------------------------------------------
 * ---
 * 1 10 6 4 1 0 1 0 1 -1
 * 1 6 4 2 0 1 -1 1 -1 2
 * 2 4 2 0 1 -1 3 -1 2 -5
 * 
 * gcd = 2
 * NO Multiplicative Inverse exists because gcd ≠ 1
 */
