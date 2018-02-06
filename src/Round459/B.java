package Round459;
import java.util.*;
import java.io.*;
public class B {
        String INPUT = "3 5\n" +
                "google 8.8.8.8\n" +
                "codeforces 212.193.33.27\n" +
                "server 138.197.64.57\n" +
                "redirect 138.197.64.57;\n" +
                "block 8.8.8.8;\n" +
                "cf 212.193.33.27;\n" +
                "unblock 8.8.8.8;\n" +
                "check 138.197.64.57;";
        void solve()
        {
                int n = i(), m = i();
                Pair[] pair1 = new Pair[n];
                for (int i = 0; i < n; i++) {
                        pair1[i] = new Pair(s(),s());
                }
                Pair[] pair2 = new Pair[m];
                for (int i = 0; i < m; i++) {
                        String s1 = s();
                        String s2 = s();
                        pair2[i] = new Pair(s1,s2.substring(0, s2.length()-1));
                }
                for (int i = 0; i < m; i++) {
                        Pair p = pair2[i];
                        for (int j = 0; j < n; j++) {
                                if(p.b.equals(pair1[j].b))
                                {
                                        pair2[i].b+="; #"+pair1[j].a;
                                        break;
                                }
                        }
                }
                for(Pair p: pair2)
                {
                        out.println(p.a+" "+p.b);
                }
        }
        static class Pair
        {
                String a,b;

                public Pair(String a, String b) {
                        this.a = a;
                        this.b = b;
                }

                @Override
                public String toString() {
                        return "Pair{" +
                                "a='" + a + '\'' +
                                ", b='" + b + '\'' +
                                '}';
                }
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
