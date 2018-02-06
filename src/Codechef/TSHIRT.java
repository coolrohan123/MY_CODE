package Codechef;
import java.util.*;
import java.io.*;
public class TSHIRT {
        static int MOD = 1000000007;
        static int all ;
        static int[][] dp = new int[1025][102];
        static ArrayList<Integer>[] al = new ArrayList[101];

        public static void main(String[] args) throws Exception {
                final BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
                final PrintWriter    out = new PrintWriter(System.out);
                int t = Integer.parseInt(br.readLine());

                while(t-->0)
                {
                        preprocess();
                        final int n = Integer.parseInt(br.readLine());

                        all = (1<<n)-1;
                        for (int i = 0; i < n; i++) {
                                String[] x = br.readLine().split(" ");
                                for (int j = 0; j < x.length; j++) {
                                        al[Integer.parseInt(x[j])].add(i);
                                }
                        }
                        out.println(calc(0,1));
                }
                out.flush();
                out.close();
        }
        private static long calc(int mask,int tid)
        {
                if(mask == all)
                        return 1;
                if(tid == 101)
                        return 0;
                if(dp[mask][tid]!=-1)
                        return dp[mask][tid];

                long ans = 0;
                ans = calc(mask,tid+1);

                for(int p: al[tid])
                {
                        if((mask&(1<<p))==0)
                                ans += calc(mask|(1<<p),tid+1);
                }
                ans %= MOD;
                return dp[mask][tid] = (int)ans;
        }

        private static void preprocess() {
                for (int i = 0; i < 101; i++) {
                        al[i] = new ArrayList<>();
                }
                for (int i = 0; i < 1025; i++) {
                        for (int j = 0; j < 101; j++) {
                                dp[i][j] = -1;
                        }
                }
        }
}
