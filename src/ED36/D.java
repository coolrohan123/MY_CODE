package  ED36;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class D{
        InputStream is;
        PrintWriter out;
        String INPUT = "8 4 1 2 2 3 3 4 4 2 ";

        int[] dfs(int cur, int[][] g, int dep, int[] hist, boolean[] fed, boolean[] ved)
        {
                hist[dep] = cur;
                fed[cur] = true;
                ved[cur] = true;
                for(int e : g[cur]){
                        if(fed[e]){
                                for(int i = 0;i <= dep;i++){
                                        if(hist[i] == e){
                                                return Arrays.copyOfRange(hist, i, dep+1);
                                        }
                                }
                                throw new RuntimeException();
                        }
                }
                for(int e : g[cur]){
                        if(!ved[e]){
                                int[] res = dfs(e, g, dep+1, hist, fed, ved);
                                if(res != null)return res;
                        }
                }
                fed[cur] = false;
                return null;
        }

        void solve()
        {

        }


        void run() throws Exception
        {
                is = oj ? System.in : new ByteArrayInputStream(INPUT.getBytes());
                out = new PrintWriter(System.out);

                long s = System.currentTimeMillis();
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
        }

        public static void main(String[] args) throws Exception { new D().run(); }

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
        private int ni()
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