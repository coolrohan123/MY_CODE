package Practice;

import FastScanner.Competitive;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.HashSet;
import java.util.InputMismatchException;
import java.util.Set;

public class H1 {
        String INPUT = "9\n" +
                "9 5 8 6 3 2 4 1 7";

        void solve()
        {
                int n = i();
                int[] a = ia(n);
                int[] mark = new int[100005];
                int[] count = new int[100005];
                if(n==1)
                {
                        out.println(a[0]);
                        return;
                }
                int largest = Math.max(a[0],a[1]);
                int sec_largest = Math.min(a[0],a[1]);
                mark[a[0]] = 1;
                if (a[0] > a[1]) {
                        count[a[0]]++;
                } else {
                        mark[a[1]]=1;
                }
                for (int i = 2; i <n ; i++) {
                        if(a[i]>largest)
                        {
                                mark[a[i]] = 1;
                                sec_largest = largest;
                                largest = a[i];
                        }
                        else if(a[i]>sec_largest)
                        {
                                count[largest]++;
                                sec_largest = a[i];
                        }
                        else
                                continue;
                }
                int j = 1;
                int ans = 0;
                while(mark[j]>0 && j<=n)j++;
                if(j==n+1)
                {
                        out.println(1);
                        return;
                }
                else
                {
                        ans = j;
                }
                for(int i = 0 ; i<n ; i++)
                {
                        if(mark[a[i]]==0 || count[a[i]]<=1)
                                continue;

                        if(count[a[i]]>count[ans])
                                ans = a[i];
                        else if(count[a[i]] == count[ans] &&(a[i]<ans))
                                ans = a[i];
                }
                out.println(ans);
        }
        ///////////////////////////////////////
        void run() throws Exception{
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                int t = 1;
                while(t-->0)
                solve();
                out.flush();
               // tr(System.currentTimeMillis()-s+"ms");
        }
        public static void main(String[] args)throws Exception {
                new H1().run();
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

        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }

}
