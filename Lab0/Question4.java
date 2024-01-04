import java.io.*;
import java.util.*;

public class Question4 {
    public static void main(String[] args) {
        //sample
        QReader in = new QReader();
        QWriter out = new QWriter();
        int cases = in.nextInt();
        for (int i = 0; i < cases; i++) {
            int n = in.nextInt();
            int[] ori = new int[n];
            for (int j = 0; j < n; j++) {
                ori[j] = in.nextInt();
            }
            int hindmin = ori[n-1];
            int maxdiff = ori[n-2]-ori[n-1];
            for (int j = n-1; j >= 0 ; j--) {
                if (hindmin > ori[j]) {
                    hindmin = ori[j];
                }
                if (j > 0) {
                    if (maxdiff < ori[j - 1] - hindmin) {
                        maxdiff = ori[j - 1] - hindmin;
                    }
                }
            }
            out.println(maxdiff);
        }
        out.close();
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
