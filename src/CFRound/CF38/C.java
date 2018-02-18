package CFRound.CF38;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class C {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "1\n" +
                "999808096\n\n" +
                "42\n" +
                "43\n" +
                "44\n" +
                "45\n" +
                "46\n" +
                "47\n" +
                "48\n" +
                "49\n" +
                "50";
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new C().run();
        }

        void solve() {
                long x = l();
                if (x == 1 || x == 5 || x == 2) {
                        out.println(-1);
                        return;
                }
                if (x == 0) {
                        out.println(1 + " " + 1);
                        return;
                }
                long n = find(x);
                if (x == n * n - 1) {
                        out.println(n + " " + n);
                        return;
                }
                for (int i = (int) n; i < 37000; i++) {
                        long m_high = (i >> 1) + 1;
                        long m_low = 2;
                        long ans = -1;
                        while (m_high >= m_low) {
                                long m = (m_high + m_low) >> 1;
                                long n_zero = (i / m) * (i / m);
                                if (i * i - n_zero == x) {
                                        ans = m;
                                        break;
                                }
                                if (i * i - n_zero > x) {
                                        m_high = m - 1;
                                } else {
                                        m_low = m + 1;
                                }
                        }
                        if (ans != -1) {
                                out.println(i + " " + ans);
                                return;
                        }
                }
                out.println("-1");
        }

        long find(long x) {
                int y = (int) Math.sqrt(x);
                if (y * y == x) {
                        return y;
                }
                return y + 1;
        }

        void run() {
                is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                int t = i();
                while (t-- > 0)
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

}

