package CFRound.Round464;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class D {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "2 aa cd";
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new D().run();
        }

        void solve() {
                int n = i();
                char[] a = s().toCharArray();
                char[] b = s().toCharArray();
                int[] first = new int[n];
                int[] sec = new int[n];
                for (int i = 0; i < n; i++) {
                        first[i] = a[i] - 'a';
                        sec[i] = b[i] - 'a';
                }
                DisjointSet d = new DisjointSet(n + 27);
                char[][] ans = new char[n][2];
                int ind = 0;
                for (int i = 0; i < n; i++) {
                        if (d.equal(first[i], sec[i])) {
                                continue;
                        }
                        ans[ind++] = new char[]{(char) (first[i] + 'a'), (char) (sec[i] + 'a')};
                        d.union(first[i], sec[i]);
                }
                out.println(ind);
                for (int i = 0; i < ind; i++) {
                        out.println(ans[i][0] + " " + ans[i][1]);
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

        public class DisjointSet {
                public int[] array;

                public DisjointSet(int n) {
                        array = new int[n];
                        Arrays.fill(array, -1);
                }

                public int root(int x) {
                        return array[x] < 0 ? x : (array[x] = root(array[x]));
                }

                public boolean equal(int x, int y) {
                        return root(x) == root(y);
                }

                public boolean union(int x, int y) {
                        x = root(x);
                        y = root(y);
                        if (x != y) {
                                if (array[y] < array[x]) {
                                        int d = x;
                                        x = y;
                                        y = d;
                                }
                                array[x] += array[y];
                                array[y] = x;
                        }
                        return x == y;
                }

                public int numOfDisjointSets() {
                        int ct = 0;
                        for (int u : array)
                                if (u < 0)
                                        ct++;
                        return ct;
                }
        }

}
