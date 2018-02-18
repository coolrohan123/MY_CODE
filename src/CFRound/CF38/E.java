package CFRound.CF38;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class E {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "6 1 2 3 3 4 4";
        long MOD = (long) 1e9 + 7;
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static long inv(long a, long mod) {
                long b = mod;
                long p = 1, q = 0;
                while (b > 0) {
                        long c = a / b;
                        long d;
                        d = a;
                        a = b;
                        b = d % b;
                        d = p;
                        p = q;
                        q = d - c * q;
                }
                return p < 0 ? p + mod : p;
        }

        public static void main(String[] args) throws Exception {
                new E().run();
        }

        void solve() {
                int n = i();
                long[] a = new long[n];
                for (int i = 0; i < n; i++) {
                        a[i] = l();
                }
                Arrays.sort(a);
                long nfact = 1;
                for (int i = 1; i <= n; i++) {
                        nfact = nfact * i % MOD;
                }
                long ans = 0;
                for (int i = 0; i < n && a[i] != a[n - 1]; ) {
                        int j = i;
                        while (j < n && a[i] == a[j]) j++;
                        ans = (ans + inv(n - i, MOD) * (j - i) % MOD * a[i] % MOD) % MOD;
                        i = j;
                }
                out.println(ans * nfact % MOD);
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
