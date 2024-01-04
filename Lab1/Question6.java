import java.util.Scanner;

public class Question6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        long x1 = input.nextLong();
        long y1 = input.nextLong();
        long x2 = input.nextLong();
        long y2 = input.nextLong();
        int N = input.nextInt();
        String s = input.next();
        int[] upDown = new int[N];
        int[] leftRight = new int[N];
        for (int i = 0; i < N; i++) {
            if (s.charAt(i) == 'U') {
                if (i == 0) upDown[i] = 1;
                else {
                    upDown[i] = upDown[i - 1] + 1;
                    leftRight[i] = leftRight[i - 1];
                }
            }
            if (s.charAt(i) == 'D') {
                if (i == 0) upDown[i] = -1;
                else {
                    upDown[i] = upDown[i - 1] - 1;
                    leftRight[i] = leftRight[i - 1];
                }
            }
            if (s.charAt(i) == 'L') {
                if (i == 0) leftRight[i] = -1;
                else {
                    leftRight[i] = leftRight[i - 1] - 1;
                    upDown[i] = upDown[i - 1];
                }
            }
            if (s.charAt(i) == 'R') {
                if (i == 0) leftRight[i] = 1;
                else {
                    leftRight[i] = leftRight[i - 1] + 1;
                    upDown[i] = upDown[i - 1];
                }
            }
        }
        long x = 0;long y = 1000000000000000L;
        long mid;
        mid = (x + y) / 2;
        int count = 0;
        while (count <= 70) {
            mid = (x + y) / 2;
            int rest = (int) (mid % N);
            long times = mid / N;
            long distUD;
            long distLR;
            long lUD = times * upDown[N - 1];
            long lLR = times * leftRight[N - 1];
            if (rest == 0) {
                distUD = lUD;
                distLR = lLR;
            }
            else {
                distUD = lUD + upDown[rest - 1];
                distLR = lLR + leftRight[rest - 1];
            }
            if (Math.abs(x2 - x1 + distLR) + Math.abs(y2 - y1 + distUD) - mid > 0) x = mid + 1;
            //else if (Math.abs(x2 - x1 + distLR) + Math.abs(y2 - y1 + distUD) - mid == 0||mid - Math.abs(x2 - x1 + distLR) - Math.abs(y2 - y1 + distUD) == 1) break;
            else y = mid;
            count++;
        }
        if (mid >= 900000000000000L) System.out.println("-1");
        else System.out.println(mid);
    }
}
