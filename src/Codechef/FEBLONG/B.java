package Codechef.FEBLONG;
import java.util.*;
import java.io.*;
public class B {
        String INPUT = "";
        void solve()
        {
                int n = i(), m = i(), x = i(), k = i();
                String s = s();
                int count_even=0,count_odd=0;
                for (int i = 0; i < s.length(); i++) {
                        if(s.charAt(i)=='E') count_even++;
                        else                 count_odd++;
                }
                boolean flag = false;
                for (int i = 1; i <= m ; i++)
                {
                        if((i&1)==1)
                        {
                                int y = count_odd<x?count_odd:x;
                                count_odd-=y;
                                n-=y;
                        }
                        else
                        {
                                int y = count_even<x?count_even:x;
                                count_even-=y;
                                n-=y;
                        }
                        if(n<=0)
                        {
                                flag = true;
                                break;
                        }
                }
                if(flag) out.println("yes");
                else out.println("no");
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
}
