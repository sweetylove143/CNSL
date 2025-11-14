import java.util.*;

public class Feistel {
    static void feistelWorking(String bin) {
        String L = bin.substring(0, 32), R = bin.substring(32);
        String K = String.format("%32s", Integer.toBinaryString(new Random().nextInt(42949672)+1)).replace(' ','0');

        StringBuilder xor1 = new StringBuilder(), xor2 = new StringBuilder();
        for (int i = 0; i < 32; i++) xor1.append((R.charAt(i)-'0') ^ (K.charAt(i)-'0'));
        for (int i = 0; i < 32; i++) xor2.append((xor1.charAt(i)-'0') ^ (L.charAt(i)-'0'));

        String total = xor2.toString() + R;
        System.out.print("The cipher text after 1 round is : ");
        for (int i = 0; i < 64; i += 8) System.out.print((char)Integer.parseInt(total.substring(i,i+8),2));
        System.out.println();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Enter 8-char message: ");
        String msg = sc.nextLine(), bin="";
        for (char c: msg.toCharArray()) bin += String.format("%8s", Integer.toBinaryString(c)).replace(' ','0');
        feistelWorking(bin);
    }
}
