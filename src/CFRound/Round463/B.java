package CFRound.Round463;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class B {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "4\n" +
                "82 94 6\n" +
                "56 67 4\n" +
                "28 59 9\n" +
                "39 74 4";
        int N = (int) 1e6 + 2;
        int[] a = new int[N];
        int[][] dp = new int[N][10];
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new B().run();
        }

        void solve() {
                preprocess();
                //out.print(a[4]);
                for (int i = 0; i < 10; i++) {
                        for (int j = 1; j < N; j++) {
                                if (a[j] == i) {
                                        dp[j][i] = dp[j - 1][i] + 1;
                                } else {
                                        dp[j][i] = dp[j - 1][i];
                                }
                        }
                }
                //out.print(dp[20][4]);
                int q = i();
                while (q-- > 0) {
                        final int l = i(), r = i(), k = i();
                        out.println(dp[r][k] - dp[l - 1][k]);
                }

        }

        void preprocess() {
                for (int i = 0; i < N; i++) {
                        if (i < 10) {
                                a[i] = i;
                        } else {
                                a[i] = calculate(i);
                        }
                }
        }

        int calculate(int i) {
                if (i < 10) {
                        return i;
                }
                int ans = calculate(val(String.valueOf(i)));
                return ans;
        }

        int val(String x) {
                int ans = 1;
                for (int i = 0; i < x.length(); i++) {
                        if (x.charAt(i) == '0') continue;
                        ans = ans * (x.charAt(i) - '0');
                }
                return ans;
        }

        void run() {
                is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
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

}
