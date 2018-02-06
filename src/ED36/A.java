package ED36;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class A {
        InputStream is;
        PrintWriter out;
        String INPUT = "4000 599";

        void solve()
        {
                String a = s();

                String b = s();
                char[] from = a.toCharArray();
                char[] to   = b.toCharArray();
                int[] freq = countFreq(from);

                //difficult to handle
                if(to.length <= from.length) {
                        boolean isEqual = true;
                        for(int i = 0 ; i < to.length ; i++)
                        {

                                boolean flag = true;
                                int j = isEqual?to[i]-'0':9;
                                for(;j>=0 ; j--)
                                {
                                        if(freq[j]>0) {
                                                freq[j]--;
                                                {
                                                        if (j != (to[i] - '0')) {
                                                                isEqual = false;
                                                        }
                                                        if (!isEqual) {
                                                                out.print(j);
                                                                flag = false;
                                                        }
                                                        if (!flag) break;
                                                        if (check(freq, to, i + 1)) {
                                                                out.print(j);
                                                                flag = false;
                                                        }
                                                        if (!flag) break;
                                                }
                                                freq[j]++;
                                        }
                                }
                        }
                } else
                {
                        //This case is quite easy....just print the from in decreasing order
                        printAns(freq);
                }

        }
        private boolean check(int[] freq, char[] to, int index) {
                for(int i = 0 ; i<=9 ; i++)
                {
                        for(int j = 0 ; j < freq[i] ; j++)
                        {
                                if(i < to[index] -'0') return true;
                                if(i > to[index] -'0') return false;
                                index++;
                        }
                }
                return true;
        }

        private void printAns(int[] freq) {
                for(int i = 9 ; i>=0 ;i--)
                {
                        while(freq[i]>0)
                        {
                                out.print(i);
                                freq[i]--;
                        }
                }
                return;
        }

        private int[] countFreq(char[] from) {
                int[] x = new int[10];
                for (int i = 0; i < from.length; i++) {
                        x[from[i]-'0']++;

                }
                return x;
        }

        void run() throws Exception
        {
                //is = System.in;
                is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
                out = new PrintWriter(System.out);

                long s = System.currentTimeMillis();
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
        }

        public static void main(String[] args) throws Exception { new A().run(); }

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
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }
}