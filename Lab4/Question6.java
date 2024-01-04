import java.io.*;
import java.util.*;

public class Question6 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int k = in.nextInt();
        int n = in.nextInt();
        int[] arr = new int[n];
        for (int i = 0; i < n; i++) {
            arr[i] = in.nextInt();
        }
        out.println(findMaxDistance(arr,k));
        out.close();
    }
    public static int findMaxDistance(int[] arr,int k) {
        int ans = 0;
        Node headInMax = new Node(-1,null,null);
        Node tailInMax = new Node(-1,headInMax,null);
        headInMax.next = tailInMax;
        Node headInMin = new Node(-1,null,null);
        Node tailInMin = new Node(-1,headInMin,null);
        headInMin.next = tailInMin;
        int pin1 = 0;
        int pin2 = 0;
        while (pin1 < arr.length) {
            if (headInMax.next == tailInMax||headInMin.next == tailInMin||pin2 < arr.length&&arr[headInMax.next.item] - arr[headInMin.next.item] <= k) {
                while (headInMax.next != tailInMax&&arr[tailInMax.before.item] < arr[pin2]) {
                    tailInMax.before.before.next = tailInMax;
                    tailInMax.before = tailInMax.before.before;
                }
                Node tmp = new Node(pin2,tailInMax.before,tailInMax);
                tailInMax.before = tmp;
                tmp.before.next = tmp;//更新最大值
                while (headInMin.next != tailInMin&&arr[tailInMin.before.item] > arr[pin2]) {
                    tailInMin.before.before.next = tailInMin;
                    tailInMin.before = tailInMin.before.before;
                }
                Node tmp2 = new Node(pin2,tailInMin.before,tailInMin);
                tailInMin.before = tmp2;
                tmp2.before.next = tmp2;//更新最小值
                pin2++;
                if (arr[headInMax.next.item] - arr[headInMin.next.item] <= k) {
                    ans = Math.max(ans,pin2 - pin1);
                }


            }
            else {
                pin1++;
                if (headInMax.next.item < pin1) {
                    headInMax.next = headInMax.next.next;
                    headInMax.next.before = headInMax;
                }
                if (headInMin.next.item < pin1) {
                    headInMin.next = headInMin.next.next;
                    headInMin.next.before = headInMin;
                }
            }
        }
        return ans;
    }


}

class Node {
    int item;
    Node before;
    Node next;
    Node(int item,Node before,Node next) {
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