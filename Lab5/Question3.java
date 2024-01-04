import java.io.*;
import java.util.*;

public class Question3 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       String str = in.next();
       int[][] res = new int[str.length()][26];
       int[] next = new int[str.length()];
       for (int pin1 = 0,pin2 = 1 ; pin2 < str.length(); pin2++) {
           while (pin1 > 0&&str.charAt(pin1) != str.charAt(pin2)) {
               pin1 = next[pin1 - 1];
           }
           if (str.charAt(pin1) == str.charAt(pin2)) {
               pin1++;
           }
           next[pin2] = pin1;
       }
       for (int pin1 = 0,pin2 = 1,pin1History1 = 0,pin1History0 = 0;pin2 < str.length();pin2++) {
           for (int i = 0; i < 26; i++) {
               pin1 = pin1History0;
               char c = (char) ('a' + i);
               while (pin1 > 0&&str.charAt(pin1) != c) {
                   pin1 = next[pin1 - 1];
               }
               if (str.charAt(pin1) == c) {
                   pin1++;
               }
               res[pin2][i] = pin1;
               if (c == str.charAt(pin2)) {
                   pin1History1 = pin1;
               }
           }
           pin1History0 = pin1History1;
       }
       for (int i = 0; i < str.length(); i++) {
           res[i][charToInt(str.charAt(i))] = i + 1;
       }
       for (int i = 0; i < str.length(); i++) {
           for (int j = 0; j < 26; j++) {
               out.print(res[i][j]);
               out.print(" ");
           }
           out.print("\n");
       }
       out.close();
   }
   public static int charToInt(char c) {
       if (c >= 'a' && c <= 'z') {
           return c - 'a';
       } else {
           return -1;
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