package Codechef.FEBLONG;
import java.util.*;
import java.io.*;
public class H {
        String INPUT = "2\n" +
                "5\n" +
                "4 5\n" +
                "3 5\n" +
                "2 4\n" +
                "1 3\n" +
                "5 5\n" +
                "2\n" +
                "4 1 2 3 4\n" +
                "1 4\n" +
                "5\n" +
                "4 5\n" +
                "3 5\n" +
                "2 4\n" +
                "2 3\n" +
                "5 5\n" +
                "2\n" +
                "2 2 5\n" +
                "3 1 2 5";
        void solve()
        {
//                int n = i();
//                BitSet[] bt = new BitSet[n];
//                for (int i = 0; i < n; i++) {
//                        bt[i] = new BitSet(n);
//                }
//                for (int i = 0; i < n; i++) {
//                        int l = i(), r = i();
//                        for (int j = l; j <=r ; j++) {
//                                bt[i].set(j);
//                        }
//                }
//                int q = i();
//                while(q-->0)
//                {
//                        int m = i();
//                        BitSet bt1 = new BitSet(m);
//                        for (int i = 0; i < m; i++) {
//                                bt1.set(i());
//                        }
//                        int count = 0;
//                        for (int i = 0; i < n; i++) {
//                                BitSet bt2 = (BitSet)bt1.clone();
//                                bt2.and(bt[i]);
//                                if(bt2.cardinality()%2==1)
//                                        count++;
//                        }
//                        out.println(count);
//                }
                int n = i();
                int[] a = new int[n+2];
                for (int i = 0; i < n; i++) {
                        int l = i(), r= i();
                        a[l]++; a[r+1]--;
                }
                for (int i = 1; i < n+1 ; i++) {
                        a[i]+=a[i-1];
                }
                int q = i();
                while(q-->0)
                {
                        int m = i();
                        int ans = 0;
                        for(int i = 1; i<=m; i++){
                                int x = i();
                                if((a[x]&1)==1)
                                        ans++;
                        }
                        out.println(ans);
                }
        }
        void run() throws Exception{
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
                new H().run();
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
