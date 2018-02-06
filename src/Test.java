import org.jetbrains.annotations.NotNull;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.*;
import java.lang.*;

public class Test {
        String INPUT = "aabbb";
        void solve()
        {
//                Integer[] a = {5,2,3,4};
//                Arrays.sort(a, new Comparator<Integer>() {
//                        @Override
//                        public int compare(Integer o1, Integer o2) {
//                                if(o1%2==1 || o2%2==1)
//                                {
//                                        if(o1%2==1)
//                                                return -1;
//                                        else
//                                                return +1;
//                                }
//                                return o1-o2;
//                        }
//                });
//                Arrays.stream(a).forEach(e->out.print(e+" "));
                long k = 100000000000000L;
                out.println((int)k%10);
        }

        void run() throws Exception{
                is = new ByteArrayInputStream(INPUT.getBytes());
                out = new PrintWriter(System.out);
                solve();
                out.flush();
        }
        public static void main(String[] args)throws Exception {
                new Test().run();
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
}
