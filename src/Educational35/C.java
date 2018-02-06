package Educational35;

import FastScanner.Competitive;

import java.util.*;
import java.io.*;

public class C {
        String INPUT = "2 2 3";

        void solve()
        {
                /**
                 * for given k1,k2,k3 find whether it is possible
                 * to generate S1US2US3={m,m+1,m+2,...} where m = Max(m1,m2,m3)
                 * and S1 = x1,x1+k1,x1+2k1,....
                 * S2 = x2,x2+k2,x2+2k2,....
                 * and so on
                 *
                 * Solution:
                 * There are a few cases to consider for YES
                 * THose are
                 * 1. one of k =1
                 * 2. exactly two of them=2
                 * 3. all of them =3
                 * 4.{2,4,4}
                 */
                int[] a = ia(3);
                Arrays.sort(a);
                if(a[0] == 1)
                {
                        out.println("YES");
                }
                else if ((a[1]==2 &&a[2]==2 )||(a[0]==2 &&a[1]==2))
                {
                        out.println("YES");
                }
                else if(a[0]==3 && a[1]==3 &&a[2]==3)
                {
                        out.println("YES");
                }
                else if(a[0]==2 && a[1] ==4 && a[2]==4)
                {
                        out.println("YES");
                }
                else
                {
                        out.println("NO");
                }
        }
        ///////////////////////////////////////
        void run() throws Exception{
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                int t = 1;
                while(t-->0)	solve();
                out.flush();
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
