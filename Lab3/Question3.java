import java.io.*;
import java.util.*;
 
class Question3 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int N = in.nextInt();
            int M = in.nextInt();
            Node[] nodes = new Node[N];
            Node[] ids = new Node[N];
            for (int j = 0; j < N; j++) {
                Node tmp = new Node(in.nextInt(),null,null);
                nodes[j] = tmp;
                ids[tmp.id] = tmp;
            }
            for (int j = 0; j < N; j++) {
                if (j < N - 1) {
                    nodes[j].next = nodes[j + 1];
                }
                if (j > 0) {
                    nodes[j].before = nodes[j - 1];
                }
            }
            Node head = new Node(-1,nodes[0],null);
            nodes[0].before = head;
            Node tail = new Node(-1,null,nodes[N - 1]);
            nodes[N - 1].next = tail;
 
            for (int j = 0; j < M; j++) {
                int x1 = in.nextInt();
                int y1 = in.nextInt();
                int x2 = in.nextInt();
                int y2 = in.nextInt();
                if (ids[y1].next == ids[x2]) {
                    Node tmp1 = ids[x1].before;
                    Node tmp2 = ids[y2].next;
                    ids[x2].before = tmp1;
                    ids[y1].next = tmp2;
                    ids[x1].before = ids[y2];
                    ids[y2].next = ids[x1];
                    ids[x2].before.next = ids[x2];
                    ids[y1].next.before = ids[y1];
                }
                else {
                    Node tmp1 = ids[x1].before;
                    Node tmp2 = ids[y1].next;
                    Node tmp3 = ids[x2].before;
                    Node tmp4 = ids[y2].next;
                    ids[x1].before = tmp3;
                    ids[y1].next = tmp4;
                    ids[x2].before = tmp1;
                    ids[y2].next = tmp2;
                    ids[x1].before.next = ids[x1];
                    ids[x2].before.next = ids[x2];
                    ids[y1].next.before = ids[y1];
                    ids[y2].next.before = ids[y2];
                }
            }
            Node iter = head.next;
            while (iter.next != null) {
                out.print(iter.id);
                out.print(" ");
                iter = iter.next;
            }
            out.print("\n");
        }
        out.close();
    }
}
class Node {
    int id;
    Node next;
    Node before;
    Node(int id,Node next,Node before) {
        this.id = id;
        this.next = next;
        this.before = before;
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