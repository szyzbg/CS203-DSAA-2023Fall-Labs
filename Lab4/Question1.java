import java.util.Objects;
import java.util.Scanner;

public class Question1 {
   public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       int T = input.nextInt();
       for (int i = 0; i < T; i++) {
           int n = input.nextInt();
           char[] s = new char[n];
           String str = input.next();
           for (int j = 0; j < n; j++) {
               s[j] = str.charAt(j);
           }
           boolean boo = false;
           stack st = new stack();
           for (int j = 0; j < n; j++) {
               if (s[j] == '(') {
                   st.push(1);
               }
               else if (s[j] == '[') {
                   st.push(2);
               }
               else if (s[j] == '{') {
                   st.push(3);
               }
               else if (s[j] == ')') {
                   if (st.pop() == 1) {
                       continue;
                   }
                   else {
                       boo = true;
                       break;
                   }
               }
               else if (s[j] == ']') {
                   if (st.pop() == 2) {
                       continue;
                   }
                   else {
                       boo = true;
                       break;
                   }
               }
               else if (s[j] == '}') {
                   if (st.pop() == 3) {
                       continue;
                   }
                   else {
                       boo = true;
                       break;
                   }
               }
           }
           if (st.top != 0) boo = true;
           if (boo) System.out.println("NO");
           else System.out.println("YES");
       }
   }
}
class stack {
   int top = 0;
   int[] arr = new int[100000];
   void push(int category) {
       arr[top] = category;
       top++;
   }
   int pop() {
       if (top > 0) {
           int tmp = arr[top - 1];
           top--;
           return tmp;
       }
       else return -1;
   }
}