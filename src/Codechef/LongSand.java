package Codechef;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.StringTokenizer;

class Sandwitch {
        static long d, x, y;
        static int res;
        static long mark[] = new long[111111];
        static int maxn = 111111;
        static long p[] = new long[111111];
        static long cnt[] = new long[111111];
        static long arr[];
        static long fac[], ifac[], DR[] = new long[101];
        static int MOD = (int) (1e9 + 7);
        static boolean[] isPrime;

        public static void main(String args[]) throws NumberFormatException {

                InputReader in = new InputReader(System.in);
                PrintWriter out = new PrintWriter(new OutputStreamWriter(System.out));

                //int t = in.nextInt();
                get_prime();
                for (int i = 0; i < 1; i++) {

//                        long n = in.nextLong();
//                        long k = in.nextLong();
                        //MOD = in.nextInt();

//                        if (n % k == 0) {
//                                out.println(n / k + " " + 1);
//                                continue;
//                        }
                        out.println(solve(10, 4, 17));
//                        long den = k - n % k + n / k;
//                        long r = n / k;
//                        long ans = 0;
//                        // System.out.println(den + " " + r);
//                        ans = solve(den, r, MOD);
//                        out.println((n / k + 1) + " " + ans);

                }
                out.close();

        }

        static void get_prime() {
                res = 0;

                for (int i = 2; i < maxn; i++)
                        if (mark[i] == 0) {
                                p[res++] = i;
                                for (int j = 2 * i; j < maxn; j += i)
                                        mark[j] = 1;
                        }
        }

        static void deal(long x, int sign) {
                for (int i = 0; i < res; i++) {
                        long temp = x;
                        while (temp != 0) {
                                cnt[i] += sign * (temp / p[i]);
                                temp /= p[i];
                        }
                }
        }

        static long C(long l, long m, long n) {
                if (l == 0 && m == 0) {
                        return 1;
                }
                if (l == 0) {
                        return 0;
                }
                if (m == 0) {
                        return 1;
                }
                if (l < m) {
                        return 0;
                }
                Arrays.fill(cnt, 0);
                deal(l, 1);
                deal(m, -1);
                deal(l - m, -1);
                long ans = 1;
                for (int i = 0; i < res; i++) {
                        if (cnt[i] == 0)
                                continue;
                        // System.out.println(p[i] + " " + cnt[i]);
                        ans = ans * power(p[i] % n, cnt[i], n) % n;
                        ans = ans % n;
                }

                return ans;
        }

        static long countFact(long n, long p) {
                long k = 0;
                while (n >= p) {
                        k += n / p;
                        n /= p;
                }
                return k;
        }

        static long phi(long n) {
                long res = n;
                for (int i = 2; i * i <= n; ++i) {
                        if (n % i == 0) {
                                while (n % i == 0) {
                                        n /= i;
                                }
                                res -= res / i;
                        }
                }
                if (n != 1) {
                        res -= res / n;
                }
                return res;
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

                // System.out.println(aaa);

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

                long pp[] = new long[primes.size()];
                long rr[] = new long[primes.size()];

                for (int j = 0; j < primes.size(); j++) {
                        pp[j] = (long) Math.pow(primes.get(j), primesp.get(j));
                        rr[j] = resi.get(j);
                }
                return applyCRT(primes, primesp, resi, N, R, mm);

                // return crt(pp, rr);

        }

        public static long crt(long[] divs, long[] mods) {
                long div = divs[0];
                long mod = mods[0];
                for (int i = 1; i < divs.length; i++) {
                        long[] apr = exGCD(div, divs[i]);
                        if ((mods[i] - mod) % apr[0] != 0)
                                return -1;
                        long ndiv = div * divs[i] / apr[0];
                        long nmod = (apr[1] * (mods[i] - mod) / apr[0] * div + mod) % ndiv;
                        if (nmod < 0)
                                nmod += ndiv;
                        div = ndiv;
                        mod = nmod;
                }
                return mod;
        }

        public static long[] exGCD(long a, long b) {
                if (a == 0 || b == 0)
                        return null;
                int as = Long.signum(a);
                int bs = Long.signum(b);
                a = Math.abs(a);
                b = Math.abs(b);
                long p = 1, q = 0, r = 0, s = 1;
                while (b > 0) {
                        long c = a / b;
                        long d;
                        d = a;
                        a = b;
                        b = d % b;
                        d = p;
                        p = q;
                        q = d - c * q;
                        d = r;
                        r = s;
                        s = d - c * s;
                }
                return new long[]{a, p * as, r * bs};
        }

        // function uses find_ncr methods and applies CRT

