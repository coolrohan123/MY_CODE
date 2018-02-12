package Practice;

import java.io.BufferedReader;
import java.io.InputStreamReader;

// Please name your class Main
class Main {
        public static void main(String[] args) throws java.lang.Exception {
                BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                int n = Integer.parseInt(br.readLine());
                String[] str = new String[n];
                for (int i = 0; i < n; i++) {
                        str[i] = br.readLine();
                }
                for (int i = 0; i < n; i++) {
                        boolean ok = false;
                        for (int j = 0; j < n; j++) {
                                for (int k = 0; k < n; k++) {
                                        if (j == k) continue;
                                        ok |= str[i].equals(str[j] + "" + str[k]);
                                }
                                if (ok) {
                                        System.out.print(i + 1 + " ");
                                        break;
                                }
                        }
                }


        }
}