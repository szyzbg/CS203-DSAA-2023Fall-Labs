import java.io.*;
import java.util.*;

public class Question2 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int n = in.nextInt();
       int p = in.nextInt();
       int q = in.nextInt();
       int[] line1 = new int[p];
       int[] line2 = new int[q];
       int[] ans = new int[n];//一定记住ans[j - 1]对应第j组
       for (int i = 0; i < p; i++) {
           line1[i] = in.nextInt();
       }
       for (int i = 0; i < q; i++) {
           line2[i] = in.nextInt();
       }
       int count = 1;
       int pin1 = 0;
       int pin2 = 0;
       while (pin1 < p||pin2 < q) {
           if (pin1 < p&&pin2 < q) {
               while (pin1 < p&&ans[line1[pin1] - 1] != 0) {
                   pin1++;
               }
               while (pin2 < q&&ans[line2[pin2] - 1] != 0) {
                   pin2++;
               }
               if (pin1 < p&&pin2 < q) {
                   ans[line1[pin1] - 1] = count;
                   ans[line2[pin2] - 1] = count;
                   count++;
               }
               else if (pin1 >= p&&pin2 < q) {
                   ans[line2[pin2] - 1] = count;
                   count++;
               }
               else if (pin1 < p&&pin2 >= q) {
                   ans[line1[pin1] - 1] = count;
                   count++;
               }
           }
           else if (pin1 >= p) {
               while (pin2 < q&&ans[line2[pin2] - 1] != 0) {
                   pin2++;
               }
               if (pin2 < q) {
                   ans[line2[pin2] - 1] = count;
                   count++;
               }
           }
           else {
               while (pin1 < p&&ans[line1[pin1] - 1] != 0) {
                   pin1++;
               }
               if (pin1 < p) {
                   ans[line1[pin1] - 1] = count;
                   count++;
               }
           }
       }
       for (int i = 0; i < ans.length; i++) {
           out.print(ans[i]);
           out.print(" ");
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