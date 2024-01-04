
import java.io.*;
import java.util.*;

public class Question6 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        SkipList pets = new SkipList();
        SkipList adopters = new SkipList();
        long ans = 0;
        for (int i = 0; i < n; i++) {
            if (in.nextInt() == 0) {
                if (adopters.isEmpty()) pets.add(in.nextLong());
                else {
                    long petValue = in.nextLong();
                    long closest = adopters.searchClosest(petValue);
                    ans += Math.abs(closest - petValue);
                    adopters.remove(closest);
                }
            }
            else {
                if (pets.isEmpty()) adopters.add(in.nextLong());
                else {
                    long adopterValue = in.nextLong();
                    long closest = pets.searchClosest(adopterValue);
                    ans += Math.abs(closest - adopterValue);
                    pets.remove(closest);
                }
            }
        }
        out.println(ans);
        out.close();
    }
}

class SkipList {
    private static class skipNode {
        skipNode[] next;
        skipNode[] prev;
        long val;
        skipNode(long val,int level) {
            this.val = val;
            this.next = new skipNode[level];
            this.prev = new skipNode[level];
        }
    }
    private final int MAX_LEVEL = 32;
    Random random = new Random();
    private int getRandomLevel() {
        int level = 0;
        for (int i = 0; i < MAX_LEVEL; i++) {
            if (random.nextBoolean()) level++;
        }
        return level;
    }
    skipNode Head = new skipNode(Integer.MIN_VALUE * 4L, MAX_LEVEL);
    skipNode Tail = new skipNode(Integer.MAX_VALUE * 4L, MAX_LEVEL);
    SkipList() {
        for (int i = 0; i < MAX_LEVEL; i++) {
            Head.next[i] = Tail;
            Tail.prev[i] = Head;
        }
    }
    public void add(long val) {
        skipNode iter = Head;
        skipNode newNode = new skipNode(val,getRandomLevel());
        for (int i = iter.next.length - 1; i >= 0; ) {
            while (iter.next[i].val <= val) {
                iter = iter.next[i];
            }
            if (newNode.next.length > i) {
                newNode.next[i] = iter.next[i];
                iter.next[i] = newNode;
                newNode.prev[i] = iter;
                newNode.next[i].prev[i] = newNode;
            }
            i--;
        }
    }
    public boolean remove(long val) {
        skipNode iter = Head;
        for (int i = iter.next.length - 1; i >= 0;i-- ) {
            while (iter.next[i].val <= val) {
                iter = iter.next[i];
            }
            if (iter.val == val) {
                for (int j = iter.next.length - 1; j >= 0; j--) {
                    iter.next[j].prev[j] = iter.prev[j];
                    iter.prev[j].next[j] = iter.next[j];
                }
                return true;
            }
        }
        return false;
    }
    public long searchClosest(long target) {
        skipNode iter = Head;
        for (int i = iter.next.length - 1; i >= 0; i--) {
            while (iter.next[i].val <= target) {
                iter = iter.next[i];
            }
        }
        if (  target - iter.val <=   iter.next[0].val - target) {
            return iter.val;
        }
        else return iter.next[0].val;
    }
    public boolean isEmpty() {
        return Head.next[0] == Tail;
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