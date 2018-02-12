package Codechef.FEBLONG;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class G {
        final long MOD = 1000000007;
        final private int[] sabfactor = new int[1002];
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "1\n" +
                "5\n" +
                "0 1 1 1 2\n" +
                "1 0 1 1 1\n" +
                "1 1 0 1 1\n" +
                "1 1 1 0 1\n" +
                "2 1 1 1 0";
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new G().run();
        }

        void solve() {
                final int n = i();
                final Pair[] actual = new Pair[n];
                for (int i = 1; i <= n; i++) {
                        final int[] a = new int[n - 1];
                        int ind = 0;
                        for (int j = 1; j <= n; j++) {
                                if (i == j) continue;
                                a[ind++] = sabfactor[gcd(i, j)];
                        }
                        Arrays.sort(a);
                        actual[i - 1] = new Pair(i - 1, Arrays.hashCode(a));
                }
                final Pair[] p = new Pair[n];
                int[][] val = new int[n][n];
                for (int i = 0; i < n; i++) {
                        final int[] a = new int[n - 1];
                        int ind = 0;
                        for (int j = 0; j < n; j++) {
                                int x = i();
                                val[i][j] = x;
                                if (x == 0) continue;
                                a[ind++] = x;
                        }
                        Arrays.sort(a);
                        p[i] = new Pair(i, Arrays.hashCode(a));
                }
                final int[] ans = new int[n];
                final boolean[] vis = new boolean[n];
                for (int i = 0; i < n; i++) {
                        Pair pair = p[i];
                        for (int j = 0; j < n; j++) {
                                if (!vis[j] && pair.hash == actual[j].hash) {
                                        ans[i] = actual[j].ind + 1;
                                        vis[j] = true;
                                        break;
                                }
                        }
                }
                for (int i = 0; i < n; i++) {
                        for (int j = 0; j < n; j++) {
                                if (i == j) continue;
                                if (val[i][j] != sabfactor[gcd(ans[i], ans[j])])
                                        throw new RuntimeException();

                        }
                }
                for (int i = 0; i < n; i++) {
                        out.println(ans[i]);
                }

        }

        int gcd(int a, int b) {
                return b == 0 ? a : gcd(b, a % b);
        }

        void preprocess() {
                for (int i = 1; i < 1001; i++) {
                        for (int j = i; j < 1001; j += i) {
                                sabfactor[j]++;
                        }
                }
        }

        void run() {
                is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                preprocess();
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

        static class Pair {
                int ind;
                long hash;

                public Pair(int ind, long hash) {
                        this.ind = ind;
                        this.hash = hash;
                }

                @Override
                public String toString() {
                        return "Pair{" +
                                "ind=" + ind +
                                ", hash=" + hash +
                                '}';
                }
        }

}
