package Practice;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class SegMentCodeCraftGCD {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "3\n" +
                "2 6 3\n" +
                "4\n" +
                "1 1 2 2\n" +
                "1 1 3 3\n" +
                "2 1 9\n" +
                "1 1 3 2\n";
        int N = (int) 5e5 + 3;
        int count, x;
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new SegMentCodeCraftGCD().run();
        }

        void solve() {
                int n = i();
                int[] a = new int[n];
                for (int i = 0; i < n; i++) {
                        a[i] = i();
                }
                SegmentTree sg = new SegmentTree(n, a);
                sg.build(1, 1, n);
                int q = i();
                while (q-- > 0) {
                        int type = i();
                        if (type == 2) {
                                int i = i(), val = i();
                                sg.update(1, 1, n, i, val);
                        } else {
                                sg.count = 0;
                                int l = i(), r = i();
                                x = i();
                                sg.query(1, 1, n, l, r, x);
                                if (sg.count > 1) out.println("NO");
                                else out.println("YES");
                        }
                }
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

        static class SegmentTree {
                int[] t;
                int[] a;
                int count;

                SegmentTree(int n, int[] a) {
                        this.t = new int[2 * n + 10];
                        this.a = new int[n + 1];
                        for (int i = 1; i <= n; i++) {
                                this.a[i] = a[i - 1];
                        }
                }

                void query(int n, int s, int e, int l, int r, int x) {
                        if (count > 1) return;
                        if (l > e || r < s) return;
                        if (s == e) { // a element
                                if (t[n] % x != 0) count++;
                                return;
                        }
                        if (l <= s && r >= e && t[n] % x == 0) {// l to r is completly inside
                                return;
                        }
                        int m = (s + e) >> 1;
                        query(2 * n, s, m, l, r, x);
                        query(2 * n + 1, m + 1, e, l, r, x);
                }

                void update(int n, int s, int e, int i, int val) {
                        if (i > e || i < s) return;
                        if (s == e) {
                                t[n] = val;
                                return;
                        }
                        int m = (s + e) >> 1;
                        update(2 * n, s, m, i, val);
                        update(2 * n + 1, m + 1, e, i, val);
                        t[n] = gcd(t[2 * n], t[2 * n + 1]);
                }

                void build(int n, int s, int e) {
                        if (s == e) {
                                t[n] = a[s];
                                return;
                        }
                        int m = (s + e) >> 1;
                        build(2 * n, s, m);
                        build(2 * n + 1, m + 1, e);
                        t[n] = gcd(t[2 * n], t[2 * n + 1]);
                }

                int gcd(int a, int b) {
                        return b == 0 ? a : gcd(b, a % b);
                }
        }

}
