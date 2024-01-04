import java.util.Scanner;

public class Question4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int S = input.nextInt();
        int[]a = new int[n+1];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        a[n] = 1000000005;
        long count = 0;//左j中i右k
        for (int i = 0; i < n - 1; i++) {
            int k = i + 1;
            for (int j = i - 1; j >= 0; j--) {
                while (k<=n&&a[i] + a[j] + a[k] < S) {
                    k++;
                }
                int k2 = k;
                while (k2 <= n&&a[i] + a[j] + a[k2] <= S) {
                    k2++;
                }
                count += (k2 - k);
            }
        }
        System.out.println(count);
    }

}
