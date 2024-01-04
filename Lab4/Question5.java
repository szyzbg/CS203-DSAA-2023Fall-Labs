import java.io.*;
import java.util.*;

public class Question5 {
   public static void main(String[] args) {

       QReader in = new QReader();
       QWriter out = new QWriter();
       int m = in.nextInt();
       ArrayList<Integer> arr = new ArrayList<>();
       for (int i = 0; i < 2000001; i++) {
           int x = in.nextInt();
           if (x != -1) {
               arr.add(x);
           }
           else break;
       }
       ArrayList<Integer> ansArray = findMaximumInSubArrays(arr,m);
       int ans = ansArray.get(0);
       for (int i = 1; i < ansArray.size(); i++) {
           ans ^= ansArray.get(i);
       }
       out.println(ans);
       out.close();
   }
   public static ArrayList<Integer> findMaximumInSubArrays(ArrayList<Integer> TotalArray,int m) {
       ArrayList<Integer> ans = new ArrayList<>();
       Node head = new Node(-1,null,null);
       Node tail = new Node(-1,head,null);
       head.next = tail;
       for (int i = 0; i < TotalArray.size(); i++) {
           if (head.next != tail&&head.next.item <= i - m) {
               head.next =  head.next.next;
               head.next.before = head;
           }
           while (head.next != tail&&TotalArray.get(tail.before.item) < TotalArray.get(i)) {
               tail.before = tail.before.before;
               tail.before.next = tail;
           }
           Node tmp = new Node(i,tail.before,tail);
           tail.before.next = tmp;
           tail.before = tmp;
           if (i >= m - 1) {
               ans.add(TotalArray.get(head.next.item));
           }
       }
       return ans;
   }
}
class Node {
   int item;
   Node before;
   Node next;
   Node(int item,Node before,Node next) {
       this.before = before;
       this.item = item;
       this.next = next;
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