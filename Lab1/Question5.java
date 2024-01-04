import java.util.Arrays;
import java.util.Scanner;

public class Question5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        while (input.hasNext()) {
            int L = input.nextInt();
            int n = input.nextInt();
            int m = input.nextInt();
            int[] evdis = new int[n + 2];
            for (int i = 1; i < n + 1; i++) {
                evdis[i] = input.nextInt();
            }
            evdis[0] = 0;
            evdis[n + 1] = L;
            Arrays.sort(evdis);
            System.out.println(testDistance(evdis,0,L,m));
        }
    }
    public static int testDistance(int[]a, int x,int y,int m) {
        int mid = (x + y) / 2;
        int q;int p;
            if (x >= y) return mid;
            else {
                p = 0; q = 0;
                for (int i = m - 1; i > 0; i--) {
                    while ((q < a.length) && ((a[q] - a[p]) <= mid)) {
                        q++;
                    }
                    q--;
                    p = q;
                }

                if (a[a.length-1]-a[p] > mid) return testDistance(a,mid+1,y,m);
                else if (a[a.length-1]-a[p] == mid) return mid;
                else return testDistance(a,x, mid,m);
            }
    }
}
