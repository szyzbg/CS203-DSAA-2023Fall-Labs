import java.io.*;
import java.util.*;

public class Question5 {
    static int t = 0;
    static int p = 0;
    static int[] dfs1Result;
    static int[] dfs1Reverse;
    static int[] sccNum;
    static ArrayList<Integer>[] scc;
    static PriorityQueue<Integer> list1 = new PriorityQueue<>();
    static PriorityQueue<Integer> list2 = new PriorityQueue<>();
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        int S = in.nextInt();
        dfs1Result = new int[n];
        dfs1Reverse = new int[n + 1];
        ArrayList<Integer>[] graphPos = new ArrayList[n + 1];
        ArrayList<Integer>[] graphNeg = new ArrayList[n + 1];
        sccNum = new int[n + 1];
        scc = new ArrayList[n];
        for (int i = 1; i < graphPos.length; i++) {
            graphPos[i] = new ArrayList<>();
            graphNeg[i] = new ArrayList<>();
            scc[i - 1] = new ArrayList<>();
        }
        for (int i = 0; i < m; i++) {
            int a = in.nextInt();
            int b = in.nextInt();
            graphPos[a].add(b);
            graphNeg[b].add(a);
        }
        for (int i = 1; i < n + 1; i++) {
            list1.add(i);
        }
        for (int i = 0; i < n; i++) {
            list2.add(-1*i);
        }
        boolean[] isVisited = new boolean[n + 1];
        while (!list1.isEmpty()) {
            dfs1(list1.peek(),graphPos,isVisited);
        }
        isVisited = new boolean[n + 1];
        while (!list2.isEmpty()) {
            dfs2(dfs1Result[-1 * list2.peek()],graphNeg,isVisited);
            p++;
        }



        //检测入度为0
        boolean[] hasOtherIn = new boolean[p];
        int otherInCount = 0;
        for (int i = 0; i < scc.length; i++) {
            for (int j = 0; j < scc[i].size(); j++) {
                for (int k = 0; k < graphNeg[scc[i].get(j)].size(); k++) {
                    if (sccNum[graphNeg[scc[i].get(j)].get(k)] != sccNum[scc[i].get(j)]&&!hasOtherIn[i]) {
                        hasOtherIn[i] = true;
                        otherInCount++;
                        break;
                    }
                }
            }
        }
        out.println((hasOtherIn[sccNum[S]]) ? p - otherInCount : p - otherInCount - 1);
        out.close();
    }
    public static void dfs1(int start,ArrayList<Integer>[] graphPos,boolean[] isVisited) {
        isVisited[start] = true;
        for (int i = 0; i < graphPos[start].size(); i++) {
            if (!isVisited[graphPos[start].get(i)]) {
                dfs1(graphPos[start].get(i),graphPos,isVisited);
            }
        }
        list1.remove(start);
        dfs1Reverse[start] = t;
        dfs1Result[t++] = start;
    }
    public static void dfs2(int start,ArrayList<Integer>[] graphNeg,boolean[] isVisited) {
        isVisited[start] = true;
        for (int i = 0; i < graphNeg[start].size(); i++) {
            if (!isVisited[graphNeg[start].get(i)]) {
                dfs2(graphNeg[start].get(i),graphNeg,isVisited);
            }
        }
        list2.remove(dfs1Reverse[start] * (-1));
        sccNum[start] = p;
        scc[p].add(start);
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