import java.io.*;
import java.util.*;

public class Question5 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       String str1 = in.next();
       String str2 = in.next();
       int left = 0;
       int right = Math.min(str1.length(),str2.length());
       int mid = (left + right) / 2;
       while (right >= left) {
           mid = (left + right) / 2;
           if (checkSubLength(str1,str2,mid)) {
               left = mid + 1;
           }
           else right = mid - 1;
       }
       out.println(right);
       out.close();
   }
   public static boolean checkSubLength(String s1,String s2,int length) {
       long[] Hash1 = new long[s1.length() - length + 1];
       long[] Hash2 = new long[s2.length() - length + 1];
       for (int i = 0; i < length; i++) {
           Hash1[0] = Hash1[0] * 139 + s1.charAt(i);
           Hash2[0] = Hash2[0] * 139 + s2.charAt(i);
       }
       long powerResult = 1;
       for (int i = 0; i < length - 1; i++) {
           powerResult *= 139;
       }
       for (int i = 1; i < Hash1.length; i++) {
           Hash1[i] = (Hash1[i - 1] - powerResult * s1.charAt(i - 1)) * 139 + s1.charAt(i + length - 1);
       }
       for (int i = 1; i < Hash2.length; i++) {
           Hash2[i] = (Hash2[i - 1] - powerResult * s2.charAt(i - 1)) * 139 + s2.charAt(i + length - 1);
       }
       mySort(Hash1);
       mySort(Hash2);
       int pin1 = 0;
       int pin2 = 0;
       while (pin1 < Hash1.length||pin2 < Hash2.length) {
           if (pin1 < Hash1.length&&(pin2 >= Hash2.length||Hash1[pin1] < Hash2[pin2])) {
               pin1++;
           }
           else if (pin1 < Hash1.length&&pin2 < Hash2.length&&Hash1[pin1] == Hash2[pin2]) return true;
           else {
               pin2++;
           }
       }
       return false;
   }
   static void merge(long[] a,int left,int mid,int right,long[] tmp) {

       int x = left, y = mid + 1;
       for (int j = left; j <= right; j++) {
           if (x <= mid && (y > right || a[x] <= a[y])) {
               tmp[j] = a[x];
               x++;
           } else {
               tmp[j] = a[y];
               y++;
           }
       }
       if (right + 1 - left >= 0) System.arraycopy(tmp, left, a, left, right + 1 - left);
   }

   static void mergeSort(long[] a, int left,int right,long[] tmp) {
       if (right - left >= 1) {
           int p = (right + left) / 2;

           mergeSort(a, left,p,tmp);
           mergeSort(a,p + 1,right,tmp);
           merge(a,left,p,right,tmp);

       }
   }
   static void mySort(long[] a) {
       long[] tmp = new long[a.length];
       mergeSort(a,0,a.length - 1,tmp);
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