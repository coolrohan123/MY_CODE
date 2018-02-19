package Practice;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.InputMismatchException;

public class SPOJ_SEGSQRS {
        public int lenbuf = 0, ptrbuf = 0;
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];

        public static void main(String[] args) {
                new SPOJ_SEGSQRS().run();
        }

        void solve() {
                int n = i(), q = i();
                int[] a = ia(n);
                SegmentTree sg = new SegmentTree(n, a);
                sg.build(1, 1, n);
                for (int i = 1; i <= q; i++) {

                        int type = i();
                        if (type == 2) {
                                int l = i(), r = i();
                                sg.count = 0;
                                sg.query(1, 1, n, l, r);
                                out.println(sg.count);
                        } else if (type == 1) {
                                int l = i(), r = i(), x = i();
                                sg.update(1, 1, n, l, r, x, type);
                        } else {
                                int l = i(), r = i(), x = i();
                                sg.update(1, 1, n, l, r, x, type);
                        }
                }
        }

        void run() {
                is = System.in;
                out = new PrintWriter(System.out);
                int t = i();
                for (int i = 1; i <= t; i++) {
                        out.println("Case " + i + ":");
                        solve();
                }

                out.flush();
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

        static class SegmentTree {
                long[] t;
                int[] a;
                long count;

                SegmentTree(int n, int[] a) {
                        this.t = new long[4 * n + 1];
                        this.a = new int[n + 1];
                        System.arraycopy(a, 0, this.a, 1, n);
                }

                void query(int n, int s, int e, int l, int r) {
                        if (l > e || r < s) return;
                        if (l <= s && r >= e) {//
                                count += t[n];
                                return;
                        }
                        if (s == e) { //
                                count += t[n];
                                return;
                        }
                        int m = (s + e) >> 1;
                        query(2 * n, s, m, l, r);
                        query(2 * n + 1, m + 1, e, l, r);
                }

                void update(int n, int s, int e, int l, int r, int val, int type) {// update in range without lazy
                        if (l > e || r < s) return;
                        if (s == e) {
                                if (type == 0) {
                                        a[s] = val;
                                        t[n] = a[s] * a[s];
                                } else if (type == 1) {
                                        a[s] += val;
                                        t[n] = a[s] * a[s];
                                }

                                return;
                        }
                        int m = (s + e) >> 1;
                        update(2 * n, s, m, l, r, val, type);
                        update(2 * n + 1, m + 1, e, l, r, val, type);
                        t[n] = t[2 * n] + t[2 * n + 1];

                }

                void build(int n, int s, int e) {
                        if (s == e) {
                                t[n] = a[s] * a[s];
                                return;
                        }
                        int m = (s + e) >> 1;
                        build(2 * n, s, m);
                        build(2 * n + 1, m + 1, e);
                        t[n] = t[2 * n] + t[2 * n + 1];
                }
        }
}
