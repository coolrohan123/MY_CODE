//package Codechef.FEBLONG;
//package Codechef.FEBLONG;
package Codes;
import java.util.*;
import java.io.*;
public class Main {
        String INPUT = "3\n" +
                "4 2 1\n" +
                "4 2 2\n" +
                "4 2 3";
        /*
                TC1:
                5
                5 1 1
                5 1 2
                5 1 3
                5 1 4
                5 1 5
                1
                800000001
                119999998
                648000008
                539200008
        TC
        I=3, D=1
        t=     1     2         3         4         5         6         7
        ans=   1 666666669 444444445 296296299 753086428 872427991 495198904
         */
        long sin2x = 0;
        Map<Long,Long> set = new HashMap<>();
        void solve()
        {
               long I = l(), D = l(), T = l();
               long mod = 1000000000+7;
               long cosx = D%mod*inv(I,mod)%mod;
               sin2x = (1-modPow(cosx,2,mod)+mod)%mod;
               set.clear();
               if((T&(-T))==T)
               {
                        if(T==1)
                        {
                                out.println(D);
                                return;
                        }
                        out.println(I%mod*findAns(cosx,T,mod)%mod);
               }
               else
               {
                        long cosnx = cosnx(cosx,T,mod);
                        out.println(I%mod*cosnx%mod);
               }
        }
        long cosnx(long cosx,long t,long mod)
        {
                if(t==2)
                {
                        long ans =  (2*modPow(cosx,2,mod)-1+mod)%mod;
                        set.put(2L,ans);
                        return ans;
                }
                if((t&1)==0){
                        long even= (2*modPow(cosnx(cosx,t/2,mod),2,mod)-1+mod)%mod;
                        set.put(t,even);
                        return even;
                }
                long cosNx = (cosnx(cosx,t-1,mod)%mod*cosx%mod - sinnx(cosx,t-1,mod)%mod*sin2x%mod+mod)%mod;
                set.put(t,cosNx);
                return cosNx;

        }
        long sinnx(long cosx,long t,long mod)
        {
                if(t==2)
                        return 2*cosx%mod;
                if((t&1)==0) {
                        return 2 * sinnx(cosx,t/2,mod)%mod*set.get(t/2)%mod;
                }

                return (sinnx(cosx,t-1,mod)*cosx%mod + set.get(t-1))%mod;
        }
        long findAns(long cosx,long t,long mod)
        {
                if(t==2)
                        return (2*modPow(cosx,2,mod)-1+mod)%mod;
                return (2*modPow(findAns(cosx,t/2,mod),2,mod)-1+mod)%mod;
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
                //is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                int t = i();
                while(t-->0)
                        solve();
                out.flush();
                //tr(System.currentTimeMillis()-s+"ms");
        }
        public static void main(String[] args)throws Exception {
                new Main().run();
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
} 