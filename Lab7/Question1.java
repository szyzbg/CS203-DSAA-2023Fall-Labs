import java.io.*;
import java.util.*;

public class Question1 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int T = in.nextInt();
       for (int i = 0; i < T; i++) {
           int n = in.nextInt();
           int[] val = new int[n + 1];
           for (int j = 1; j < n + 1; j++) {
               val[j] = in.nextInt();
           }
           ArrayList<Integer>[] children = new ArrayList[n + 1];
           for (int j = 1; j < n + 1; j++) {
               children[j] = new ArrayList<>();
           }
           for (int j = 1; j < n; j++) {
               children[in.nextInt()].add(in.nextInt());
           }
           if (isHeap(val,children)) {
               out.println("Case #" + (i + 1) + ": YES");
           }
           else out.println("Case #" + (i + 1) + ": NO");
       }
       out.close();
   }
   static boolean isHeap(int[] val,ArrayList<Integer>[] children) {
       int[] depth = new int[children.length];
       boolean[] isChildren = new boolean[children.length];
       int root = -1;
       for (int i = 1; i < children.length; i++) {
           for (int j = 0; j < children[i].size(); j++) {
               isChildren[children[i].get(j)] = true;
           }
       }
       for (int i = 1; i < isChildren.length; i++) {
           if (!isChildren[i]) {
               root = i;
               break;
           }
       }
       int count = 0;
       for (int i = 1; i < children.length; i++) {
           if (children[i].size() == 1) count++;
           if (children[i].size() > 2||count > 1) return false;
       }
       //bfs
       int count2 = 1;
       int iter = root;
       Deque<Integer> bfsQueue = new LinkedList<>();
       bfsQueue.addFirst(root);
       while (!bfsQueue.isEmpty()&&children[bfsQueue.peekLast()].size() == 2) {
           count2 += 2;
           for (int i = 0; i < 2; i++) {
               bfsQueue.addFirst(children[bfsQueue.peekLast()].get(i));
           }
           bfsQueue.pollLast();
       }
       if (count2 != children.length - 1&&count2 != children.length - 2) return false;
       boolean isBigHeap = true;
       boolean isLittleHeap = true;
       for (int i = 1; i < children.length; i++) {
           for (int j = 0; j < children[i].size(); j++) {
               if (val[children[i].get(j)] > val[i]) {
                   isBigHeap = false;
                   break;
               }
           }
       }
       for (int i = 1; i < children.length; i++) {
           for (int j = 0; j < children[i].size(); j++) {
               if (val[children[i].get(j)] < val[i]) {
                   isLittleHeap = false;
                   break;
               }
           }
       }
       return isBigHeap||isLittleHeap;
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