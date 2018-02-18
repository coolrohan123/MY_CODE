package CFRound.Round451;

import FastScanner.Competitive;
import java.io.PrintWriter;
import java.util.Arrays;

public class TaskA {
    public void solve(int testNumber, Competitive in, PrintWriter out) {
            String s = in.s();
            int[] a = new int[s.length()+1];
            for (int i = 1; i <= s.length() ; i++) {
                    a[i] = s.charAt(i-1)-'0';
            }
            boolean carry = false;
            for (int i = s.length(); i >=0 ; i--) {
                    if(i== s.length()){
                            if(a[i]>=5){
                                    a[i] = 0;
                                    carry = true;
                            }
                            else{
                                    a[i] = 0;
                                    carry = false;
                            }

                    }
                    else if(carry){
                            if(a[i]==9){
                                    a[i]++;
                                    continue;
                            }
                            else{
                                    a[i]++;
                                    carry=false;
                            }
                    }
                    else
                            break;
            }
            Arrays.stream(a,1,s.length()+1).forEach(out::print);
    }
}
