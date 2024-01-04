import java.util.Scanner;

public class Question1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int T = input.nextInt();
        for (int i = 0; i < T; i++) {
            String str = input.next();
            int l = str.length();
            System.out.println((l + 1) * l / 2);
        }
    }
}
