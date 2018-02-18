package CFRound.CF38;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class CA {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "1 48";
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new CA().run();
        }

        void solve() {
                long x = l();
                if (x == 0) {
                        out.println(1 + " " + 1);
                        return;
                }
                // solve n^2-l^2=x
                for (long y = 1; y * y <= x; y++) {
                        if (x % y != 0) continue;
                        long a = y;
                        long b = x / y;
                        //(a-b)(a+b)=x
                        if ((a - b) % 2 != 0) continue;
                        long n = a - (a - b) / 2;
                        if (n * n == x) continue; // l = 0 case
                        long m = (long) Math.sqrt(n * n / (n * n - x));
                        if (x == n * n - (n / m) * (n / m)) {
                                out.println(n + " " + m);
                                return;
                        }
                }
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
