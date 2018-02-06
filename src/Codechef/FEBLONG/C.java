package Codechef.FEBLONG;
import java.util.*;
import java.io.*;
public class C {
        String INPUT = "";
        int[] a ;
        void solve()
        {
                String s = s(); int n = s.length();
                if(!isValid(s))
                {
                       out.println(-1);
                       return;
                }
                Pair[] p = new Pair[n];
                for (int i = 0; i < s.length(); i++) {
                        p[i] = new Pair(s.charAt(i),i,a[s.charAt(i)-'a']);
                }
                Arrays.sort(p, new Comparator<Pair>() {
                        @Override
                        public int compare(Pair o1, Pair o2) {
                                if(o1.c%2==1 || o2.c%2==1)
                                {
                                        if(o1.c%2==1 && o2.c%2==1)
                                                return Integer.compare(o1.c,o2.c);
                                        if(o1.c%2==1)
                                                return -1;
                                        else if(o2.c%2==1)
                                                return 1;
                                }
                                return o1.a-o2.a;
                        }
                });

                int[] ans = new int[n];
                int l = 0, r=0;

                if((n&1)==1){ l = r = n/2; }
                else        { l = n/2-1; r = l+1;}


                int k = 0;
                for(;l>=0 && r<=n-1; l--,r++)
                {
                        if(l==r)
                        {
                                ans[l] = p[k++].b;
                                continue;
                        }
                        ans[l] = p[k++].b;
                        ans[r] = p[k++].b;
                }
                for (int i = 0; i < n; i++) {
                        out.print(ans[i]+1+" ");
                }
                out.println();
        }
        static class Pair{
                char a ; int b,c;

                public Pair(char a, int b,int c) {
                        this.a = a;
                        this.b = b;
                        this.c = c;
                }

                @Override
                public String toString() {
                        return "Pair{" +
                                "a=" + a +
                                ", b=" + b +
                                ", c=" + c +
                                '}';
                }
        }
        boolean isValid(String s)
        {
                a = new int[26];
                for (int i = 0; i < s.length(); i++) {
                        a[s.charAt(i)-'a']++;
                }
                int odd=0;
                for (int i = 0; i < 26; i++) {
                        if((a[i]&1)==1)
                                odd++;
                        if(odd>1) return false;
                }
                return true;
        }
        void run() throws Exception{
                //is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                int t = i();
                while(t-->0)
                        solve();
                out.flush();
                //tr(System.currentTimeMillis()-s+"ms");
        }
        public static void main(String[] args)throws Exception {
                new C().run();
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
}
