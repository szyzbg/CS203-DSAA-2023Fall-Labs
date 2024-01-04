import java.io.*;
import java.util.*;
 
class Question6 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
 
            int n = in.nextInt();
            Node head = new Node(0,null,null);
            Node tail = new Node(0,null,null);
            NodeBig headBig = new NodeBig(head,head,null,null);
            NodeBig tailBig = new NodeBig(tail,tail,null,null);
            Node[] nodes = new Node[n];
            NodeBig[] nodeBigs = new NodeBig[n];
            for (int j = 0; j < n; j++) {
                nodes[j] = new Node(in.nextInt(),null,null);
                nodeBigs[j] = new NodeBig(nodes[j],nodes[j],null,null);
            }
            for (int j = 0; j < n; j++) {
                if (j > 0) {
                    nodes[j].before = nodes[j - 1];
                    nodeBigs[j].before = nodeBigs[j - 1];
                }
                if (j < n - 1) {
                    nodes[j].next = nodes[j + 1];
                    nodeBigs[j].next = nodeBigs[j + 1];
                }
            }
            head.next = nodes[0];
            nodes[0].before = head;
            tail.before = nodes[n - 1];
            nodes[n - 1].next = tail;
            headBig.next = nodeBigs[0];
            nodeBigs[0].before = headBig;
            tailBig.before = nodeBigs[n - 1];
            nodeBigs[n - 1].next = tailBig;
             
            boolean alwaysIncreasing = false;
            while (!alwaysIncreasing) {
                NodeBig pin1 = headBig.next;
                NodeBig pin2 = headBig.next.next;
                alwaysIncreasing = true;
                while (pin2 != null&&pin2 != tailBig) {
                    if (pin1.tail.item <= pin2.head.item) {
                        while (pin2.next != tailBig&&pin2.next.head.item >= pin2.tail.item) {
                            pin2 = pin2.next;
                        }
                        if (pin2.head == pin2.tail) {
                            pin1.tail = pin2.before.tail;
                            pin1.next = pin2;
                            pin1.next.before = pin1;//合并小链表
                            pin1 = pin1.next;
                            pin2 = pin1.next;
                        }
                        else {
                            pin1.tail = pin2.tail;
                            pin1.next = pin2.next;
                            pin1.next.before = pin1;
                            pin2 = pin1.next;
                        }
 
                    }
                    else {
                        alwaysIncreasing = false;
                        while (pin2.next != tailBig&&pin2.head == pin2.tail&&pin2.next.head.item < pin2.tail.item) {
                            pin2 = pin2.next;
                        }
                        if (pin1.head != pin1.tail&&pin2.head != pin2.tail) {
                            pin1.tail = pin1.tail.before;
                            pin2.head = pin2.head.next;
                            pin1.next = pin2;
                            pin2.before = pin1;
                            pin1.tail.next = pin2.head;
                            pin2.head.before = pin1.tail;
                            pin1 = pin1.next;
                            pin2 = pin2.next;
                        }
                        else if (pin1.head == pin1.tail&&pin2.head == pin2.tail) {
                            pin1.before.next = pin2.next;
                            pin2.next.before = pin1.before;
                            pin1.before.tail.next = pin2.next.head;
                            pin2.next.head.before = pin1.before.tail;
                            pin1 = pin2.next;
                            pin2 = pin1.next;
                        }
                        else if (pin1.head == pin1.tail) {
                            pin1.before.next = pin2;
                            pin2.before = pin1.before;
                            pin2.head = pin2.head.next;
                            pin1.before.tail.next = pin2.head;
                            pin2.head.before = pin1.before.tail;
                            pin2 = pin2.next;
                            pin1 = pin2.before;
                        }
                        else {
                            pin1.tail = pin1.tail.before;
                            pin1.next = pin2.next;
                            pin2.next.before = pin1;
                            pin1.tail.next = pin2.next.head;
                            pin2.next.head.before = pin1.tail;
                            pin1 = pin2.next;
                            pin2 = pin1.next;
                        }//更新存链表的链表
 
//                            Node tmp = pin1.tail.before;
//                            pin1.tail.before.next = pin2.head.next;
//                            pin2.head.next.before = tmp;//更新答案链表
//                            pin1 = pin2.next;
//                            pin2 = pin1.next;
 
                    }
                }
            }
            Node iter = head.next;
            while (iter != null&&iter != tail) {
                out.print(iter.item);
                out.print(" ");
                iter = iter.next;
            }
            out.print("\n");
        }
 
 
        out.close();
    }
    static void changeNodeNext(Node target,Node next) {
        target.next = next;
    }
 
}
 
class Node {
    int item;
    Node next;
    Node before;
    Node(int item,Node next,Node before) {
        this.item = item;
        this.next = next;
        this.before = before;
    }
}
class NodeBig {
    Node head;
    Node tail;
    NodeBig next;
    NodeBig before;
    NodeBig(Node head,Node tail,NodeBig next,NodeBig before) {
        this.head = head;
        this.tail = tail;
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