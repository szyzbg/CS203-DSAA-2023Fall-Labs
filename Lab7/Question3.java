import java.io.*;
import java.util.*;

public class Question3 {
   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int N = in.nextInt();
       int M = in.nextInt();
       int K = in.nextInt();
       long[] a = new long[N];
       long[] b = new long[M];
       ArrayList<treeNodeContent> k = new ArrayList<>(K);
       for (int i = 0; i < N; i++) {
           a[i] = in.nextLong();
       }
       for (int i = 0; i < M; i++) {
           b[i] = in.nextLong();
       }
       Arrays.sort(a);
       int[] father = new int[M];
       ArrayList<treeNodeContent> HeapNodes = new ArrayList<>(M);
       Deque<Integer> fatherQueue = new LinkedList<>();
       fatherQueue.addFirst(0);
       for (int i = 1,cnt = 0; i < M; i++) {
           cnt++;
           father[i] = fatherQueue.peekLast();
           fatherQueue.addFirst(i);
           if (cnt == 2) {
               cnt = 0;
               fatherQueue.pollLast();
           }
       }
       ArrayList<Integer>[] children = new ArrayList[M];
       for (int i = 0; i < M; i++) {
           children[i] = new ArrayList<>();
       }
       for (int i = 1; i < M; i++) {
           children[father[i]].add(i);
       }
       ArrayList<treeNodeContent> tree = new ArrayList<>(M);
       for (int i = 0; i < M; i++) {
           myToolBox.insertionInHeap(tree,new treeNodeContent(0,i),father,a,b);
       }
       k.add(tree.get(0));
       for (int i = 1; i < K; i++) {
           k.add(myToolBox.changeAndGet(tree,new treeNodeContent(k.get(k.size() - 1).pinA + 1,k.get(k.size() - 1).pinB),children,a,b));
       }
       long[] res = new long[K];
       for (int i = 0; i < K; i++) {
           res[i] = a[k.get(i).pinA] * b[k.get(i).pinB];
       }
       for (int i = 0; i < K; i++) {
           out.print(res[i]);
           out.print(" ");
       }
       out.println("");
       out.close();
   }
}

class myToolBox {
   static void insertionInHeap(ArrayList<treeNodeContent> tree,treeNodeContent content,int[] father,long[] a,long[] b) {
       tree.add(content);
       for (int i = tree.size() - 1; i > 0; ) {
           if (a[tree.get(i).pinA]*b[tree.get(i).pinB] < a[tree.get(father[i]).pinA]*b[tree.get(father[i]).pinB]) {
               swap(tree.get(i),tree.get(father[i]));
               i = father[i];
           }
           else break;
       }
   }
   static treeNodeContent changeAndGet(ArrayList<treeNodeContent> tree,treeNodeContent content,ArrayList<Integer>[] children,long[] a,long[] b) {
       if (content.pinA < a.length) {
           tree.set(0,content);
           for (int i = 0; !children[i].isEmpty(); ) {
               if (children[i].size() == 1) {
                   if (a[tree.get(i).pinA]*b[tree.get(i).pinB] > a[tree.get(children[i].get(0)).pinA]*b[tree.get(children[i].get(0)).pinB]) {
                       swap(tree.get(i),tree.get(children[i].get(0)));
                       i = children[i].get(0);
                   }
                   else break;
               }
               else {
                   if (a[tree.get(children[i].get(0)).pinA]*b[tree.get(children[i].get(0)).pinB] < a[tree.get(children[i].get(1)).pinA]*b[tree.get(children[i].get(1)).pinB]) {
                       if (a[tree.get(i).pinA]*b[tree.get(i).pinB] > a[tree.get(children[i].get(0)).pinA]*b[tree.get(children[i].get(0)).pinB]) {
                           swap(tree.get(i),tree.get(children[i].get(0)));
                           i = children[i].get(0);
                       }
                       else break;
                   }
                   else {
                       if (a[tree.get(i).pinA]*b[tree.get(i).pinB] > a[tree.get(children[i].get(1)).pinA]*b[tree.get(children[i].get(1)).pinB]) {
                           swap(tree.get(i),tree.get(children[i].get(1)));
                           i = children[i].get(1);
                       }
                       else break;
                   }
               }
           }
       }
       else {
           treeNodeContent tmp = tree.get(tree.size() - 1);
           tree.remove(tree.size() - 1);
           children[(tree.size() + 1) / 2 - 1].remove(children[(tree.size() + 1) / 2 - 1].size() - 1);
           return changeAndGet(tree,tmp,children,a,b);
       }

       return tree.get(0);
   }
   static void swap(treeNodeContent a,treeNodeContent b) {
       int tmp1 = a.pinA;
       int tmp2 = a.pinB;
       a.pinA = b.pinA;
       a.pinB = b.pinB;
       b.pinA = tmp1;
       b.pinB = tmp2;
   }
}
class treeNodeContent {
   int pinA;
   int pinB;
   treeNodeContent(int pinA,int pinB) {
       this.pinA = pinA;
       this.pinB = pinB;
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