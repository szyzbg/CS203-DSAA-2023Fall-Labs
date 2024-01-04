import java.io.*;
import java.util.*;

public class Question2 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int T = in.nextInt();
       for (int i = 0; i < T; i++) {
           int n = in.nextInt();
           int m = in.nextInt();
           int s = in.nextInt();
           ArrayList<Integer>[] connections = new ArrayList[n + 1];
           for (int j = 0; j < connections.length; j++) {
               connections[j] = new ArrayList<>();
           }
           for (int j = 0; j < m; j++) {
               int a = in.nextInt();
               int b = in.nextInt();
               connections[a].add(b);
               connections[b].add(a);
           }
           boolean[] isVisited = new boolean[n + 1];
           int[] res = new int[n + 1];
           Deque<Integer> bfsQueue = new LinkedList<>();
           //bfs
           bfsQueue.addFirst(s);
           isVisited[s] = true;
           while (!bfsQueue.isEmpty()) {
               int nowIter = bfsQueue.pollLast();
               for (int j = 0; j < connections[nowIter].size(); j++) {
                   if (!isVisited[connections[nowIter].get(j)]) {
                       res[connections[nowIter].get(j)] = res[nowIter] + 1;
                       isVisited[connections[nowIter].get(j)] = true;
                       bfsQueue.addFirst(connections[nowIter].get(j));
                   }
               }
           }
           for (int j = 1; j < n + 1; j++) {
               if (j != s&&res[j] == 0) res[j] = -1;
           }
           for (int j = 1; j < n + 1; j++) {
               out.print(res[j]);
               out.print(" ");
           }
           out.print("\n");
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