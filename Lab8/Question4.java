import java.io.*;
import java.util.*;

public class Question4 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int T = in.nextInt();
       for (int i = 0; i < T; i++) {
           int n = in.nextInt();
           int m = in.nextInt();
           LinkedList<Integer>[] children = new LinkedList[n + 1];
           for (int j = 1; j < n + 1; j++) {
               children[j] = new LinkedList<>();
           }
           boolean[] isChild = new boolean[n + 1];
           for (int j = 0; j < n - 1; j++) {
               int child = in.nextInt();
               int father = in.nextInt();
               children[father].add(child);
               isChild[child] = true;
           }
           int root = 0;
           for (int j = 1; j < n + 1; j++) {
               if (!isChild[j]) {
                   root = j;
                   break;
               }
           }
           LinkedList<Quest>[] Query = new LinkedList[n + 1];
           for (int j = 1; j < n + 1; j++) {
               Query[j] = new LinkedList<>();
           }
           Quest[] sequence = new Quest[m];
           for (int j = 0; j < m; j++) {
               int qChild = in.nextInt();
               int qAnc = in.nextInt();
               Query[qChild].add(new Quest(qAnc));
               sequence[j] = Query[qChild].getLast();
           }
           boolean[] flag = new boolean[n + 1];
           dfs(-1,root,children,Query,flag);
           for (int j = 0; j < m; j++) {
               out.print(sequence[j].res ? "Yes\n" : "No\n");
           }
       }
       out.close();
   }
   static void dfs(int father,int now,LinkedList<Integer>[] children,LinkedList<Quest>[] Query,boolean[] flag) {
       flag[now] = true;
       int s = Query[now].size();
       for (int i = 0; i < s; i++) {
           if (flag[Query[now].getFirst().qAnc]) {
               Query[now].getFirst().res = true;
           }
           Query[now].removeFirst();
       }
       int s2 = children[now].size();
       for (int i = 0; i < s2; i++) {
           if (children[now].getFirst() != father) {
               dfs(now,children[now].getFirst(),children,Query,flag);
               children[now].removeFirst();
           }
       }
       flag[now] = false;
   }
}
class Quest {

   int qAnc;
   boolean res;
   Quest(int qAnc) {
       this.qAnc = qAnc;
       res = false;
   }
}

class QReader {
   private final BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
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
   private final BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(System.out));

   public void print(Object object) {
       try {
           writer.write(object.toString());
       } catch (IOException e) {
       }
   }

   public void println(Object object) {
       try {
           writer.write(object.toString());
           writer.write("\n");
       } catch (IOException e) {
       }
   }

   @Override
   public void close() {
       try {
           writer.close();
       } catch (IOException e) {
       }
   }
}