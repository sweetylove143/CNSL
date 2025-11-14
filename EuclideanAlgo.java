import java.util.Scanner;

public class EuclideanAlgo {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input
        System.out.print("Enter first number (a): ");
        int a = sc.nextInt();
        System.out.print("Enter second number (b): ");
        int b = sc.nextInt();

        // Initialize
        int dividend = a;
        int divisor = b;
        int t0 = 0; // coefficient for previous divisor
        int t1 = 1; // coefficient for current divisor
        int step = 1;

        System.out.printf("%-5s %-9s %-8s %-5s %-9s %-5s %-5s %-5s\n",
                "Step", "Dividend", "Divisor", "Q", "Remainder", "T0", "T1", "T");

        // Loop
        while (divisor != 0) {
            int q = dividend / divisor;
            int r = dividend % divisor;
            int t = t0 - q * t1;

            // Print current row
            System.out.printf("%-5d %-9d %-8d %-5d %-9d %-5d %-5d %-5d\n",
                    step, dividend, divisor, q, r, t0, t1, t);

            // Shift for next iteration
            dividend = divisor;
            divisor = r;
            t0 = t1;
            t1 = t;
            step++;
        }

        System.out.println("\nGCD = " + dividend);
    }
}