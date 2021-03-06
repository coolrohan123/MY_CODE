package Codechef.FEBLONG;
import java.util.*;
import java.io.*;
public class E {
        String INPUT = "1 4 2 5" ;
        long MOD = 1000000000+7;
        long a = 0,b=0,first=0;
        void solve()
        {
                long I = l(), D = l(), T = l();
                first = D%MOD*inv(I,MOD)%MOD;
                a = (2*D*inv(I,MOD)%MOD)%MOD;
                b = MOD-1;
                out.println(I%MOD*findNTH(T)%MOD);
        }
        long findNTH(long n)
        {
                if(n == 0)
                        return 1;
                if(n == 1)
                        return first;
                long[][] F = new long[][]{{a,b},{1,0}};
                power(F,n-1);
                return (F[0][0]*first%MOD+F[0][1])%MOD;
        }
        void power(long[][] F,long n)
        {
                if(n == 0 || n == 1)
                        return ;
                long[][] M = new long[][]{{a,b},{1,0}};
                power(F,n/2);
                multiply(F,F);
                if((n&1) == 1)
                        multiply(F,M);
        }
        void multiply(long[][] F,long[][] M)
        {
                long x =  F[0][0]*M[0][0]%MOD + F[0][1]*M[1][0]%MOD;
                long y =  F[0][0]*M[0][1]%MOD + F[0][1]*M[1][1]%MOD;
                long z =  F[1][0]*M[0][0]%MOD + F[1][1]*M[1][0]%MOD;
                long w =  F[1][0]*M[0][1]%MOD + F[1][1]*M[1][1]%MOD;

                F[0][0] = x%MOD;
                F[0][1] = y%MOD;
                F[1][0] = z%MOD;
                F[1][1] = w%MOD;
        }
        private long inv(long base,long mod)
        {
                return modPow(base,mod-2,mod);
        }
        private long modPow(long base,long exp,long mod)
        {
                long res = 1L;
                while(exp>0)
                {
                        if(exp%2==1)
                                res = (res*base)%mod;
                        base = (base*base)%mod;
                        exp>>=1;
                }
                return res;
        }
        void run() throws Exception{
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                int t = i();
                while(t-->0)
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
        }
        public static void main(String[] args)throws Exception {
                new E().run();
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
