package CFRound.Round458;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class D {
         String INPUT = "5\n" +
                 "1 2 3 4 5\n" +
                 "6\n" +
                 "1 1 4 2\n" +
                 "2 3 6\n" +
                 "1 1 4 2\n" +
                 "1 1 5 2\n" +
                 "2 5 10\n" +
                 "1 1 5 2";
        public class Tree {
                int pow = 1 << 19;
                int[] gcd = new int[2 * pow];

                Tree(int[] a) {
                        for (int i = 0; i < a.length; i++) {
                                gcd[pow + i] = a[i];
                        }
                        for (int i = pow - 1; i > 0; i--) {
                                update(i);
                        }
                }

                private void update(int v) {
                        gcd[v] = gcd(gcd[2 * v], gcd[2 * v + 1]);
                }

                private void set(int to, int x) {
                        set(1, 0, pow - 1, to, x);
                }

                private void set(int v, int l, int r, int to, int x) {
                        if (v >= pow) {
                                gcd[v] = x;
                        } else {
                                int m = (l + r) / 2;
                                if (to <= m) {
                                        set(2 * v, l, m, to, x);
                                } else {
                                        set(2 * v + 1, m + 1, r, to, x);
                                }
                                update(v);
                        }
                }

                private boolean get(int l, int r, int x) {
                        int y = get(1, 0, 0, pow - 1, l, r, x);
                        return y < 2;
                }

                private int get(int v, int n, int l, int r, int left, int right, int x) {
                        if (r < left || l > right) {
                                return n;
                        }
                        if (l >= left && r <= right) {
                                if (gcd[v] % x == 0) {
                                        return n;
                                }
                                if (v >= pow) {
                                        n++;
                                        return n;
                                }
                        }
                        int m = (l + r) / 2;
                        n = get(2 * v, n, l, m, left, right, x);
                        if (n > 1) {
                                return n;
                        }
                        n = get(2 * v + 1, n, m + 1, r, left, right, x);
                        return n;
                }private int gcd(int a,int b)
                {
                        return  b == 0? a: gcd(b,a%b);
                }
        }

         void solve()
         {
                 int n = i();
                 int[] a = new int[n];
                 for (int i = 0; i <n ; i++) {
                         a[i] = i();
                 }
                 Tree seg = new Tree(a);
                 int q = i();
                 while(q-->0)
                 {
                         if(i()==1)
                         {
                                 out.println(seg.get(i()-1,i()-1,i())?"YES":"NO");
                         }
                         else
                                 seg.set(i()-1,i());
                 }

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
                new D().run();
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