        private static long CRT(ArrayList<Long> nums, ArrayList<Long> arr) {
                long prodall = 1;
                for (long j : nums)
                        prodall *= j;

                long ret = 0;
                for (int i = 0; i < nums.size(); i++) {
                        long ni = nums.get(i), ai = arr.get(i);
                        ret = (ret + ai * prodall / ni % prodall * inv(prodall / ni % ni, ni)) % prodall;
                }
                return ret;
        }

        public static long inv(long N, long M) {
                long x = 0, lastx = 1, y = 1, lasty = 0, q, t, a = N, b = M;
                while (b != 0) {
                        q = a / b;
                        t = a % b;
                        a = b;
                        b = t;
                        t = x;
                        x = lastx - q * x;
                        lastx = t;
                        t = y;
                        y = lasty - q * y;
                        lasty = t;
                }
                return (lastx + M) % M;
        }

        public static long[] exgcd(long a, long b) {
                if (a == 0 || b == 0)
                        return null;
                int as = Long.signum(a);
                int bs = Long.signum(b);
                a = Math.abs(a);
                b = Math.abs(b);
                long p = 1, q = 0, r = 0, s = 1;
                while (b > 0) {
                        long c = a / b;
                        long d;
                        d = a;
                        a = b;
                        b = d % b;
                        d = p;
                        p = q;
                        q = d - c * q;
                        d = r;
                        r = s;
                        s = d - c * s;
                }
                return new long[]{a, p * as, r * bs};
        }

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

        static long modpowIter(long a, long b) {
                long ans = 1;

                // c=(int)(1e9+7);
                while (b != 0) {
                        if (b % 2 == 1)
                                ans = (ans * a) % MOD;

                        a = (a * a) % MOD;
                        b /= 2;

                }
                return ans % MOD;
        }

        static void precompute() {
                int i;
                int N = MOD;
                fac = new long[N];
                ifac = new long[N];
                fac[0] = 1;
                for (i = 1; i < N; i++) {
                        fac[i] = (i * fac[i - 1]) % MOD;
                }
                ifac[N - 1] = mInv2(fac[N - 1], MOD);
                for (i = N - 2; i >= 0; i--) {
                        ifac[i] = ((i + 1) * ifac[i + 1]) % MOD;

                }
        }

        public static long combine(long num, long den, int p) {
                if (num == 0 || den == 0) {
                        return 1;
                }
                if (den > num) {
                        return 1;
                }

                if (den > num - den) {
                        den = num - den;
                }
                long result = 1;
                for (int i = 0; i < den; i++) {
                        result *= (num - i);
                        result /= (i + 1);
                }
                return result % p;
        }

        static long mC(int n, int k) {

                if (k > n) {
                        return 1;
                }
                long an; // n * (n-1) * ... * (n-k+1)

                an = fac[n] % MOD;
                an *= ifac[n - k];
                an %= MOD;
                an *= ifac[k];
                an %= MOD;

                // numerator / denominator mod p.
                return an;
        }

        static int gcd(int num1, int num2) {
                if (num1 > num2) {
                        int temp = num1;
                        num1 = num2;
                        num2 = temp;
                }
                while (num1 != 0) {
                        int temp = num1;
                        num1 = num2 % num1;
                        num2 = temp;
                }
                return num2;
        }

        static void extendedEuclid(long A, long B) {
                if (B == 0) {
                        d = A;
                        x = 1;
                        y = 0;
                } else {
                        extendedEuclid(B, A % B);
                        long temp = x;
                        y = temp - (A / B) * y;
                }
        }

        // if(gcd(A,M)==1)
        static long mInv2(long A, int M) {
                extendedEuclid(A, M);
                return (x % M + M) % M;
        }

        static void sieve() {

                isPrime = new boolean[1000001];
                for (int i = 0; i <= 1000000; ++i) {
                        isPrime[i] = true;
                }
                isPrime[0] = false;
                isPrime[1] = false;
                for (int i = 2; i * i <= 1000000; ++i) {
                        if (isPrime[i] == true) {
                                for (int j = i * i; j <= 1000000; j += i)
                                        isPrime[j] = false;
                        }
                }
        }

        public static class InputReader {
                public BufferedReader reader;
                public StringTokenizer tokenizer;

                public InputReader(InputStream inputstream) {
                        reader = new BufferedReader(new InputStreamReader(inputstream));
                        tokenizer = null;
                }

                public String nextLine() {
                        String fullLine = null;
                        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                                try {
                                        fullLine = reader.readLine();
                                } catch (IOException e) {
                                        throw new RuntimeException(e);
                                }
                                return fullLine;
                        }
                        return fullLine;
                }

                public String next() {
                        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
                                try {
                                        tokenizer = new StringTokenizer(reader.readLine());
                                } catch (IOException e) {
                                        throw new RuntimeException(e);
                                }
                        }
                        return tokenizer.nextToken();
                }

                public long nextLong() {
                        return Long.parseLong(next());
                }

                public int nextInt() {
                        return Integer.parseInt(next());
                }
        }

}
