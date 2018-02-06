package Round458;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class C {
        String INPUT = "111111011\n" +
                "2";
        int mod = 1000000007;
        void solve()
        {
                char[] s = s().toCharArray();
                int k = i();
                int[] dp = new int[1005];
                dp[1] = 0;
                for (int i = 2; i <1005; i++) {
                        dp[i] = dp[Integer.bitCount(i)]+1;
                }
                long[][] ncr = new long[1005][1005];
                for (int i = 0; i < 1005; i++) {
                        ncr[i][0] = 1;
                        for (int j = 1; j <=i; j++) {
                                ncr[i][j] = (ncr[i-1][j-1]%mod+ncr[i-1][j]%mod)%mod;
                                ncr[i][j] %= mod;
                        }
                }
                long ans = 0; int ones = 0;
                for(int i = 0 ; i<s.length; i++)
                {
                        if(s[i]=='0')
                                continue;
                        for(int j = Math.max(ones,1); j<1005; j++)
                        {
                                if(dp[j]==k-1)
                                {
                                        ans += (ncr[s.length-i-1][j-ones]);
                                        ans%=mod;
                                        if(i==0 && k ==1)
                                        {
                                                ans = (ans-1+mod)%mod;
                                        }
                                }
                        }
                        ones++;
                }
                if(k==0)
                {
                        out.println(1);
                        return;
                }

                int count = 0;
                for(int i = 0 ; i<s.length; i++)
                {
                        if(s[i] =='1')
                                count++;
                }
                if(dp[count]==k-1)
                        ans = (ans+1)%mod;
                out.println(ans);
        }
        ///////////////////////////////////////
        void run() throws Exception{
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
        }
        public static void main(String[] args)throws Exception {
                new C().run();
        }
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        public int lenbuf = 0, ptrbuf = 0;

        private int readByte()
        {
                if(lenbuf == -1)throw new InputMismatchException();
                if(ptrbuf >= lenbuf){
                        ptrbuf = 0;
                        try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
                        if(lenbuf <= 0)return -1;
                }
                return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
        private int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }

        private double d() { return Double.parseDouble(s()); }
        private char c() { return (char)skip(); }

        private String s()
        {
                int b = skip();
                StringBuilder sb = new StringBuilder();
                while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
                        sb.appendCodePoint(b);
                        b = readByte();
                }
                return sb.toString();
        }

        private char[] sa(int n)
        {
                char[] buf = new char[n];
                int b = skip(), p = 0;
                while(p < n && !(isSpaceChar(b))){
                        buf[p++] = (char)b;
                        b = readByte();
                }
                return n == p ? buf : Arrays.copyOf(buf, p);
        }

        private char[][] nm(int n, int m)
        {
                char[][] map = new char[n][];
                for(int i = 0;i < n;i++)map[i] = sa(m);
                return map;
        }

        private int[] ia(int n)
        {
                int[] a = new int[n];
                for(int i = 0;i < n;i++)a[i] = i();
                return a;
        }

        private int i()
        {
                int num = 0, b;
                boolean minus = false;
                while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
                if(b == '-'){
                        minus = true;
                        b = readByte();
                }

                while(true){
                        if(b >= '0' && b <= '9'){
                                num = num * 10 + (b - '0');
                        }else{
                                return minus ? -num : num;
                        }
                        b = readByte();
                }
        }

        private long l()
        {
                long num = 0;
                int b;
                boolean minus = false;
                while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
                if(b == '-'){
                        minus = true;
                        b = readByte();
                }

                while(true){
                        if(b >= '0' && b <= '9'){
                                num = num * 10 + (b - '0');
                        }else{
                                return minus ? -num : num;
                        }
                        b = readByte();
                }
        }

        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }

}
