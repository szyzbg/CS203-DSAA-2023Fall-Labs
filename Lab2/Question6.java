import java.io.*;
import java.util.*;

public class Question6 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int p = in.nextInt();
        int q = in.nextInt();
        long[] h = new long[n];
        long[] s = new long[n];
        for (int i = 0; i < n; i++) {
            h[i] = in.nextLong();
            s[i] = in.nextLong();
        }
        int power = (int) Math.pow(2,p);
        long[] difference = new long[n];
        for (int i = 0; i < n; i++) {
            difference[i] = h[i] - s[i];
        }
        mergeSort(difference,n,h,s);
        long[] afterPowerMore = new long[n];
        for (int i = n - 1; i >= Math.max(n - q,0); i--) {
            if (h[i] * power - s[i] <= 0) afterPowerMore[i] = 0;
            else if (h[i] * power - s[i] > 0&&h[i] - s[i] <= 0) afterPowerMore[i] = h[i] * power - s[i];
            else afterPowerMore[i] = h[i] * power - h[i];
        }
        long afterDropQ = (((n - q) >= 0) && (q > 0) && ((h[n - q] - s[n - q]) > 0)) ? (h[n - q] - s[n - q]) : 0;
        for (int i = n - q - 1; i >= 0; i--) {
            afterPowerMore[i] = (q > 0&&power * h[i] - s[i] - afterDropQ > 0) ? power * h[i] - s[i] - afterDropQ : 0;
        }
        int m = maxIndex(afterPowerMore);
        long sum = 0;
        for (int i = n - 1; i >= Math.max(n - q,0); i--) {
            sum += Math.max(h[i], s[i]);
        }
        for (int i = n - q - 1; i >= 0; i--) {
            sum += s[i];
        }
        sum += afterPowerMore[m];
        out.println(sum);
        out.close();
    }
    static long[] merge(long[] a,int n,long[] b,int m,long[] target1,long[] target2) {
        long[] ans = new long[n+m];
        int x = 0,y = 0;
        for (int j = 0; j < n+m; j++) {
            if (x<=n-1&&(y>m-1||a[x]<=b[y])) {
                ans[j] = target1[x];
                x++;
            }
            else {
                ans[j] = target2[y];
                y++;
            }
        }
        return ans;
    }

    static void mergeSort(long[] a,int n,long[] x,long[] y) {
        if (n > 1) {
            int p = n / 2;
            long[] b = new long[p];long[] x1 = new long[p];long[] y1 = new long[p];
            for (int i = 0; i < p; i++) {
                b[i] = a[i];
                x1[i] = x[i];
                y1[i] = y[i];
            }
            long[] c = new long[n - p];long[] x2 = new long[n - p];long[] y2 = new long[n - p];
            for (int i = 0; i < n - p; i++) {
                c[i] = a[i + p];
                x2[i] = x[i + p];
                y2[i] = y[i + p];
            }
            mergeSort(b,p,x1,y1);
            mergeSort(c,n-p,x2,y2);
            long[] ansx = merge(b,p,c,n-p,x1,x2);
            long[] ansy = merge(b,p,c,n-p,y1,y2);
            long[] ans = merge(b,p,c,n-p);
            for (int i = 0; i < n; i++) {
                x[i] = ansx[i];
                y[i] = ansy[i];
            }
            for (int i = 0; i < n; i++) {
                a[i] = ans[i];
            }
        }

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
    static int maxIndex(long[] a) {
        int maxIn = 0;
        for (int i = 0; i < a.length; i++) {
            if (a[maxIn] < a[i]) maxIn = i;
        }
        return maxIn;
    }
}

//class QReader {
//    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
//    private StringTokenizer tokenizer = new StringTokenizer("");
//
//    private String innerNextLine() {
//        try {
//            return reader.readLine();
//        } catch (IOException e) {
//            return null;
//        }
//    }
//
//    public boolean hasNext() {
//        while (!tokenizer.hasMoreTokens()) {
//            String nextLine = innerNextLine();
//            if (nextLine == null) {
//                return false;
//            }
//            tokenizer = new StringTokenizer(nextLine);
//        }
//        return true;
//    }
//
//    public String nextLine() {
//        tokenizer = new StringTokenizer("");
//        return innerNextLine();
//    }
//
//    public String next() {
//        hasNext();
//        return tokenizer.nextToken();
//    }
//
//    public int nextInt() {
//        return Integer.parseInt(next());
//    }
//
//    public long nextLong() {
//        return Long.parseLong(next());
//    }
//}
//
//class QWriter implements Closeable {
//    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));
//
//    public void print(Object object) {
//        try {
//            writer.write(object.toString());
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    public void println(Object object) {
//        try {
//            writer.write(object.toString());
//            writer.write("\n");
//        } catch (IOException e) {
//            return;
//        }
//    }
//
//    @Override
//    public void close() {
//        try {
//            writer.close();
//        } catch (IOException e) {
//            return;
//        }
//    }
//}