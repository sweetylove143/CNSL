import java.util.Scanner;

public class EuclideanAlgorithm {
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

            // shift values: B → A, R → B
            A = B;
            B = R;
        }

        System.out.println("\nGCD = " + A);
    }
}
