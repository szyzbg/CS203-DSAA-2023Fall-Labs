import java.util.Arrays;
import java.util.Scanner;

public class Question1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n];
        for (int i = 0; i < n; i++) {
            a[i] = input.nextInt();
        }
        Arrays.sort(a);
        int t = input.nextInt();
        for (int i = 0; i < t; i++) {
            int l = input.nextInt();
            test(a,0,n-1,l);
        }
    }
    public static void test(int[] a,int x,int y,int l) {
        int mid = (x + y) / 2;
        if (x<=y) {
            if (l==a[mid]) System.out.println("YES");
            else if (l>a[mid]) test(a,mid+1,y,l);
            else test(a,x,mid-1,l);
        }

        /*while(x<=y){
            mid=(x+y)/2;
            if(l==a[mid]) System.out.println("YES");
            else if(l>a[mid]) x=mid+1;

        }
        else System.out.println("NO");*/

    }


}
