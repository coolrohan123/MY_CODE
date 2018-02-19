package Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Arrays;

// Please name your class Main
class Main {
        public static void main(String[] args) throws java.lang.Exception {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                String[] str = br.readLine().split("\\+");
                int[] a = new int[str.length];
                for (int i = 0; i < str.length; i++) {
                        a[i] = str[i].charAt(0) - '0';
                }
                Arrays.sort(a);
                for (int i = 0; i < a.length; i++) {
                        System.out.print(a[i]);
                        if (i != a.length - 1) {
                                System.out.print('+');
                        }
                }
        }
}