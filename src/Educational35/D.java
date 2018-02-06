package Educational35;

import FastScanner.Competitive;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class D {
        String INPUT = "4\n" +
                "1 2 4 3\n" +
                "4\n" +
                "1 1\n" +
                "1 4\n" +
                "1 4\n" +
                "2 3";

        void solve()
        {
                //Using final in those variable where we are sure that it will not change
                // will reduce executiion time
                final int n = i();
                final int[] a = ia(n);
                final int q = i();
                Answer(n, a, q);


        }

        private void Answer(int n, int[] a, int q) {
                int count = 0 ;
                count = getInversionCount(n, a, count);
                printAnsforQuery(count, q);
        }

        private void printAnsforQuery(int count, int q) {
                for (int i = 0; i < q; i++) {
                        final int l = i(), r = i();
                        final int mid = (r - l + 1) / 2;
                        if (mid % 2 == 0) {
                                out.println(count % 2 == 0 ? "even" : "odd");
                        } else {
                                count++;
                                out.println(count % 2 == 0 ? "even" : "odd");
                        }
                }
        }

        private int getInversionCount(int n, int[] a, int count) {
                for (int i = 0; i < n - 1; i++) {
                        for (int j = i + 1; j < n; j++) {
                                if (a[j] < a[i]) {
                                        count++;
                                }
                        }
                }
                return count;
        }

        /////////////////////////////////////////////////////////////
        void run() throws Exception{
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                int t = 1;
                while(t-->0)	solve();
                out.flush();
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
