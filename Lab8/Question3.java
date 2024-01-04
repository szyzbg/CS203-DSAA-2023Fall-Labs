import java.io.*;
import java.util.*;

public class Question3 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int T = in.nextInt();
       for (int i = 0; i < T; i++) {
           int n = in.nextInt();
           int m = in.nextInt();
           LinkedList<Integer>[] connections = new LinkedList[n + 1];
           for (int j = 1; j < n + 1; j++) {
               connections[j] = new LinkedList<>();
           }
           for (int j = 0; j < m; j++) {
               int u = in.nextInt();
               int v = in.nextInt();
               connections[u].addLast(v);
               connections[v].addLast(u);
           }
           boolean[] isVisited = new boolean[n + 1];
           boolean[] label = new boolean[n + 1];
           int labelTrueNum = 0;
           Deque<QueueClass> bfsQueue = new LinkedList<>();
           //bfs
           bfsQueue.addFirst(new QueueClass(1,true));
//            isVisited[1] = true;
           while (!bfsQueue.isEmpty()) {
               QueueClass last = bfsQueue.pollLast();
               if (!isVisited[last.index]) {
                   label[last.index] = !last.fatherLabel;
                   if (label[last.index]) {
                       labelTrueNum++;
                   }
                   while (!connections[last.index].isEmpty()) {
                       bfsQueue.addFirst(new QueueClass(connections[last.index].pollFirst(),label[last.index]));
                   }
               }
               isVisited[last.index] = true;
           }
           int num = 0;
           if (labelTrueNum > n / 2) {
               for (int j = 1; j < n + 1; j++) {
                   if (!label[j]) {
                       num++;
                   }
               }
               out.println(num);
               for (int j = 1; j < n + 1; j++) {
                   if (!label[j]) {
                       out.print(j);
                       out.print(" ");
                   }
               }
           }
           else {
               for (int j = 1; j < n + 1; j++) {
                   if (label[j]) {
                       num++;
                   }
               }
               out.println(num);
               for (int j = 1; j < n + 1; j++) {
                   if (label[j]) {
                       out.print(j);
                       out.print(" ");
                   }
               }
           }
           out.print("\n");
       }
       out.close();
   }
}

class QueueClass {
   int index;
   boolean fatherLabel;
   QueueClass(int index,boolean fatherLabel) {
       this.index = index;
       this.fatherLabel = fatherLabel;
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