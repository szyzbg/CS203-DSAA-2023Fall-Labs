
import java.io.*;
import java.util.*;



public class Question5 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int m = in.nextInt();
       int k = in.nextInt();
       int[] a = new int[m];
       int[] n = new int[m - k + 1];
       for (int i = 0; i < m; i++) {
           a[i] = in.nextInt();
       }
       for (int i = 0; i < m - k + 1; i++) {
           n[i] = in.nextInt();
       }
       SkipList window = new SkipList();
       for (int i = 0; i < k; i++) {
           window.add(a[i]);
       }
       out.print(window.searchByIndex(n[0]));
       out.print("\n");
       for (int i = 0; i < m - k; i++) {
           window.remove(a[i]);
           window.add(a[i + k]);
           out.println(window.searchByIndex(n[i + 1]));
       }
       out.close();
   }
}

class SkipList {
   private static class skipNode {
       skipNode[] next;
       skipNode[] prev;
       int[] span;
       long val;
       skipNode(long val,int level) {
           this.val = val;
           this.next = new skipNode[level];
           this.prev = new skipNode[level];
           this.span = new int[level];
           for (int i = 0; i < level; i++) {
               span[i] = 1;
           }
       }
   }
   private final int MAX_LEVEL = 32;
   Random random = new Random();
   private int getRandomLevel() {
       int level = 0;
       for (int i = 0; i < MAX_LEVEL; i++) {
           if (random.nextBoolean()) level++;
       }
       return level;
   }
   skipNode Head = new skipNode(Integer.MIN_VALUE * 4L, MAX_LEVEL);
   skipNode Tail = new skipNode(Integer.MAX_VALUE * 4L, MAX_LEVEL);
   SkipList() {
       for (int i = 0; i < MAX_LEVEL; i++) {
           Head.next[i] = Tail;
           Tail.prev[i] = Head;
           Head.span[i] = 1;
       }
   }
   public void add(long val) {
       skipNode iter = Head;
       skipNode newNode = new skipNode(val,getRandomLevel());
       for (int i = iter.next.length - 1; i >= 0; ) {
           while (iter.next[i].val <= val) {
               iter = iter.next[i];
           }
           if (i >= newNode.prev.length) iter.span[i]++;
           if (newNode.next.length > i) {
               newNode.next[i] = iter.next[i];
               iter.next[i] = newNode;
               newNode.prev[i] = iter;
               newNode.next[i].prev[i] = newNode;
           }
           i--;
       }
       int total = 1;
       iter = newNode.prev[0];
       for (int i = 0; i < newNode.next.length; ) {
           while (iter.next.length == i + 1) {
               total += iter.prev[i].span[i];
               iter = iter.prev[i];
           }
           i++;
           if (i < newNode.next.length) {
               newNode.span[i] = iter.span[i] - total + 1;
               iter.span[i] = total;
           }
       }
   }

   public boolean remove(long val) {
       skipNode iter = Head;
       for (int i = iter.next.length - 1; i >= 0;i-- ) {
           while (iter.next[i].val <= val) {
               iter = iter.next[i];
           }
           if (iter.val != val) {
               iter.span[i]--;
           }
           if (iter.val == val) {
               for (int j = iter.next.length - 1; j >= 0; j--) {
                   iter.next[j].prev[j] = iter.prev[j];
                   iter.prev[j].next[j] = iter.next[j];
               }
               for (int j = 0; j < iter.next.length; j++) {
                   iter.prev[j].span[j] = iter.prev[j].span[j] + iter.span[j] - 1;
               }
               return true;
           }
       }
       return false;
   }
   public long searchByIndex(int index) {
       int nowIndex = 0;
       skipNode iter = Head;
       for (int i = MAX_LEVEL - 1; i >= 0; i--) {
           while (iter.span[i] + nowIndex <= index) {
               nowIndex += iter.span[i];
               iter = iter.next[i];
               if (nowIndex == index) return iter.val;
           }
       }
       return -1;
   }
   public long searchClosest(long target) {
       skipNode iter = Head;
       for (int i = iter.next.length - 1; i >= 0; i--) {
           while (iter.next[i].val <= target) {
               iter = iter.next[i];
           }
       }
       if (  target - iter.val <=   iter.next[0].val - target) {
           return iter.val;
       }
       else return iter.next[0].val;
   }
   public boolean isEmpty() {
       return Head.next[0] == Tail;
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