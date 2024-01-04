import java.io.*;
import java.util.*;

public class Question5 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        ArrayList<Integer>[] connections = new ArrayList[n + 1];
        for (int i = 1; i < n + 1; i++) {
            connections[i] = new ArrayList<>();
        }
        boolean[] isVisited = new boolean[n + 1];
        boolean[] hasGiant = new boolean[n + 1];
        int[] depth = new int[n + 1];
        for (int i = 1; i < n; i++) {
            int u = in.nextInt();
            int v = in.nextInt();
            connections[u].add(v);
            connections[v].add(u);
        }
        isVisited[1] = true;
        int m = in.nextInt();
        for (int i = 0; i < m; i++) {
            hasGiant[in.nextInt()] = true;
        }
        long ans = 0;
        for (int i = 0; i < connections[1].size(); i++) {
            ans = Math.max(ans,bfs(hasGiant,ReturnLevel(connections,connections[1].get(i),1,1)));
        }
        out.println(ans);
        out.close();
    }
    static int bfs(boolean[] hasGiant,ArrayList<Integer>[] levelList) {
        ArrayList<Integer> giantsArray = new ArrayList<>();
        int count = 1;
        while (!levelList[++count].isEmpty()) {
            for (int i = 0; i < levelList[count].size(); i++) {
                if (hasGiant[levelList[count].get(i)]) giantsArray.add(count);
            }
        }
        if (giantsArray.isEmpty()) return 0;
        if (giantsArray.size() > 1) {
            for (int i = 0; i < giantsArray.size() - 1; i++) {
                if (giantsArray.get(i) >= giantsArray.get(i + 1)) giantsArray.set(i + 1,giantsArray.get(i) + 1);
            }
        }
        return giantsArray.get(giantsArray.size() - 1);
    }
    static void buildLevel(ArrayList<Integer>[] levelList,ArrayList<Integer>[] connections,int nowNodeIndex,int nowLevel,int except) {
        levelList[nowLevel].add(nowNodeIndex);
        if (nowNodeIndex == 1 || connections[nowNodeIndex].size() != 1) {
            for (int i = 0; i < connections[nowNodeIndex].size(); i++) {
                if (connections[nowNodeIndex].get(i) != except) {
                    buildLevel(levelList,connections,connections[nowNodeIndex].get(i),nowLevel + 1,nowNodeIndex);
                }
            }
        }
    }
    static ArrayList<Integer>[] ReturnLevel(ArrayList<Integer>[] connections,int nowNodeIndex,int nowLevel,int except) {
        ArrayList<Integer>[] levelList = new ArrayList[400001 - connections[1].size()];
        for (int i = 0; i < levelList.length; i++) {
            levelList[i] = new ArrayList<>();
        }
        buildLevel(levelList,connections,nowNodeIndex,nowLevel,except);
        return levelList;
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