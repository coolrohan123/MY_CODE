package CFRound.CF38;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;

public class D {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "3 3\n" +
                "1 2 1\n" +
                "2 3 1\n" +
                "1 3 1\n" +
                "30 10 20\n";
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new D().run();
        }

        void solve() {
                final int n = i(), m = i();
                final ArrayList<Pair>[] al = new ArrayList[n];
                for (int i = 0; i < n; i++) {
                        al[i] = new ArrayList<>();
                }
                for (int i = 0; i < m; i++) {
                        final int l = i() - 1, r = i() - 1;
                        final long w = l();
                        al[l].add(new Pair(r, w));
                        al[r].add(new Pair(l, w));
                }
                long[] ans = new long[n];
                for (int i = 0; i < n; i++) {
                        ans[i] = l();
                }
                final PriorityQueue<Pair> pq = new PriorityQueue<>(new Comparator<Pair>() {
                        @Override
                        public int compare(Pair o1, Pair o2) {
                                return Long.compare(o1.c, o2.c);
                        }
                });
                for (int i = 0; i < n; i++) {
                        pq.add(new Pair(i, ans[i]));
                }
                while (!pq.isEmpty()) {
                        Pair x = pq.remove();
                        int i = x.t;
                        long c = x.c;
                        if (c != ans[i]) continue;
                        for (Pair p : al[i]) {
                                long temp = c + 2 * p.c;
                                if (temp < ans[p.t]) {
                                        ans[p.t] = temp;
                                        pq.add(new Pair(p.t, ans[p.t]));
                                }
                        }
                }
                Arrays.stream(ans).forEach(e -> out.print(e + " "));

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

        static class Pair {
                int t;
                long c;

                public Pair(int t, long c) {
                        this.t = t;
                        this.c = c;
                }
        }

}
