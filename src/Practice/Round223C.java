package Practice;

//import FastScanner.Competitive;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class Round223C {
        String INPUT = "())(())(())(\n" +
                "7\n" +
                "1 1\n" +
                "2 3\n" +
                "1 2\n" +
                "1 12\n" +
                "8 12\n" +
                "5 11\n" +
                "2 10";
        static class Pair
        {
                int a,b,c;

                public Pair(int a, int b, int c) {
                        this.a = a;
                        this.b = b;
                        this.c = c;
                }
        }
        char[] s;
        int n;
        void solve()
        {
                s = s().toCharArray();
                n = s.length;
                for(int i = 0 ; i<n ; i++)
                {
                        if(s[i]=='(')
                        {
                                t[i+n] = new Pair(0,1,0);
                        }
                        else
                        {
                                t[i+n] = new Pair(0,0,1);
                        }
                }
                build();
                int q = i();
                while(q-->0)
                {
                        int l = i()-1,r=i();
                        out.println(query(l,r));
                }

        }
        private Pair combine(Pair l,Pair r)
        {
               int pair = Math.min(l.b,r.c);
               return new Pair(l.a+r.a+2*pair,l.b+r.b-pair,l.c+r.c-pair);
        }
        int N = (int)1e6;
        Pair[] t = new Pair[2*N];

        public void build() //builds the segment tree
        {
                for(int i = n-1; i>0 ; i--)
                {

                        t[i] = combine(t[i<<1],t[i<<1|1]);
                }

        }
//        public void modify(int p,int value)// update the value at index p with value
//        {
//                for(t[p+=n] = value;p>1;p>>=1)
//                        t[p>>1] = t[p]+t[p^1];
//        }
//        public void modify(int l,int r,int value)//range update
//        {
//                for(l+=n,r+=n;l<r;l>>=1,r>>=1)
//                {
//                        if((l&1)==1) t[l++] += value;
//                        if((r&1)==1) t[--r] += value;
//                }
//        }
        public int query(int l,int r) // sum of interval [l,r) { array is 0 based}
        {
                Pair r1 =new Pair(0,0,0);
                Pair r2 = new Pair(0,0,0);
                for(l+=n,r+=n;l<r; l>>=1 , r>>=1)
                {
                        if((l&1)==1)
                        {
                                r1 = combine(r1,t[l]);
                                l++;
                        }
                        if((r&1)==1)
                        {
                                --r;
                                r2 = combine(t[r],r2);
                        }
                }
                return combine(r1,r2).a;
        }
        ///////////////////////////////////////
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
                new Round223C().run();
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
