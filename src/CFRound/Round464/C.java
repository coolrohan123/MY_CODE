package CFRound.Round464;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class C {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "3\n" +
                "1 2 3\n" +
                "1 3\n";
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new C().run();
        }

        void solve() {
                int n = i();
                int[] a = new int[n + 1];
                for (int i = 1; i <= n; i++) {
                        a[i] = i();
                }
                int s = i(), f = i();
                for (int i = 1; i <= n; i++) {
                        a[i] += a[i - 1];
                }
                int range = f - s;
                long max = Long.MIN_VALUE;
                int ind = Integer.MIN_VALUE;
                int prev = a[range] - a[0];
                long ans = Long.MAX_VALUE;
                for (int i = 0; i <= n; i++) {
                        long temp = 0;
                        if (i + range <= n) {
                                temp = a[i + range] - a[i];
                        } else {
                                temp = (a[n] - a[i]) + (a[range - (n - i)] - a[0]);
                        }
                        if (max <= (temp)) {
                                max = temp;
                        }
                }
                for (int i = 0; i <= n; i++) {
                        long temp = 0;
                        if (i + range <= n) {
                                temp = a[i + range] - a[i];
                        } else {
                                temp = (a[n] - a[i]) + (a[range - (n - i)] - a[0]);
                        }
                        if (max == (temp)) {
                                ind = i + 1;
                                ind = (n - ind + s);
                                ind = (ind + 1) % n == 0 ? n : (ind + 1) % n;
                                ans = Math.min(ans, ind);
                        }
                }
                if (max == prev) {
                        out.println(1);
                        return;
                }
                out.println(ans);
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

        private void tr(Object... o) {
                if (!oj) System.out.println(Arrays.deepToString(o));
        }

}