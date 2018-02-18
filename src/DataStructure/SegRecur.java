package DataStructure;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SegRecur {
        String INPUT = "";
        int N = 1000005;
        int[] d = new int[N];
        Pair[] t = new Pair[4 * N];
        int[] a = new int[N];

        void build(int n, int s, int e) {
                if (s == e) {
                        t[n] = new Pair(a[s], a[s] == 1 ? 1 : 0);
                        return;
                }
                int m = (s + e) >> 1;
                build(2 * n, s, m);
                build(2 * n + 1, m + 1, e);
                t[n] = new Pair(t[2 * n].a + t[2 * n + 1].a, 0);
        }

        void update(int n, int s, int e, int l, int r) {
                if (l > e || r < s)
                        return;
                if (s == e) {
                        int x = d[(int) t[n].a];
                        if (x == 2)
                                t[n].b = 1;
                        t[n].a = x;
                        return;
                }
                if (e - s + 1 == t[n].b)
                        return;
                int m = (s + e) >> 1;
                update(2 * n, s, m, l, r);
                update(2 * n + 1, m + 1, e, l, r);
                t[n].a = t[2 * n].a + t[2 * n + 1].a;
                t[n].b = t[2 * n].b + t[2 * n + 1].b;

        }

        long query(int n, int s, int e, int l, int r) {
                if (l > e || r < s)
                        return 0;
                if (l <= s && r >= e)
                        return t[n].a;
                int m = (s + e) >> 1;
                long p = query(2 * n, s, m, l, r);
                long q = query(2 * n + 1, m + 1, e, l, r);
                return p + q;
        }

        void solve() {
                preprocess();
                int n = i(), q = i();
                for (int i = 1; i <= n; i++) {
                        a[i] = i();
                }
                build(1, 1, n);
                while(q-->0) {
                        int type = i(), l = i(), r = i();
                        if (type == 2) {
                                out.println(query(1, 1, n, l, r));
                        } else {
                                update(1, 1, n, l, r);
                        }
                }

        }

        private void preprocess() {
                d[1] = 1;
                for (int i = 2; i < N; i++) {
                        d[i]++;
                        for (int j = i; j < N; j += i)
                                d[j]++;
                }
        }

        void run() {
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
        }

        static class Pair {
                long a, b;

                Pair(long a, long b) {
                        this.a = a;
                        this.b = b;
                }
        }
        public static void main(String[] args)throws Exception {
                new SegRecur().run();
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
