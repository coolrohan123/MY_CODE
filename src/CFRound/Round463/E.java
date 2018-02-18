package CFRound.Round463;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.InputMismatchException;

public class E {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "3 4";
        int MOD = 1000000000 + 7;
        int n, k;
        long[][] dp = new long[5003][5003];
        long d, x, y;
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new E().run();
        }

        void solve() {
                n = i();
                k = i();
                for (long[] a : dp) {
                        Arrays.fill(a, -1);
                }
                long ans = doDP(0, 0, n);
                out.println(dp[0][0]);
        }

        long doDP(int K, int a, int N) {
                if (dp[K][a] >= 0) return dp[K][a];
                int b = N - a;
                if (K == k) {
                        dp[K][a] = modPow(2, b, MOD);
                        return dp[K][a] % MOD;
                }
                long x = a > 0 ? a * doDP(K + 1, a, N) : 0;
                long y = b > 0 ? b * doDP(K + 1, a + 1, N) : 0;
                return dp[K][a] = (x + y) % MOD;
        }

        private long modPow(long base, long exp, long mod) {
                long res = 1L;
                while (exp > 0) {
                        if (exp % 2 == 1)
                                res = (res * base) % mod;
                        base = (base * base) % mod;
                        exp >>= 1;
                }
                return res;
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

        static class NCRMODM {
                static int res;
                static long arr[];

                static long countFact(long n, long p) {
                        long k = 0;
                        while (n >= p) {
                                k += n / p;
                                n /= p;
                        }
                        return k;
                }

                static long f(long n, long md, long p) {

                        return power(arr[(int) md], (n / md), md) * arr[(int) (n % md)];

                }

                static long F(long n, long md, long p) {
                        long i = 1;
                        long ret = 1;
                        while (i <= n) {
                                ret = (ret * f(n / i, md, p) % md) % md;
                                i = p * i;
                        }
                        return ret;

                }

                static long find_f(long n, long r, long p, long d) {
                        long md = 1; // p^d

                        for (long j = 0; j < d; ++j) {
                                md *= p;
                        }

                        long b = countFact(n, p) - countFact(r, p) - countFact(n - r, p);
                        if (b >= d)
                                return 0;
                        long a[] = new long[(int) md + 1];
                        a[0] = 1;
                        for (long j = 1; j <= md; ++j) {
                                if (j % p != 0) {
                                        a[(int) j] = a[(int) j - 1] * j;
                                } else {
                                        a[(int) j] = a[(int) j - 1];
                                }
                                a[(int) j] %= md;

                        }
                        arr = a;
                        long mod1 = F(n, md, p) % md;
                        long mod2 = F(r, md, p) % md;
                        mod2 = mul(mod2, F(n - r, md, p) % md, md);
                        long mp = md - md / p;
                        long aaa = ((power(p, b, md) * (mod1 * power(mod2, mp - 1, md))) % md) % md;
                        return aaa;
                }

                static long power(long a, long b, long mod) {
                        long res = 1;
                        if (b == 0)
                                return 1;
                        while (b > 0) {
                                if ((b & 1) != 0)
                                        res = mul(res, a, mod);
                                a = mul(a, a, mod);
                                b /= 2;
                        }
                        return res;
                }

                public static long mul(long a, long b, long m) {

                        return ((a % m) * (b % m)) % m;
                }

                public static long solve(long N, long R, int M) {
                        ArrayList<Long> primes = new ArrayList<Long>();
                        ArrayList<Long> primesp = new ArrayList<Long>();
                        ArrayList<Long> resi = new ArrayList<Long>();
                        int mm = M;
                        for (int i = 2; i * i <= M; i++) {
                                if (M % i == 0) {
                                        int j = 0;

                                        while (M % i == 0) {
                                                j++;
                                                M /= i;
                                        }

                                        primes.add((long) i);
                                        primesp.add((long) j);
                                        resi.add(find_f(N, R, i, j));

                                        // resi.add(lucas(N, R, j));
                                }
                        }
                        if (M > 1) {
                                primes.add((long) M);
                                primesp.add((long) 1);
                                resi.add(find_f(N, R, M, 1L));

                        }
                        return applyCRT(primes, primesp, resi, N, R, mm);

                        // return crt(pp, rr);

                }
                // function uses find_ncr methods and applies CRT

                static long applyCRT(ArrayList<Long> primes, ArrayList<Long> primesp, ArrayList<Long> resi, long nn, long rr,
                                     long m) {

                        // Apply Gauss algorithm
                        long res = 0;
                        for (long i = 0; i < (long) primes.size(); ++i) {
                                long cur = 1;
                                for (long j = 0; j < primesp.get((int) i); ++j)
                                        cur *= primes.get((int) i);
                                res = res + mul(mul(resi.get((int) i), m / cur, m),
                                        power((m / cur) % cur, cur - cur / primes.get((int) i) - 1, cur), m);

                                res %= m;
                        }

                        return res;
                }
        }

}
