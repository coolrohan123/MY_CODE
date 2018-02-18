package CFRound.Round464;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class E {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "6\n" +
                "1 3\n" +
                "2\n" +
                "1 4\n" +
                "2\n" +
                "1 8\n" +
                "2\n";
        int N = (int) 5e5 + 5;
        long[] a = new long[N];
        long[] pref = new long[N];
        int ind;
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new E().run();
        }

        void solve() {
                ind = 0;
                double ans = 0.0;
                for (int T = i(); T > 0; T--) {
                        int type = i();
                        if (type == 1) {
                                long x = l();
                                if (ind == 0) {
                                        a[ind] = x;
                                        pref[ind + 1] = x;
                                } else {
                                        a[ind] = x;
                                        pref[ind + 1] = pref[ind] + x;
                                }
                                ind++;
                                ans = Math.max(ans, a[ind - 1] - ternarySearch(1, ind));
                        } else {
                                out.printf("%.10f\n", ans);

                        }
                }
        }

        double f(long x) {
                return (double) (pref[(int) x] + a[ind - 1]) / (x + 1);
        }

        double ternarySearch(long l, long h) {
                long min = l, max = h;
                for (int i = 0; i < 20; i++) {
                        long l1 = (2 * l + h) / 3;
                        long l2 = (2 * h + l) / 3;
                        double vl1 = f(l1);
                        double vl2 = f(l2);
                        if (vl1 < vl2) {
                                h = l2;
                        } else {
                                l = l1;
                        }
                }
                double m = Double.MAX_VALUE;
                for (long i = Math.max(min, l - 1); i < Math.min(h + 1, max); i++) {
                        m = Math.min(m, f(i));
                }
                return m;
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
