import java.util.Scanner;

public class p2_EuclideanAlgorithm {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter value of A: ");
        int A = sc.nextInt();

        System.out.print("Enter value of B: ");
        int B = sc.nextInt();

        System.out.printf("%-5s%-10s%-10s%-10s%n", "Q", "A", "B", "R");
        System.out.println("-----------------------------------");

        while (B != 0) {
            int Q = A / B;
            int R = A % B;

            System.out.printf("%-5d%-10d%-10d%-10d%n", Q, A, B, R);

            A = B;
            B = R;
        }

        System.out.println("\nGCD = " + A);
    }
}

/*
 * EXPLANATION:
 * ------------
 * Uses repeated division to find gcd of two numbers.
 * 
 * Steps:
 * 1. Divide A by B → Q (quotient), R (remainder)
 * 2. Replace:
 * A = B
 * B = R
 * 3. Continue until R = 0
 * 4. The last non-zero B (or A) is gcd.
 * 
 * Example:
 * A=47, B=12
 * 47 = 12*3 + 11
 * 12 = 11*1 + 1
 * 11 = 1*11 + 0 → gcd=1
 * 
 * SAMPLE INPUT 1:
 * Enter value of A: 47
 * Enter value of B: 12
 * 
 * SAMPLE OUTPUT 1:
 * Q A B R
 * -----------------------------------
 * 3 47 12 11
 * 1 12 11 1
 * 11 11 1 0
 * 
 * GCD = 1
 * 
 * SAMPLE INPUT 2:
 * Enter value of A: 105
 * Enter value of B: 30
 * 
 * SAMPLE OUTPUT 2:
 * Q A B R
 * -----------------------------------
 * 3 105 30 15
 * 2 30 15 0
 * 
 * GCD = 15
 */
