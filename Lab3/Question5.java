import java.io.*;
import java.util.*;
 
class Question5 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        long[] d = new long[n];
        for (int i = 0; i < n; i++) {
            d[i] = in.nextLong();
        }
        Node[] nodes = new Node[n];
        for (int i = 0; i < n; i++) {
            nodes[i] = new Node(d[i],null,null);
        }
        Node[] nodes1 = nodes.clone();
        Node[] tmp = new Node[n];
        mergeSort(nodes1,0,nodes1.length - 1,tmp);
        for (int i = 0; i < n; i++) {
            if (i > 0) {
                nodes1[i].before = nodes1[i - 1];
            }
            if (i < n - 1) {
                nodes1[i].next = nodes1[i + 1];
            }
        }
        for (int i = 0; i < n - 1; i++) {
            if (nodes[i].before!=null&&nodes[i].next!=null) {
                //long t = Math.min(Math.abs(nodes[i].item.item - nodes[i].next.item.item),Math.abs(nodes[i].item.item-nodes[i].before.item.item));
                out.print(Math.min(Math.abs(nodes[i].item - nodes[i].next.item),Math.abs(nodes[i].item-nodes[i].before.item)));
                out.print(" ");
                nodes[i].before.next = nodes[i].next;
                nodes[i].next.before = nodes[i].before;
            }
            else if (nodes[i].before == null) {
                out.print(Math.abs(nodes[i].item - nodes[i].next.item));
                out.print(" ");
                nodes[i].next.before = null;
            }
            else {
                out.print(Math.abs(nodes[i].item - nodes[i].before.item));
                out.print(" ");
                nodes[i].before.next = null;
            }
        }
 
 
 
        out.close();
    }
 
    static void merge(Node[] a,int left,int mid,int right,Node[] tmp) {
 
        int x = left, y = mid + 1;
        for (int j = left; j <= right; j++) {
            if (x <= mid && (y > right || a[x].item <= a[y].item)) {
                tmp[j] = a[x];
                x++;
            } else {
                tmp[j] = a[y];
                y++;
            }
        }
        if (right + 1 - left >= 0) System.arraycopy(tmp, left, a, left, right + 1 - left);
    }
 
    static void mergeSort(Node[] a, int left,int right,Node[] tmp) {
        if (right - left >= 1) {
            int p = (right + left) / 2;
 
            mergeSort(a, left,p,tmp);
            mergeSort(a,p + 1,right,tmp);
            merge(a,left,p,right,tmp);
 
        }
    }
}
 
 
class Node {
    long item;
    Node before;
    Node next;
    Node(long item,Node before,Node next) {
        this.next = next;
        this.before = before;
        this.item = item;
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