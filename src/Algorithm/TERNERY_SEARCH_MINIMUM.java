package Algorithm;

public class TERNERY_SEARCH_MINIMUM {
        //used it to solve problem E in round 464
        double f(long x) {
                return 0.0;///write your function here.
        }

        double ternarySearch(long l, long h) {
                long ll = l, hh = h;
                for (int i = 0; i < 50; i++) {
                        long l1 = (2 * l + h) / 3;
                        long l2 = (2 * h + l) / 3;
                        double vl1 = f(l1);
                        double vl2 = f(l2);
                        if (vl1 < vl2) {
                                h = l2;
                        } else {
                                l = l1;
                        }
                }
                double min = Double.POSITIVE_INFINITY;
                for (long i = Math.max(ll, l - 2); i < Math.min(h + 3, hh); i++) {
                        min = Math.min(min, f(i));
                }
                return min;
        }
}
