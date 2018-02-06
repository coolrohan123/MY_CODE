package Codes.Codeforces.ED37;
import java.util.*;
import java.io.*;
public class E {
        String INPUT = "5 5 1 2 3 4 3 2 4 2 2 5";
        Set<Integer> s1 = new HashSet<>();
        Set<Integer> s2 = new HashSet<>();
        void solve()
        {
                int n = i(),m = i();
                int[] from = new int[m];
                int[] to = new int[m];
                for (int i = 0; i < m; i++) {
                        from[i] = i()-1;
                        to[i] = i()-1;
                }
                int[][] g = arrayGraphU(from,to,n);
                for (int i = 0; i <n ; i++) {
                        s1.add(i);
                }
                int[] ans = new int[n];int k=0;
                for (int i = 0; i < n; i++) {
                        if(!s1.contains(i)) continue;
                        ans[k++] = bfs(i,g);
                }
                ans = Arrays.copyOf(ans,k);
                Arrays.sort(ans);
                out.println(k);
                Arrays.stream(ans).forEach(e->out.print(e+" "));
        }
        private int bfs(int i,int[][] g)
        {
                int ans = 1;
                s1.remove(i);
                Queue<Integer> q = new LinkedList<>();
                q.add(i);
                while(!q.isEmpty())
                {
                        int now = q.poll();
                        for(int x: g[now])
                        {
                                if(!s1.contains(x)) continue;
                                s1.remove(x);
                                s2.add(x);
                        }
                        for(int x: s1)
                        {
                                q.add(x);
                                ans++;
                        }
                        s1.clear();
                        s1.addAll(s2);
                        s2.clear();
                }
                return ans;
        }
        private int[][] arrayGraphU(int[] f, int[] t, int n) {
                int[][] graph = new int[n][];
                int[] p = new int[n];
                for (int from : f) {
                        p[from]++;
                }
                for (int to : t) {
                        p[to]++;
                }
                for (int i = 0; i < n; i++) {
                        graph[i] = new int[p[i]];
                }
                for (int i = 0; i < f.length; i++) {
                        graph[f[i]][--p[f[i]]] = t[i];
                        graph[t[i]][--p[t[i]]] = f[i];
                }
                return graph;

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
                new E().run();
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
