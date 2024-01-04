import java.io.*;
import java.util.*;

public class Question2 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int n = in.nextInt();
       int num = in.nextInt();
       ArrayList<Integer>[] connections = new ArrayList[n + 1];
       ArrayList<Integer>[] weights = new ArrayList[n + 1];
       for (int i = 1; i < n + 1; i++) {
           connections[i] = new ArrayList<>();
           weights[i] = new ArrayList<>();
       }
       for (int i = 0; i < n - 1; i++) {
           int p1 = in.nextInt();
           int p2 = in.nextInt();
           int w = in.nextInt();
           connections[p1].add(p2);
           connections[p2].add(p1);
           weights[p1].add(w);
           weights[p2].add(w);
       }
       int[] finalPath = new int[n + 1];
       buildFinalPath(finalPath,connections,weights,-1,1);
       int count = 0;
       for (int i = 2; i < finalPath.length; i++) {
           if (connections[i].size() == 1&&finalPath[i] == num) count++;
       }
       out.println(count);
       out.close();
   }
   public static void buildFinalPath(int[] path,ArrayList<Integer>[] connections,ArrayList<Integer>[] weights,int except,int now) {
       for (int i = 0; i < connections[now].size(); i++) {
           if (connections[now].get(i) != except) {
               path[connections[now].get(i)] = path[now] + weights[now].get(i);
           }
       }
       for (int i = 0; i < connections[now].size(); i++) {
           if (connections[now].get(i) != except) {
               buildFinalPath(path,connections,weights,now,connections[now].get(i));
           }
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