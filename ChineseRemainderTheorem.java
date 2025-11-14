import java.util.Scanner;

public class ChineseRemainderTheorem {

    // Function to find modular inverse of a under modulo m using Extended Euclidean Algorithm
    public static int modInverse(int a, int m) {
        a = a % m;
        for (int x = 1; x < m; x++) {
            if ((a * x) % m == 1) {
                return x;
            }
        }
        return 1; // if not found, though for CRT we assume solution exists
    }

    // Function to solve system of congruences using CRT
    public static int findX(int[] num, int[] rem, int k) {
        int prod = 1;
        for (int i = 0; i < k; i++) {
            prod *= num[i];
        }

        int result = 0;
        for (int i = 0; i < k; i++) {
            int pp = prod / num[i]; // partial product
            result += rem[i] * modInverse(pp, num[i]) * pp;
        }

        return result % prod;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of congruences: ");
        int k = sc.nextInt();

        int[] rem = new int[k];
        int[] num = new int[k];

        for (int i = 0; i < k; i++) {
            System.out.print("Enter remainder a" + (i + 1) + ": ");
            rem[i] = sc.nextInt();

            System.out.print("Enter modulus m" + (i + 1) + ": ");
            num[i] = sc.nextInt();
        }

        int x = findX(num, rem, k);

        int prod = 1;
        for (int i = 0; i < k; i++) {
            prod *= num[i];
        }

        System.out.println("\nx = " + x + " (mod " + prod + ")");
        System.out.println("Final Answer: x = " + x);

        sc.close();
    }
}
