import java.util.Scanner;

public class Question5 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            final int a = input.nextInt();
            final int b = input.nextInt();
            final int c = input.nextInt();
            String str1 = "+-";
            String str2 = "/.";
            String str3 = ".+";
            String str4 = "|/";
            String str5 = "/|";
        if (c > b) {
            //up
            for (int j = 0; j < 2 * b; j++) {

                if (!isEven(j)) {
                    for (int k = 0; k < 2 * b - j; k++) {
                        System.out.print(".");
                    }
                    for (int k = 0; k < a; k++) {
                        System.out.print(str1);
                    }
                    System.out.print("+");
                    for (int k = 0; k < j / 2; k++) {
                        System.out.print(str3);
                    }
                    System.out.print("\n");
                }
                if (isEven(j)) {
                    for (int k = 0; k < 2 * b - j; k++) {
                        System.out.print(".");
                    }
                    for (int k = 0; k < a; k++) {
                        System.out.print(str2);
                    }
                    System.out.print("/");
                    for (int k = 0; k < (j - 1) / 2; k++) {
                        System.out.print(str4);
                    }
                    System.out.print("|");
                    System.out.print("\n");
                }
            }
            //mid
            for (int k = 0; k < c - b; k++) {
                for (int l = 0; l < a; l++) {
                    System.out.print(str1);
                }
                System.out.print("+");
                for (int l = 0; l < b; l++) {
                    System.out.print(str3);
                }
                System.out.print("\n");
                for (int l = 0; l < a; l++) {
                    System.out.print("|.");
                }
                System.out.print("|");
                for (int l = 0; l < b; l++) {
                    System.out.print(str5);
                }
                System.out.print("\n");
            }
            for (int l = 0; l < a; l++) {
                System.out.print(str1);
            }
            System.out.print("+");
            for (int l = 0; l < b; l++) {
                System.out.print(str3);
            }
            System.out.print("\n");
//            //down
            for (int j = 0; j < 2 * b; j++) {

                if (!isEven(j)) {

                    for (int k = 0; k < a; k++) {
                        System.out.print("|.");
                    }
                    for (int k = 0; k < b - j / 2; k++) {
                        System.out.print("|/");
                    }
                    for (int k = 0; k < j + 1; k++) {
                        System.out.print(".");
                    }
                    System.out.print("\n");
                }
                if (isEven(j)) {
                    for (int k = 0; k < a; k++) {
                        System.out.print(str1);
                    }

                    for (int k = 0; k < b - (j + 1) / 2; k++) {
                        System.out.print("+.");
                    }
                    System.out.print("+");
                    for (int k = 0; k < j + 1; k++) {
                        System.out.print(".");
                    }
                    System.out.print("\n");
                }
            }
        }
        else {
            //up
            for (int j = 0; j < 2 * c; j++) {

                if (!isEven(j)) {
                    for (int k = 0; k < 2 * b - j; k++) {
                        System.out.print(".");
                    }
                    for (int k = 0; k < a; k++) {
                        System.out.print(str1);
                    }
                    System.out.print("+");
                    for (int k = 0; k < j / 2; k++) {
                        System.out.print(str3);
                    }
                    System.out.print("\n");
                }
                if (isEven(j)) {
                    for (int k = 0; k < 2 * b - j; k++) {
                        System.out.print(".");
                    }
                    for (int k = 0; k < a; k++) {
                        System.out.print(str2);
                    }
                    System.out.print("/");
                    for (int k = 0; k < (j - 1) / 2; k++) {
                        System.out.print(str4);
                    }
                    System.out.print("|");
                    System.out.print("\n");
                }
            }
            //mid
            for (int j = 2 * c; j < 2 * b; j++) {
                if (!isEven(j)) {
                    for (int k = 0; k < 2 * b - j; k++) {
                        System.out.print(".");
                    }
                    for (int k = 0; k < a; k++) {
                        System.out.print(str1);
                    }
                    System.out.print("+");
                    for (int k = 0; k < c; k++) {
                        System.out.print(".+");
                    }
                    for (int k = 0; k < 2 * (b -  c) - (2 * b - j); k++) {
                        System.out.print(".");
                    }
                    System.out.print("\n");
                }
                if (isEven(j)) {
                    for (int k = 0; k < 2 * b - j; k++) {
                        System.out.print(".");
                    }
                    for (int k = 0; k < a; k++) {
                        System.out.print(str2);
                    }
                    System.out.print("/");
                    for (int k = 0; k < c; k++) {
                        System.out.print("|/");
                    }
                    for (int k = 0; k < 2 * (b -  c) - (2 * b - j); k++) {
                        System.out.print(".");
                    }
                    System.out.print("\n");
                }
            }
            //down
            for (int j = 2 * b; j < 2 * (b + c) + 1; j++) {
                if (!isEven(j)) {
                    for (int k = 0; k < a; k++) {
                        System.out.print(str1);
                    }
                    for (int k = 0; k < b + c - j / 2; k++) {
                        System.out.print("+.");
                    }
                    System.out.print("+");
                    for (int k = 0; k < 2 * (b -  c) - (2 * b - j); k++) {
                        System.out.print(".");
                    }
                    System.out.print("\n");
                }
                if (isEven(j)) {
                    for (int k = 0; k < a; k++) {
                        System.out.print("|.");
                    }
                    for (int k = 0; k < b + c - (j - 1) / 2; k++) {
                        System.out.print("|/");
                    }
                    for (int k = 0; k < 2 * (b -  c) - (2 * b - j); k++) {
                        System.out.print(".");
                    }
                    System.out.print("\n");
                }
            }
        }
        }
    }
    public static boolean isEven(int t) {
        return !(t % 2 == 0);
    }
}
