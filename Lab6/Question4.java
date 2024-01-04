import java.io.*;
import java.util.*;

public class Question4 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int T = in.nextInt();
       for (int i = 0; i < T; i++) {
           int N = in.nextInt();

           long[] x = new long[N];
           for (int j = 0; j < N; j++) {
               x[j] = in.nextLong();
           }
           long count = 0;

           for (int j = 0; j < N - 1; j++) {
               int minIndex = 0;
               for (int k = 0; k < N; k++) {
                   if (x[k] < x[minIndex]) minIndex = k;
               }
               long min = x[minIndex];
               x[minIndex] = 100000000;
               minIndex = 0;
               for (int k = 0; k < N; k++) {
                   if (x[k] < x[minIndex]) minIndex = k;
               }
               long secondMin = x[minIndex];
               x[minIndex] = min + secondMin;
               count += x[minIndex];
           }
           out.println(count);
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