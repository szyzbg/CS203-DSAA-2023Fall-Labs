import java.io.*;
import java.util.*;
 
class Question1 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int n = in.nextInt();
        int m = in.nextInt();
        Node headn = new Node(-1,-1);
        Node itern = headn;
        for (int i = 0; i < n; i++) {
            Node tmp = new Node(in.nextInt(),in.nextInt());
            itern.next = tmp;
            itern = itern.next;
        }
        Node tailn = new Node(-1,-1);
        itern.next = tailn;
 
        Node headm = new Node(-1,-1);
        Node iterm = headm;
        for (int i = 0; i < m; i++) {
            Node tmp = new Node(in.nextInt(),in.nextInt());
            iterm.next = tmp;
            iterm = iterm.next;
        }
        Node tailm = new Node(-1,-1);
        iterm.next = tailm;

        int count = 0;
        itern = headn.next;
        iterm = headm.next;
        while (iterm.next != null||itern.next != null) {
            if (iterm.next != null&&itern.next != null) {
                if (iterm.exp > itern.exp) {
                    count++;
                    iterm = iterm.next;
                } else if (iterm.exp < itern.exp) {
                    count++;
                    itern = itern.next;
                } else {
                    int cnt = 0;
                    cnt += (iterm.coef+itern.coef);
                    if(cnt != 0) {
                        count++;
                    }
 
                    iterm = iterm.next;
                    itern = itern.next;
                }
            }
            else if (iterm.next == null) {
                count++;
                itern = itern.next;
            }
            else {
                count++;
                iterm = iterm.next;
            }
        }
        out.println(count);
        itern = headn.next;
        iterm = headm.next;
        while (iterm.next != null||itern.next != null) {
            if (iterm.next != null&&itern.next != null) {
                if (iterm.exp > itern.exp) {
                    out.println(iterm.coef+" "+iterm.exp);
                    iterm = iterm.next;
                } else if (iterm.exp < itern.exp) {
                    out.println(itern.coef+" "+itern.exp);
                    itern = itern.next;
                } else {
                    int cnt = 0;
                    cnt += (iterm.coef+itern.coef);
                    if(cnt != 0) {
                        out.println((iterm.coef+itern.coef) + " " + iterm.exp);
                    }
 
                    iterm = iterm.next;
                    itern = itern.next;
                }
            }
            else if (iterm.next == null) {
                out.println(itern.coef+" "+itern.exp);
                itern = itern.next;
            }
            else {
                out.println(iterm.coef+" "+iterm.exp);
                iterm = iterm.next;
            }
        }
 
 
        out.close();
    }
}
 class Node {
    int coef;
    int exp;
    Node next;
    Node(int coef,int exp) {
        this.coef = coef;
        this.exp = exp;
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