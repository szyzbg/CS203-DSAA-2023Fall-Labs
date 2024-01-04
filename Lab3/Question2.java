import java.io.*;
import java.util.*;
 
class Question2 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int n = in.nextInt();
            Node EOL = new Node("",null,null);
            Node head = new Node("",null,null);
            head.next = EOL;
            EOL.before = head;
            String str = in.next();
            Node pin = EOL;
            boolean replace = false;
            for (int j = 0; j < n; j++) {
 
                    if (replace) {
                        if (pin == EOL) {
                            Node tmp = EOL.before;
                            Node newNode = new Node(String.valueOf(str.charAt(j)),tmp,EOL);
                            tmp.next = newNode;
                            EOL.before = newNode;
                            pin = newNode;
                        }
                        else {
                            pin.item = String.valueOf(str.charAt(j));
                        }
                        replace = false;
                        continue;
                    }
                    else if (str.charAt(j) == 'r') {
                        replace = true;
                        continue;
                    }
                    else if (str.charAt(j) == 'I') {
                        pin = head.next;
                        continue;
                    }
                    else if (str.charAt(j) == 'H') {
                        pin = (pin.before == head) ? pin : pin.before;
                    }
                    else if (str.charAt(j) == 'L') {
                        pin = (pin.next == null) ? pin : pin.next;
                    }
                    else if (str.charAt(j) == 'x') {
                        if (pin != EOL) {
                            pin.before.next = pin.next;
                            pin.next.before = pin.before;
                            pin = pin.next;
                        }
                    }
                    else {
                        Node newNode = new Node(String.valueOf(str.charAt(j)),pin.before,pin);
                        newNode.before.next = newNode;
                        newNode.next.before = newNode;
                    }
 
            }
            Node iter = head.next;
            while (iter.next != null) {
                out.print(iter.item);
                iter = iter.next;
            }
            out.print("\n");
        }
        out.close();
    }
}
class Node {
    String item;
    Node before;
    Node next;
    Node(String item,Node before,Node next) {
        this.item = item;
        this.before = before;
        this.next = next;
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