import java.io.*;
import java.util.*;

public class Question5 {
    public static void main(String[] args) {
        QReader in = new QReader();
        QWriter out = new QWriter();
        int T = in.nextInt();
        for (int i = 0; i < T; i++) {
            int row = in.nextInt();
            int column = in.nextInt();
            int[][] matrix = new int[row][column];
            for (int j = 0; j < row; j++) {
                for (int k = 0; k < column; k++) {
                    matrix[j][k] = in.nextInt();
                }
            }
            out.println(maxSum(matrix));
            max = 0;
        }
        out.close();
    }
    static int[][] dxy = {{0,1},{1,0},{0,-1},{-1,0},{1,-1},{1,1},{-1,1},{-1,-1}};
    static int max = 0;
    static int maxSum(int[][] matrix) {
        boolean[][] isChosen = new boolean[matrix.length][matrix[0].length];
        boolean[][] isVisited = new boolean[matrix.length][matrix[0].length];
        dfs(matrix,isChosen,isVisited,0,0,0);
        return max;
    }

    static void dfs(int[][] matrix,boolean[][] isChosen,boolean[][] isVisited,int nowRow,int nowCol,int sum) {
        isVisited[nowRow][nowCol] = true;
        boolean surroundChosen = false;
        for (int i = 0; i < 8; i++) {
            try {
                int r = nowRow + dxy[i][0];
                int c = nowCol + dxy[i][1];
                if (isChosen[r][c]) {
                    surroundChosen = true;
                    break;
                }
            } catch (IndexOutOfBoundsException e) {
                continue;
            }
        }
        if (surroundChosen) {
            for (int i = 0; i < 4; i++) {
                try {
                    int r = nowRow + dxy[i][0];
                    int c = nowCol + dxy[i][1];
                    if (!isVisited[r][c]) {
                        dfs(matrix,isChosen,isVisited,r,c,sum);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        else {
            //没有周围被选时有两种支路，选或不选
            //不选
            for (int i = 0; i < 4; i++) {
                try {
                    int r = nowRow + dxy[i][0];
                    int c = nowCol + dxy[i][1];
                    if (!isVisited[r][c]) {
                        dfs(matrix,isChosen,isVisited,r,c,sum);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
            //选
            isChosen[nowRow][nowCol] = true;
            sum += matrix[nowRow][nowCol];
            max = Math.max(max,sum);
            for (int i = 0; i < 4; i++) {
                try {
                    int r = nowRow + dxy[i][0];
                    int c = nowCol + dxy[i][1];
                    if (!isVisited[r][c]) {
                        dfs(matrix,isChosen,isVisited,r,c,sum);
                        break;
                    }
                } catch (IndexOutOfBoundsException e) {
                    continue;
                }
            }
        }
        isVisited[nowRow][nowCol] = false;
        isChosen[nowRow][nowCol] = false;
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