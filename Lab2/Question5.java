import java.io.*;
import java.util.*;

public class Question5 {
    public static void main(String[] args) {

        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        long[] a = new long[n];
        for (int i = 0; i < n; i++) {
            a[i] = in.nextLong();
        }
        mergeSort(a,a.length);
        int targetIndex = n / 3;
        long[] ans = new long[n];
        int pin1 = 0;
        int pin2 = 0;
        ArrayList<Long> after1 = new ArrayList<>();
        ArrayList<Long> after2 = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (a[i] < a[targetIndex]) {
                after1.add(a[i]);
            }
            else {
                after2.add(a[i]);
            }
        }

        for (int i = 0; i < n; i++) {
            if (i % 3 == 0 && pin1 < after1.size()) {
                ans[i] = after1.get(pin1);
                pin1++;
            }
            else {
                ans[i] = after2.get(pin2);
                pin2++;
            }
        }
        out.println(ans[1]);
        for (int i = 0; i < n; i++) {
            out.print(ans[i]);
            out.print(" ");
        }
        out.close();
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

class QReader {
    private BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
    private StringTokenizer tokenizer = new StringTokenizer("");

    private String innerNextLine() {
        try {
            return reader.readLine();
        } catch (IOException e) {
            return null;
        }
    }

    public boolean hasNext() {
        while (!tokenizer.hasMoreTokens()) {
            String nextLine = innerNextLine();
            if (nextLine == null) {
                return false;
            }
            tokenizer = new StringTokenizer(nextLine);
        }
        return true;
    }

    public String nextLine() {
        tokenizer = new StringTokenizer("");
        return innerNextLine();
    }

    public String next() {
        hasNext();
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class QWriter implements Closeable {
    private BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

    public void print(Object object) {
        try {
            writer.write(object.toString());
        } catch (IOException e) {
            return;
        }
    }

    public void println(Object object) {
        try {
            writer.write(object.toString());
            writer.write("\n");
        } catch (IOException e) {
            return;
        }
    }

    @Override
    public void close() {
        try {
            writer.close();
        } catch (IOException e) {
            return;
        }
    }
}