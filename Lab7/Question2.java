import java.io.*;
import java.util.*;

public class Question2 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int n = in.nextInt();
       int[] insertion = new int[n];
       int[] res = new int[n];
       int[] father = new int[n];
       for (int i = 0; i < n; i++) {
           insertion[i] = in.nextInt();
       }
       Deque<Integer> fatherQueue = new LinkedList<>();
       fatherQueue.addFirst(0);
       for (int i = 1,cnt = 0; i < n; i++) {
           cnt++;
           father[i] = fatherQueue.peekLast();
           fatherQueue.addFirst(i);
           if (cnt == 2) {
               cnt = 0;
               fatherQueue.pollLast();
           }
       }
       for (int i = 0; i < n; i++) {
           if (i != 0) {
               int iter = i;
               while (iter != 0&&insertion[iter] > insertion[father[iter]]) {
                   int tmp = insertion[iter];
                   insertion[iter] = insertion[father[iter]];
                   insertion[father[iter]] = tmp;
                   iter = father[iter];
                   res[i]++;
               }
           }
       }
       for (int i = 0; i < n; i++) {
           out.print(res[i]);
           out.print(" ");
       }
       out.print("\n");
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