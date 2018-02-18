package CFRound.CF38;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class B {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "2 2 999995";
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;

        public static void main(String[] args) throws Exception {
                new B().run();
        }

        void solve() {
                int n = i();
                int[] a = ia(n);
                Merge.sort(a);
                int mid = (1 + 1000000) >> 1;
                long ans = 0;
                long ans1 = 0;
                int start = 1;
                for (int i = 0; i < a.length; i++) {
                        if (a[i] > mid) {
                                start = a[i];
                                break;
                        }
                }
                for (int i = 0; i < a.length; i++) {
                        if (a[i] <= mid) {
                                ans = Math.max(ans, a[i] - 1);
                        } else {
                                ans1 = Math.max(ans1, 1000000 - start);
                        }
                }
                out.print(Math.max(ans, ans1));

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

        static class Merge {

                public static void sort(int inputArr[]) {
                        int length = inputArr.length;
                        doMergeSort(inputArr, 0, length - 1);
                }

                private static void doMergeSort(int[] arr, int lowerIndex, int higherIndex) {
                        if (lowerIndex < higherIndex) {
                                int middle = lowerIndex + (higherIndex - lowerIndex) / 2;
                                doMergeSort(arr, lowerIndex, middle);
                                doMergeSort(arr, middle + 1, higherIndex);
                                mergeParts(arr, lowerIndex, middle, higherIndex);
                        }
                }

                private static void mergeParts(int[] array, int lowerIndex, int middle, int higherIndex) {
                        int[] temp = new int[higherIndex - lowerIndex + 1];
                        for (int i = lowerIndex; i <= higherIndex; i++) {
                                temp[i - lowerIndex] = array[i];
                        }
                        int i = lowerIndex;
                        int j = middle + 1;
                        int k = lowerIndex;
                        while (i <= middle && j <= higherIndex) {
                                if (temp[i - lowerIndex] < temp[j - lowerIndex]) {
                                        array[k] = temp[i - lowerIndex];
                                        i++;
                                } else {
                                        array[k] = temp[j - lowerIndex];
                                        j++;
                                }
                                k++;
                        }
                        while (i <= middle) {
                                array[k] = temp[i - lowerIndex];
                                k++;
                                i++;
                        }
                        while (j <= higherIndex) {
                                array[k] = temp[j - lowerIndex];
                                k++;
                                j++;
                        }
                }
        }

}
