import java.util.Scanner;

public class Question1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        int[] a = new int[n+5];
        for (int i = 0;i < n;i++) {
            a[i] = input.nextInt();
        }

        int t = input.nextInt();
        int[] b = new int[t+5];
        boolean flag = false;
        for (int i = 0;i < t;i++){
            b[i] = input.nextInt();
            flag = false;
            for (int j = 0;j < n;j++){
                if(!flag){
                    if (b[i] == a[j]){
                        flag = true;
                        System.out.println("yes");
                    }
                }
                }
            if(!flag) {
                System.out.println("no");
            }
            }
        }
    }
