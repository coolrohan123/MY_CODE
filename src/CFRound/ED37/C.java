package CFRound.ED37;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;
public class C {
        String INPUT = "6\n" +
                "1 2 5 3 4 6\n" +
                "010100";
        void solve()
        {
                int n = i();
                int[] a = new int[n+1];
                for (int i = 1; i <=n ; i++) {
                        a[i] = i();
                }
                int[] swap = new int[n];
                char[] s = s().toCharArray();
                for(int i =1 ; i<=n-1; i++){
                        swap[i] = s[i-1]-'0';
                }
                for(int i =1; i<=n-1; i++)
                {
                        if(swap[i]==1)
                        {
                                int j = i;
                                while(j<=n-1 && swap[j] == 1) j++;
                                Arrays.sort(a,i,j+1);
                                i = j;
                        }
                }
                boolean ok = true;
                for(int i=1; i<=n ; i++)
                        ok &= (a[i]==a[i-1]+1);
                if(ok) out.println("YES");
                else    out.println("NO");

                ///implemented in the contest, wrong logic
//                int ind = 0;
//                boolean flag = true;
//                for(int i = 1; i<=n-1; i++)
//                {
//                        if(a[i]==i) continue;
//                        if(a[i]<i) continue;
//                        ind = a[i] -1;
//                        for (int j = i; j <=ind ; j++) {
//                                if(swap[j]!=1)
//                                {
//                                        flag = false;
//                                        break;
//                                }
//                        }
//                        if(!flag)
//                                break;
//                        i = ind;
//                }
//                if(flag) out.println("YES");
//                else    out.println("NO");

        }

        void run() {
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
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

        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }

}
