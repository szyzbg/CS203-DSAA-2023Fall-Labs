import java.math.BigInteger;
import java.util.Objects;
import java.util.Scanner;

public class Question3 {
   public static void main(String[] args) {
       Scanner input = new Scanner(System.in);
       String in = input.next();
       String str = "(" + in + ")";
       stack st = new stack();
       for (int i = 0; i < str.length(); i++) {
           if (str.charAt(i) == '(') {
               st.push(BigInteger.valueOf(0));
           }
           else if (str.charAt(i) == ')'&&str.charAt(i - 1) == '(') {
               st.arr[st.top - 1] = BigInteger.valueOf(1);
           }
           else {
               BigInteger tmp = BigInteger.valueOf(0);
               while (!Objects.equals(st.arr[st.top - 1], BigInteger.valueOf(0))) {
                   tmp = tmp.add(st.arr[st.top - 1]);
                   st.top--;
               }
               st.arr[st.top - 1] = tmp.multiply(BigInteger.valueOf(2));
           }
       }
       System.out.println(st.pop().divide(BigInteger.valueOf(2)).mod(BigInteger.valueOf(514329)) );
   }
}
class stack {
   int top = 0;
   BigInteger[] arr = new BigInteger[100009];
   void push(BigInteger category) {
       arr[top] = category;
       top++;
   }
   BigInteger pop() {
       if (top > 0) {
           BigInteger tmp = arr[top - 1];
           top--;
           return tmp;
       }
       else return BigInteger.valueOf(-1);
   }
}