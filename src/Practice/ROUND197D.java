package Practice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class ROUND197D {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "1 1\n" +
                "1 1 \n" +
                "1 1\n" +
                "3 4\n" +
                "1 2\n" +
                "1 2\n";
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) {
                new ROUND197D().run();
        }

        void solve() {
                int n = i();
                int q = i();
                int[] a = ia(1 << n);
                SegmentTree sg = new SegmentTree(1 << n, a, n);
                sg.build(1, 1, 1 << n, 0);
                while (q-- > 0) {
                        int ind = i(), val = i();
                        sg.update(1, 1, 1 << n, ind, val, 0);
                        out.println(sg.t[1]);
                }
        }

        void run() {
                is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                solve();
                out.flush();
                tr(System.currentTimeMillis() - s + "ms");
        }

        private int readByte() {
                if (lenbuf == -1) throw new InputMismatchException();
                if (ptrbuf >= lenbuf) {
                        ptrbuf = 0;
                        try {
                                lenbuf = is.read(inbuf);
                        } catch (IOException e) {
                                throw new InputMismatchException();
                        }
                        if (lenbuf <= 0) return -1;
                }
                return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) {
                return !(c >= 33 && c <= 126);
        }

        private int skip() {
                int b;
                while ((b = readByte()) != -1 && isSpaceChar(b)) ;
                return b;
        }

        private double d() {
                return Double.parseDouble(s());
        }

        private char c() {
                return (char) skip();
        }

        private String s() {
                int b = skip();
                StringBuilder sb = new StringBuilder();
                while (!(isSpaceChar(b))) { // when nextLine, (isSpaceChar(b) && b != ' ')
                        sb.appendCodePoint(b);
                        b = readByte();
                }
                return sb.toString();
        }

        private char[] sa(int n) {
                char[] buf = new char[n];
                int b = skip(), p = 0;
                while (p < n && !(isSpaceChar(b))) {
                        buf[p++] = (char) b;
                        b = readByte();
                }
                return n == p ? buf : Arrays.copyOf(buf, p);
        }

        private char[][] nm(int n, int m) {
                char[][] map = new char[n][];
                for (int i = 0; i < n; i++) map[i] = sa(m);
                return map;
        }

        private int[] ia(int n) {
                int[] a = new int[n];
                for (int i = 0; i < n; i++) a[i] = i();
                return a;
        }

        private int i() {
                int num = 0, b;
                boolean minus = false;
                while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
                if (b == '-') {
                        minus = true;
                        b = readByte();
                }

                while (true) {
                        if (b >= '0' && b <= '9') {
                                num = num * 10 + (b - '0');
                        } else {
                                return minus ? -num : num;
                        }
                        b = readByte();
                }
        }

        private long l() {
                long num = 0;
                int b;
                boolean minus = false;
                while ((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-')) ;
                if (b == '-') {
                        minus = true;
                        b = readByte();
                }

                while (true) {
                        if (b >= '0' && b <= '9') {
                                num = num * 10 + (b - '0');
                        } else {
                                return minus ? -num : num;
                        }
                        b = readByte();
                }
        }

        private void tr(Object... o) {
                if (!oj) System.out.println(Arrays.deepToString(o));
        }

        static class SegmentTree {
                int[] t, a;
                int ind;

                SegmentTree(int n, int[] a, int ind) {
                        this.t = new int[4 * n + 1];
                        this.a = new int[n + 1];
                        this.ind = ind;
                        System.arraycopy(a, 0, this.a, 1, n);
                }

                void update(int n, int s, int e, int i, int val, int in) {
                        if (i < s || i > e) return;
                        if (s == e) {
                                t[n] = val;
                                return;
                        }
                        int m = (s + e) >> 1;
                        update(2 * n, s, m, i, val, in + 1);
                        update(2 * n + 1, m + 1, e, i, val, in + 1);
                        if ((ind - in) % 2 == 1) t[n] = t[2 * n] | t[2 * n + 1];
                        else t[n] = t[2 * n] ^ t[2 * n + 1];
                }

                void build(int n, int s, int e, int in) {
                        if (s == e) {
                                t[n] = a[s];
                                return;
                        }
                        int m = (s + e) >> 1;
                        build(2 * n, s, m, in + 1);
                        build(2 * n + 1, m + 1, e, in + 1);
                        if ((ind - in) % 2 == 1) t[n] = t[2 * n] | t[2 * n + 1];
                        else t[n] = t[2 * n] ^ t[2 * n + 1];
                }
        }

}
