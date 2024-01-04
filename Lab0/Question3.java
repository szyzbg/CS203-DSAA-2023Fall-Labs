import java.util.Objects;
import java.util.Scanner;

public class Question3 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            String[] str = new String[13];
            int[] count = new int[35];
            for (int j = 0; j < 13; j++) {
                str[j] = input.next();
            }
            for (int j = 0; j < 13; j++) {
                if (Objects.equals(str[j], "W1")) count[1]++;
                else if (Objects.equals(str[j], "W2")) count[2]++;
                else if (Objects.equals(str[j], "W3")) count[3]++;
                else if (Objects.equals(str[j], "W4")) count[4]++;
                else if (Objects.equals(str[j], "W5")) count[5]++;
                else if (Objects.equals(str[j], "W6")) count[6]++;
                else if (Objects.equals(str[j], "W7")) count[7]++;
                else if (Objects.equals(str[j], "W8")) count[8]++;
                else if (Objects.equals(str[j], "W9")) count[9]++;
                else if (Objects.equals(str[j], "T1")) count[10]++;
                else if (Objects.equals(str[j], "T2")) count[11]++;
                else if (Objects.equals(str[j], "T3")) count[12]++;
                else if (Objects.equals(str[j], "T4")) count[13]++;
                else if (Objects.equals(str[j], "T5")) count[14]++;
                else if (Objects.equals(str[j], "T6")) count[15]++;
                else if (Objects.equals(str[j], "T7")) count[16]++;
                else if (Objects.equals(str[j], "T8")) count[17]++;
                else if (Objects.equals(str[j], "T9")) count[18]++;
                else if (Objects.equals(str[j], "Y1")) count[19]++;
                else if (Objects.equals(str[j], "Y2")) count[20]++;
                else if (Objects.equals(str[j], "Y3")) count[21]++;
                else if (Objects.equals(str[j], "Y4")) count[22]++;
                else if (Objects.equals(str[j], "Y5")) count[23]++;
                else if (Objects.equals(str[j], "Y6")) count[24]++;
                else if (Objects.equals(str[j], "Y7")) count[25]++;
                else if (Objects.equals(str[j], "Y8")) count[26]++;
                else if (Objects.equals(str[j], "Y9")) count[27]++;
                else if (Objects.equals(str[j], "E")) count[28]++;
                else if (Objects.equals(str[j], "S")) count[29]++;
                else if (Objects.equals(str[j], "W")) count[30]++;
                else if (Objects.equals(str[j], "N")) count[31]++;
                else if (Objects.equals(str[j], "B")) count[32]++;
                else if (Objects.equals(str[j], "F")) count[33]++;
                else if (Objects.equals(str[j], "Z")) count[34]++;
            }
            int count2 = 0;
            for (int j = 0; j < count[1]; j++) {
                count2++;
                System.out.print("W1");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[2]; j++) {
                count2++;
                System.out.print("W2");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[3]; j++) {
                count2++;
                System.out.print("W3");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[4]; j++) {
                count2++;
                System.out.print("W4");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[5]; j++) {
                count2++;
                System.out.print("W5");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[6]; j++) {
                count2++;
                System.out.print("W6");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[7]; j++) {
                count2++;
                System.out.print("W7");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[8]; j++) {
                count2++;
                System.out.print("W8");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[9]; j++) {
                count2++;
                System.out.print("W9");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[10]; j++) {
                count2++;
                System.out.print("T1");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[11]; j++) {
                count2++;
                System.out.print("T2");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[12]; j++) {
                count2++;
                System.out.print("T3");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[13]; j++) {
                count2++;
                System.out.print("T4");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[14]; j++) {
                count2++;
                System.out.print("T5");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[15]; j++) {
                count2++;
                System.out.print("T6");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[16]; j++) {
                count2++;
                System.out.print("T7");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[17]; j++) {
                count2++;
                System.out.print("T8");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[18]; j++) {
                count2++;
                System.out.print("T9");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[19]; j++) {
                count2++;
                System.out.print("Y1");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[20]; j++) {
                count2++;
                System.out.print("Y2");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[21]; j++) {
                count2++;
                System.out.print("Y3");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[22]; j++) {
                count2++;
                System.out.print("Y4");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[23]; j++) {
                count2++;
                System.out.print("Y5");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[24]; j++) {
                count2++;
                System.out.print("Y6");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[25]; j++) {
                count2++;
                System.out.print("Y7");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[26]; j++) {
                count2++;
                System.out.print("Y8");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[27]; j++) {
                count2++;
                System.out.print("Y9");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[28]; j++) {
                count2++;
                System.out.print("E");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[29]; j++) {
                count2++;
                System.out.print("S");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[30]; j++) {
                count2++;
                System.out.print("W");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[31]; j++) {
                count2++;
                System.out.print("N");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[32]; j++) {
                count2++;
                System.out.print("B");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[33]; j++) {
                count2++;
                System.out.print("F");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
            for (int j = 0; j < count[34]; j++) {
                count2++;
                System.out.print("Z");
                if (count2 == 13) System.out.println();
                else System.out.print(" ");
            }
        }
    }
}
