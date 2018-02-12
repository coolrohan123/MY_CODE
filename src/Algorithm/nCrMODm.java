package Algorithm;

import java.util.ArrayList;

public class nCrMODm {
        static long d, x, y;

        public static void main(String[] args) {
                System.out.println(NCRMODM.solve(10, 4, 17));
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
