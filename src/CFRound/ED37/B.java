package CFRound.ED37;


import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.PriorityQueue;
public class B {
        String INPUT = "1 3 4 5 4 4 6 6";
        void solve()
        {
                int n = i();
                PriorityQueue<Pair> pq = new PriorityQueue<>();
                Pair[] a = new Pair[n];
                int time = 1000000;
                for (int i = 1; i <= n; i++) {
                        int x = i(), y = i();
                        time = Math.min(x,time);
                        a[i-1] = new Pair(i,x,y);
                }
                int ind = 0;
                int[] ans = new int[n+1];
                int q = 0,first = 0;
                for(int i = 1; i<5001; i++)
                {
                        if(ind<n && a[ind].b!=i)
                                continue;
                        while(ind<n && a[ind].b==i){
                                pq.add(a[ind]);
                                ind++;
                        }
                        if(pq.isEmpty())
                                break;
                        Pair p = pq.remove();
                        time = Math.max(time,p.b);
                        if(p.c-time>=0)
                                ans[p.a] = time++;
                }
                for(int i=1 ; i<=n ; i++)
                        out.print(ans[i]+" ");

                out.println();
        }
        static class Pair implements Comparable<Pair>
        {
                int a,b,c;

                @Override
                public int compareTo(Pair o) {
                       if(Integer.compare(this.b,o.b)==0)
                               return this.a-o.a;
                       return this.b-o.b;
                }

                public Pair(int a, int b, int c) {
                        this.a = a;
                        this.b = b;
                        this.c = c;
                }
        }

        void run() {
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                int t = i();
                while(t-->0)
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
        }
        public static void main(String[] args)throws Exception {
                new B().run();
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
