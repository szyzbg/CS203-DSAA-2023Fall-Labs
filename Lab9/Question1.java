import java.io.*;
import java.util.*;

public class Question1 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int n = in.nextInt();
       int m = in.nextInt();
       ArrayList<Integer>[] graph = new ArrayList[n + 1];
       ArrayList<Long>[] weights = new ArrayList[n + 1];
       for (int i = 1; i < graph.length; i++) {
           graph[i] = new ArrayList<>();
           weights[i]  = new ArrayList<>();
       }
       for (int i = 0; i < m; i++) {
           int a = in.nextInt();
           int b = in.nextInt();
           long w = in.nextLong();
           graph[a].add(b);
           weights[a].add(w);
       }

       long[] dist = new long[n + 1];
       for (int i = 2; i < n + 1; i++) {
           dist[i] = Long.MAX_VALUE - 1;
       }
       dist[0] = Long.MAX_VALUE;
       int iter = 1;
       MinHeap list = new MinHeap(dist,n);
       for (int i = 1; i < n + 1; i++) {
           list.add(i);
       }
       while (!list.isEmpty()&&iter != 0) {
           if (iter == n) {
               break;
           }
           for (int i = 0; i < graph[iter].size(); i++) {
               if (weights[iter].get(i) + dist[iter] < dist[graph[iter].get(i)]) {
                   dist[graph[iter].get(i)] = weights[iter].get(i) + dist[iter];
                   list.reBalance(graph[iter].get(i));
               }
           }
           list.removeMin();
           iter = list.getMin();

       }
       out.println((dist[n]!=Long.MAX_VALUE - 1&&dist[n] >= 0)?dist[n]:-1);
       out.close();
   }
}

class MinHeap {
   public MinHeap(long[] dist,int n) {
       values.add(-1);
       this.dist = dist;
       indexes = new int[n + 1];
   }
   private ArrayList<Integer> values = new ArrayList<>();
   private long[] dist;
   private int[] indexes;
   public boolean isEmpty() {
       return values.size() == 1;
   }
   public void add(int val) {
       values.add(val);
       indexes[val] = values.size() - 1;
       int iterIndex = values.size() - 1;
       while (iterIndex > 1) {
           if (dist[values.get(iterIndex / 2)] > dist[values.get(iterIndex)]) {
               int tmp = values.get(iterIndex / 2);
               values.set(iterIndex / 2,values.get(iterIndex));
               indexes[values.get(iterIndex)] = iterIndex / 2;
               values.set(iterIndex,tmp);
               indexes[tmp] = iterIndex;
               iterIndex = iterIndex / 2;
           }
           else break;
       }
   }
   public int removeMin() {
       int min = values.get(1);
       values.set(1,values.get(values.size() - 1));
       indexes[values.get(values.size() - 1)] = 1;
       values.remove(values.size() - 1);
       int iter = 1;
       while (iter * 2 < values.size()) {
           if (values.size() == iter * 2 + 1) {
               if (dist[values.get(iter * 2)] < dist[values.get(iter)]) {
                   int tmp = values.get(iter*2);
                   values.set(iter*2,values.get(iter));
                   indexes[values.get(iter)] = iter * 2;
                   values.set(iter,tmp);
                   indexes[tmp] = iter;
                   iter = iter * 2;
               }
               else break;
           }
           else {
               if (dist[values.get(iter*2)]<=dist[values.get(iter*2+1)]) {
                   if (dist[values.get(iter)] > dist[values.get(iter*2)]) {
                       int tmp = values.get(iter*2);
                       values.set(iter*2,values.get(iter));
                       indexes[values.get(iter)] = iter * 2;
                       values.set(iter,tmp);
                       indexes[tmp] = iter;
                       iter = iter * 2;
                   }
                   else break;
               }
               else {
                   if (dist[values.get(iter)] > dist[values.get(iter*2+1)]) {
                       int tmp = values.get(iter*2+1);
                       values.set(iter*2+1,values.get(iter));
                       indexes[values.get(iter)] = iter * 2 + 1;
                       values.set(iter,tmp);
                       indexes[tmp] = iter;
                       iter = iter * 2 + 1;
                   }
                   else break;
               }
           }
       }
       return min;
   }
   public void reBalance(int val) {

       int iter = indexes[val];
       while (iter >= 1) {
           if (dist[values.get(iter)] < dist[values.get(iter / 2)]) {
               int tmp = values.get(iter);
               values.set(iter,values.get(iter / 2));
               indexes[values.get(iter / 2)] = iter;
               values.set(iter / 2,tmp);
               indexes[tmp] = iter / 2;
               iter = iter / 2;
           }
           else break;
       }
   }

   public int getMin() {
       return values.get(1);
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