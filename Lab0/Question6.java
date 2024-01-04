import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Question6 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        int n = input.nextInt();
        for (int i = 0; i < n; i++) {
            String str = input.next();
            int[] w = new int[30];
            int[] b = new int[30];
            int[] s = new int[30];
            int[] z = new int[30];
            for (int j = 0; j < 30; j++) {
                w[j] = 100;
                b[j] = 100;
                s[j] = 100;
                z[j] = 100;
            }
            for (int j = 1; j < 28; j+=2) {
                if (str.charAt(j) == 'w') {
                    w[j] = str.charAt(j-1)-'0';
                }
                if (str.charAt(j) == 'b') {
                    b[j] = str.charAt(j-1)-'0';
                }
                if (str.charAt(j) == 's') {
                    s[j] = str.charAt(j-1)-'0';
                }
                if (str.charAt(j) == 'z') {
                    z[j] = str.charAt(j-1)-'0';
                }
            }
            Arrays.sort(w);
            Arrays.sort(b);
            Arrays.sort(s);
            Arrays.sort(z);
            int bi = 0;
            int tri = 0;
            int wcount = 0;
            int bcount = 0;
            int scount = 0;
            int zcount = 0;
            for (int j = 0; j < 30; j++) {
                if (w[j] != 100) wcount++;
                else break;
            }
            for (int j = 0; j < 30; j++) {
                if (b[j] != 100) bcount++;
                else break;
            }
            for (int j = 0; j < 30; j++) {
                if (s[j] != 100) scount++;
                else break;
            }
            for (int j = 0; j < 30; j++) {
                if (z[j] != 100) zcount++;
                else break;
            }
            boolean wbo = wcount % 3 == 0;
            boolean bbo = bcount % 3 == 0;
            boolean sbo = scount % 3 == 0;
            boolean zbo = zcount % 3 == 0;
            boolean fin = false;
            if (wbo) {
                tri += tryTri(w);
            }
            if (bbo) {
                tri += tryTri(b);
            }
            if (sbo) {
                tri += tryTri(s);
            }
            if (zbo) {
                for (int j = 0; j < 30; j+=3) {
                    if (z[j] == z[j+1]&&z[j+1]==z[j+2]) {
                        if (z[j] != 100) tri++;
                    }
                    else break;
                }
            }
            int tr = tri;
            if (notTri(wcount)) {
                for (int j = 0; j < wcount; j++) {
                    int[] w2 = w.clone();
                    if (w2[j] == w2[j+1]) {
                        bi = 1;
                        w2[j] = 100;
                        w2[j+1] = 100;
                        Arrays.sort(w2);
                        tri += tryTri(w2);
                        if (bi==1&&tri==4) {
                            fin = true;
                        }
                        tri = tr;
                    }
                }
            }
            if (notTri(bcount)) {
                for (int j = 0; j < bcount; j++) {
                    int[] b2 = b.clone();
                    if (b2[j] == b2[j+1]) {
                        bi = 1;
                        b2[j] = 100;
                        b2[j+1] = 100;
                        Arrays.sort(b2);
                        tri += tryTri(b2);
                        if (bi==1&&tri==4) {
                            fin = true;
                        }
                        tri = tr;
                    }
                }
            }
            if (notTri(scount)) {
                for (int j = 0; j < scount; j++) {
                    int[] s2 = s.clone();
                    if (s2[j] == s2[j+1]) {
                        bi = 1;
                        s2[j] = 100;
                        s2[j+1] = 100;
                        Arrays.sort(s2);
                        tri += tryTri(s2);
                       if (bi==1&&tri==4) {
                            fin = true;
                        }
                        tri = tr;
                    }
                }
            }
            if (notTri(zcount)) {
                for (int j = 0; j < zcount; j++) {
                    int[] z2 = z.clone();
                    if (z2[j] == z2[j+1]) {
                        bi = 1;
                        z2[j] = 100;
                        z2[j+1] = 100;
                        Arrays.sort(z2);
                        for (int k = 0; k < zcount - 2; k+=3) {
                            if (z2[k] == z2[k+1]&&z2[k+1]==z2[k+2]) {
                                tri++;
                            }
                        }
                        if (bi==1&&tri==4) {
                            fin = true;
                        }
                        tri = tr;
                    }
                }
            }
            if (fin) System.out.println("Blessing of Heaven");
            if (!fin) System.out.println("Bad luck");
        }
    }
    public static boolean isTri(int a,int b,int c) {
        return (a==b&&b==c)||(b==a+1&&c==b+1);
    }
    public static boolean notTri(int t) {
        return t%3==2;
    }
    public static int tryTri(int[] a) {
        int[] a2 = a.clone();
        int maxtri = 0;
        int triin = 0;
        for (int i = 0; i < 20; i++) {
            for (int j = i+1; j < 20; j++) {
                for (int k = j+1; k < 20; k++) {
                    if(isTri(a2[i],a2[j],a2[k])&&a2[i]!=100&&a2[j]!=100&&a2[k]!=100) {
                        triin++;
                        a2[i] = 100;a2[j] = 100;a2[k] = 100;
                        if (triin > maxtri) maxtri = triin;
                    }
                }
            }
        }
        return maxtri;
    }
}
