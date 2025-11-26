import java.util.Scanner;

public class p3_BitwiseOperations {

    public static void AndOperation(String s) {
        System.out.print("AND : [ ");
        for (char c : s.toCharArray()) {
            System.out.print((c & 127) + " ");
        }
        System.out.print("] Characters : ");
        for (char c : s.toCharArray()) {
            System.out.print((char) (c & 127));
        }
        System.out.println();
    }

    public static void OrOperation(String s) {
        System.out.print("OR  : [ ");
        for (char c : s.toCharArray()) {
            System.out.print((c | 127) + " ");
        }
        System.out.print("] Characters : ");
        for (char c : s.toCharArray()) {
            System.out.print((char) (c | 127));
        }
        System.out.println();
    }

    public static void XorOperation(String s) {
        System.out.print("XOR : [ ");
        for (char c : s.toCharArray()) {
            System.out.print((c ^ 127) + " ");
        }
        System.out.print("] Characters : ");
        for (char c : s.toCharArray()) {
            System.out.print((char) (c ^ 127));
        }
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String s1 = sc.nextLine();

        System.out.print("Enter second string: ");
        String s2 = sc.nextLine();

        System.out.println("\n--- Results for String 1 ---");
        AndOperation(s1);
        OrOperation(s1);
        XorOperation(s1);

        System.out.println("\n--- Results for String 2 ---");
        AndOperation(s2);
        OrOperation(s2);
        XorOperation(s2);
    }
}

/*
 * EXPLANATION (Simple):
 * ----------------------
 * Bitwise AND (&):
 * - Compares each bit of character ASCII with 127.
 * - 127 = 01111111 → removes highest bit.
 * - Useful in masking.
 * 
 * Bitwise OR (|):
 * - Sets all lower 7 bits.
 * - Character becomes extended (usually shows 127/DEL).
 * 
 * Bitwise XOR (^):
 * - Flips bits where 127 has 1.
 * - Used in simple encryption (XOR cipher).
 * 
 * Overall:
 * Each character → converted to ASCII → bit operation → converted back.
 * 
 * SAMPLE INPUT 1:
 * Enter first string: Hello
 * Enter second string: World
 * 
 * SAMPLE OUTPUT 1:
 * --- Results for String 1 ---
 * AND : [ 72 101 108 108 111 ] Characters : Hello
 * OR : [ 127 127 127 127 127 ] Characters :
 * XOR : [ 55 26 19 19 16 ] Characters : 7
 * 
 * --- Results for String 2 ---
 * AND : [ 87 111 114 108 100 ] Characters : World
 * OR : [ 127 127 127 127 127 ] Characters :
 * XOR : [ 40 16 13 19 27 ] Characters : (
 * 
 * SAMPLE INPUT 2:
 * Enter first string: Java
 * Enter second string: Code
 * 
 * SAMPLE OUTPUT 2:
 * --- Results for String 1 ---
 * AND : [ 74 97 118 97 ] Characters : Java
 * OR : [ 127 127 127 127 ] Characters :
 * XOR : [ 53 30 9 30 ] Characters : 5
 * 
 * --- Results for String 2 ---
 * AND : [ 67 111 100 101 ] Characters : Code
 * OR : [ 127 127 127 127 ] Characters :
 * XOR : [ 60 16 27 26 ] Characters : <
 */
