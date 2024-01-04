import java.io.*;
import java.util.*;

public class Question3 {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int N = in.nextInt();
        int Q = in.nextInt();
        double[] a = new double[N+2];
        for (int i = 1; i < N+1; i++) {
            a[i] = in.nextInt();
        }
        a[0] = 0;a[N+1]=1000000005;
        for (int i = 0; i < Q; i++) {
            int x = in.nextInt();
            int y = in.nextInt();
            if (y - x <= 1||y<=a[1]||x>=a[N]) {
                out.println("NO");

            }
            else {
                int ans = findIndex(a,0,N+1,y-0.2)-findIndex(a,0,N+1,x+ 0.2);
                if (ans==0) out.println("NO");
                else out.println("YES" +" "+ (ans));
            }
        }
        out.close();
    }
    public static int findIndex(double[] a,int x,int y,double l) {
        int mid = (x+y)/2;
            if (l>a[mid]&&l<a[mid+1]) {
                return mid;
            }
            else if (l>a[mid+1]) {
                return findIndex(a,mid+1,y,l);
            }
            else return findIndex(a,x,mid,l);

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