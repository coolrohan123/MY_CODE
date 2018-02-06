package DataStructure;

import java.util.Arrays;

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
