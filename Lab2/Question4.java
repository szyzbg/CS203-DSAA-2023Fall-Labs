import java.util.*;

public class Question4 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int k = input.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextLong();
        }
        mergeSort(a,a.length);
        System.out.println(a[k - 1]);
    }
    static long[] merge(long[] a,int n,long[] b,int m) {
        long[] ans = new long[n+m];
        int x = 0,y = 0;
        for (int j = 0; j < n+m; j++) {
            if (x<=n-1&&(y>m-1||a[x]<=b[y])) {
                ans[j] = a[x];
                x++;
            }
            else {
                ans[j] = b[y];
                y++;
            }
        }
        return ans;
    }

    static void mergeSort(long[] a,int n) {
        if (n > 1) {
            int p = n / 2;
            long[] b = new long[p];
            for (int i = 0; i < p; i++) {
                b[i] = a[i];
            }
            long[] c = new long[n - p];
            for (int i = 0; i < n - p; i++) {
                c[i] = a[i + p];
            }
            mergeSort(b,p);
            mergeSort(c,n-p);
            long[] ans = merge(b,p,c,n-p);
            for (int i = 0; i < n; i++) {
                a[i] = ans[i];
            }
        }

    }
}
