package ROUND460;
import java.util.*;
import java.io.*;

public class D {
        String INPUT = "3 2 aba 1 3 2 3";
        char[] a;
        boolean[] vis;
        int[] freq = new int[26];
        int ans = Integer.MIN_VALUE;
        void solve()
        {
                int n = i(), m = i();
                a = s().toCharArray();
                int[] from = new int[m];
                int[] to   = new int[m];
                vis        = new boolean[n];

                for (int i = 0; i <m; i++) {
                        from[i] = i()-1;
                        to[i]   = i()-1;
                }
                int[][] graph = DirectedGraph(n,from,to,m);
                int[] topSort;
                if((topSort=sortTopologically(graph))==null)
                {
                        out.println(-1);
                        return;
                }
                for(char c='a';c<='z';c++)
                {
                        int[] dp = new int[n];
                        for (int i: topSort) {
                                if(a[i]==c)
                                        dp[i]++;
                                ans = Math.max(ans,dp[i]);
                                for(int e: graph[i])
                                        dp[e] = Math.max(dp[e],dp[i]);
                        }
                }
                out.println(ans);
        }
        private static int[] sortTopologically(int[][] g)
        {
                int n = g.length;
                int[] ec = new int[n];
                //ec stores in degree of every vertex
                for(int i = 0; i < n; i++){
                        for(int to : g[i])ec[to]++;
                }

                int[] ret = new int[n];
                int q = 0;
                // sources
                for(int i = 0;i < n;i++){
                        if(ec[i] == 0)ret[q++] = i;
                }

                for(int p = 0;p < q;p++){
                        for(int to : g[ret[p]]){
                                if(--ec[to] == 0)ret[q++] = to;
                        }
                }
                // loop
                for(int i = 0;i < n;i++){
                        if(ec[i] > 0)return null;
                }
                return ret;
        }
        private static int[][] DirectedGraph(int n, int[] from, int[] to, int m)
        {
                int[][] g = new int[n][];
                int[] p = new int[n];
                for(int i = 0;i < m;i++)p[from[i]]++;
                for(int i = 0;i < n;i++)g[i] = new int[p[i]];
                for(int i = 0;i < m;i++){
                        g[from[i]][--p[from[i]]] = to[i];
                }
                return g;
        }
        void run() throws Exception{
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
        }
        public static void main(String[] args)throws Exception {
                new D().run();
        }
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        public int lenbuf = 0, ptrbuf = 0;

        private int readByte()
        {
                if(lenbuf == -1)throw new InputMismatchException();
                if(ptrbuf >= lenbuf){
                        ptrbuf = 0;
                        try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
                        if(lenbuf <= 0)return -1;
                }
                return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
        private int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }

        private double d() { return Double.parseDouble(s()); }
        private char c() { return (char)skip(); }

        private String s()
        {
                int b = skip();
                StringBuilder sb = new StringBuilder();
                while(!(isSpaceChar(b))){ // when nextLine, (isSpaceChar(b) && b != ' ')
                        sb.appendCodePoint(b);
                        b = readByte();
                }
                return sb.toString();
        }

        private char[] sa(int n)
        {
                char[] buf = new char[n];
                int b = skip(), p = 0;
                while(p < n && !(isSpaceChar(b))){
                        buf[p++] = (char)b;
                        b = readByte();
                }
                return n == p ? buf : Arrays.copyOf(buf, p);
        }

        private char[][] nm(int n, int m)
        {
                char[][] map = new char[n][];
                for(int i = 0;i < n;i++)map[i] = sa(m);
                return map;
        }

        private int[] ia(int n)
        {
                int[] a = new int[n];
                for(int i = 0;i < n;i++)a[i] = i();
                return a;
        }

        private int i()
        {
                int num = 0, b;
                boolean minus = false;
                while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
                if(b == '-'){
                        minus = true;
                        b = readByte();
                }

                while(true){
                        if(b >= '0' && b <= '9'){
                                num = num * 10 + (b - '0');
                        }else{
                                return minus ? -num : num;
                        }
                        b = readByte();
                }
        }

        private long l()
        {
                long num = 0;
                int b;
                boolean minus = false;
                while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
                if(b == '-'){
                        minus = true;
                        b = readByte();
                }

                while(true){
                        if(b >= '0' && b <= '9'){
                                num = num * 10 + (b - '0');
                        }else{
                                return minus ? -num : num;
                        }
                        b = readByte();
                }
        }

        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }

}
