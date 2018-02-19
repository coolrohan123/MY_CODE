package DataStructure;

public class SegRecur {
        static class SegmentTree {
                int[] t;
                int[] a;
                int count;

                SegmentTree(int n, int[] a) {
                        this.t = new int[4 * n + 1];
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
