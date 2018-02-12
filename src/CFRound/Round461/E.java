package CFRound.Round461;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class E {
        public int lenbuf = 0, ptrbuf = 0;
        String INPUT = "2 12 0 4\n" +
                "3 4\n" +
                "4 2";
        int N = 1003, M = 10003;
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];

        public static void main(String[] args) throws Exception {
                new E().run();
        }

        void solve() {
                long[][] dp = new long[N][M];
                for (long[] a : dp)
                        Arrays.fill(a, -1);
                int n = i();
                long w = l(), b = l(), x = l();
                int[] c = new int[n];
                for (int i = 0; i < n; i++) {
                        c[i] = i();
                }
                long[] cost = new long[n];
                for (int i = 0; i < n; i++) {
                        cost[i] = l();
                }
                long ans = -1;
                dp[0][0] = w;
                for (int i = 0; i < n; i++) {
                        for (int j = 0; j < M; j++) {
                                if (dp[i][j] != -1) {
                                        long max = w + j * b;
                                        long cur = dp[i][j];
                                        for (int k = 0; k <= c[i]; k++) {
                                                if ((long) k * cost[i] > cur)
                                                        break;
                                                long newMax = max + k * b;
                                                long newMana = cur - (long) k * cost[i];
                                                dp[i + 1][j + k] = max(dp[i + 1][j + k], min(newMax, newMana + x));
                                        }
                                }
                        }
                }
                for (int i = 0; i < M; i++) {
                        if (dp[n][i] != -1)
                                ans = i;
                }
                out.println(ans);


        }

        long max(long a, long b) {
                if (a > b) return a;
                return b;
        }

        long min(long a, long b) {
                if (a > b) return b;
                return a;
        }

        void run() {
                is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                solve();
                out.flush();
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

}
