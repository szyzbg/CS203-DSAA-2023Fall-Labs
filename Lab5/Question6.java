import java.io.*;
import java.util.*;

public class Question6 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       HashMap<Character,Character> set = new HashMap<>();
       for (int i = 0; i < 26; i++) {
           char tmp = in.next().charAt(0);
           set.put(tmp, (char) ('a' + i));
       }
       String str = in.next();
       int spl = str.length() / 2;
       StringBuilder AfterTranslation = new StringBuilder();
       for (int i = 0; i < spl; i++) {
           AfterTranslation.append(set.get(str.charAt(i)));
       }
       for (int i = spl; i < str.length(); i++) {
           AfterTranslation.append(str.charAt(i));
       }
       int[] next = findNext(AfterTranslation.toString());
       if (next[next.length - 1] <= spl) {
           out.println(str.length() - next[next.length - 1]);
       }
       else {
           int tmpTransfer = next[next.length - 1];
           while (tmpTransfer > spl) {
               tmpTransfer = next[tmpTransfer - 1];
           }
           out.println(str.length() - tmpTransfer);
       }
       out.close();
   }
   public static int[] findNext(String str) {
       int strLength = str.length();
       int[] next = new int[strLength];
       for (int pin1 = 0,pin2 = 1 ; pin2 < strLength; pin2++) {
           while (pin1 > 0&&str.charAt(pin1) != str.charAt(pin2)) {
               pin1 = next[pin1 - 1];
           }
           if (str.charAt(pin1) == str.charAt(pin2)) {
               pin1++;
           }
           next[pin2] = pin1;
       }
       return next;
   }

   public static int charToInt(char c) {
       if (c >= 'a' && c <= 'z') {
           return c - 'a' + 1;
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