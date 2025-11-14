import java.util.Scanner;

public class ExtendedEuclidean {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter a: ");
        int a = sc.nextInt();
        System.out.print("Enter b (modulus): ");
        int b = sc.nextInt();

        int A = a, B = b;
        int t1 = 0, t2 = 1, t = 0;

        System.out.printf("%-5s%-8s%-8s%-8s%-8s%-8s%-8s%n", "q", "a", "b", "r", "t1", "t2", "t");
        System.out.println("------------------------------------------------------");

        while (B != 0) {
            int q = A / B;
            int r = A % B;
            t = t1 - q * t2;

            System.out.printf("%-5d%-8d%-8d%-8d%-8d%-8d%-8d%n", q, A, B, r, t1, t2, t);

            A = B;
            B = r;
            t1 = t2;
            t2 = t;
        }

        System.out.println("\ngcd = " + A);

        if (A == 1) {
            int inverse = (t1 % b + b) % b;
            System.out.println("Multiplicative Inverse of " + a + " mod " + b + " = " + inverse);
        } else {
            System.out.println("No Multiplicative Inverse exists since gcd != 1.");
        }
    }
}
