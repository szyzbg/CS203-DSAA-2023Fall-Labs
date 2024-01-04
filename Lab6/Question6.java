import java.io.*;
import java.util.*;

public class Question6 {
   static ArrayList<Integer>[] tree;
   static int[] p;
   static int[] e;
   static boolean[] isLeaf;

   public static void main(String[] args) {
       QReader in = new QReader();
       QWriter out = new QWriter();
       int n = in.nextInt();
       tree = new ArrayList[n + 1];
       for (int i = 1; i < tree.length; i++) {
           tree[i] = new ArrayList<>();
       }
       p = new int[n + 1];
       isLeaf = new boolean[n + 1];
       for (int i = 0; i < n - 1; i++) {
           int u = in.nextInt();
           int v = in.nextInt();
           tree[u].add(v);
           tree[v].add(u);
       }
       for (int i = 1; i < n + 1; i++) {
           p[i] = in.nextInt();
       }
       int maxRootIndex = 1;
       for (int i = 1; i < p.length; i++) {
           if (p[i] > p[maxRootIndex]) maxRootIndex = i;
       }//maxRootIndex是选取的根
       e = p.clone();
       int l = buildE(maxRootIndex,-1,maxRootIndex);
       long ans = 0;
       if (tree[maxRootIndex].size() > 1) {//判断根有几个子
           int maxIndex2 = 0;
           for (int i = 0; i < e.length; i++) {
               if (!isLeaf[i]) e[i] = 0;
           }
           int tmp = e[l];
           int[] eBackup = e.clone();
           eBackup[l] = 0;
           for (int i = 0; i < tree[maxRootIndex].size(); i++) {
               if (eBackup[maxIndex2] < eBackup[buildE(tree[maxRootIndex].get(i),maxRootIndex,maxRootIndex)]) maxIndex2 = buildE(tree[maxRootIndex].get(i),maxRootIndex,maxRootIndex);
           }
           eBackup[l] = tmp;//找e中前两大的数
           if (eBackup[maxIndex2] < p[maxRootIndex]) eBackup[maxIndex2] = p[maxRootIndex];

           for (int i = 1; i < n + 1; i++) {
               if (isLeaf[i]) ans += eBackup[i];
           }
       }
       else {//根只有一个子
           ans += e[maxRootIndex];
           for (int i = 0; i < e.length; i++) {
               if (isLeaf[i]) ans += e[i];
           }
       }

       out.println(ans);
       out.close();
   }
   public static int buildE(int index,int before,int maxRootIndex) {//迭代函数，结果为index节点的所有子树的最大p下标
       if (index != maxRootIndex&&tree[index].size() == 1) {
           isLeaf[index] = true;
           return index;
       }
       else {
           int a;
           if (tree[index].get(0) != before) {//before用来排除父节点
               a = buildE(tree[index].get(0),index,maxRootIndex);
           }
           else {
               a = buildE(tree[index].get(1),index,maxRootIndex);
           }
           for (int i = 0; i < tree[index].size(); i++) {
               if (tree[index].get(i) != before) {
                   int bEi = buildE(tree[index].get(i),index,maxRootIndex);
                   a = (e[a] < e[bEi]) ? bEi : a;
               }
           }
           if (e[a] < p[index]) {
               e[a] = p[index];
               return a;
           }
           else {
               return a;
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