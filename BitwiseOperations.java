public class BitwiseOperations {

    public static void AndOperation(String s) {
        System.out.print("AND : [ ");
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) & 127;
            System.out.print(val + " ");
        }
        System.out.print("] Characters : ");
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) & 127;
            System.out.print((char) val);
        }
        System.out.println();
    }

    public static void OrOperation(String s) {
        System.out.print("OR  : [ ");
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) | 127;
            System.out.print(val + " ");
        }
        System.out.print("] Characters : ");
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) | 127;
            System.out.print((char) val);
        }
        System.out.println();
    }

    public static void XorOperation(String s) {
        System.out.print("XOR : [ ");
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) ^ 127;
            System.out.print(val + " ");
        }
        System.out.print("] Characters : ");
        for (int i = 0; i < s.length(); i++) {
            int val = s.charAt(i) ^ 127;
            System.out.print((char) val);
        }
        System.out.println();
    }

    public static void main(String[] args) {
        String string = "Rahul Bhai";
        AndOperation(string);
        OrOperation(string);
        XorOperation(string);
    }
}
