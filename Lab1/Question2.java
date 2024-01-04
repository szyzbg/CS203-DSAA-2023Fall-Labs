import java.util.Scanner;

public class Question2 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            long x = input.nextInt();
            long a = x*(x+1)/2;
            long b = (x*(x+1))*(2*x+1)/6;
            long ans = (a + b) / 2;
            System.out.println(ans);
        }
    }
}
