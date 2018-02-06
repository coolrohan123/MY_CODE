package DataStructure;

import  java.util.*;
import  java.io.*;

public class SUBST1suffixarray {
        int MAX = 50010,n;
        char[] st;
        int[] sa ,ra,tempsa,tempra,countin,lcp,plcp,phi;
        public SUBST1suffixarray()
        {

        }
        public SUBST1suffixarray(String s, int n)
        {
                this.st = s.toCharArray();
                this.sa = new int[MAX];
                this.ra = new int[MAX];
                this.tempsa = new int[MAX];
                this.tempra = new int[MAX];
                this.countin = new int[MAX];
                this.lcp = new int[MAX];
                this.plcp = new int[MAX];
                this.phi = new int[MAX];
                this.n = n;
        }
        void countingSort(int k)
        {
                int maxRange = Math.max(260,n);
                Arrays.fill(countin,0);
                for (int i = 0; i < n; i++) {
                        countin[i+k<n ? ra[i+k]:0]++;
                }
                int sum = 0;
                for (int i = 0; i < maxRange; i++) {
                        int temp = countin[i];
                        countin[i] = sum;
                        sum+= temp;
                }
                for (int i = 0; i < n; i++) {
                        tempsa[countin[sa[i]+k<n?ra[sa[i]+k]:0]++] = sa[i];
                }
                sa = Arrays.copyOf(tempsa,n);
        }
        void constructSA()
        {
                for (int i = 0; i < n; i++) {
                        sa[i] = i;
                        ra[i] = st[i];
                }
                for (int i = 1; i < n; i<<=1) {
                        countingSort(i);
                        countingSort(0);
                        int rank;
                        tempra[sa[0]] = rank = 0;
                        for (int j = 1; j <n ; j++) {
                                tempra[sa[j]] = (ra[sa[j-1]] == ra[sa[j]] && ra[sa[j-1]+i] == ra[sa[j]+i])?rank: ++rank;
                        }
                        for (int j = 0; j < n; j++) {
                                ra[j] = tempra[j];
                        }
                        if(ra[sa[n-1]] == n-1)
                                break;
                }
        }
        void computeLCP()
        {
                phi[sa[0]] = -1;
                for (int i = 1; i < n ; i++) {
                        phi[sa[i]] = sa[i-1];
                }
                int l = 0;
                for (int i = 0; i < n; i++) {
                        if(phi[i] == -1)
                        {
                                plcp[i] = 0;
                                continue;
                        }
                        while(st[i+l]==st[phi[i]+l])
                                l++;
                        plcp[i] = l;
                        l = Math.max(l-1,0);
                }
                for (int i = 0; i < n; i++) {
                        lcp[i] = plcp[sa[i]];
                }
        }

        String INPUT = "";
        void solve()
        {
                int t = i();

                while(t-->0)
                {
                        String s = s();
                        int n = s.length();
                        s+="$";
                        SUBST1suffixarray sa = new SUBST1suffixarray(s,s.length());
                        sa.constructSA();
                        sa.computeLCP();
                        out.println(n*(n+1)/2-Arrays.stream(sa.lcp,0,s.length()+1).sum());
                }

        }
        void run() throws Exception{
                is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                solve();
                out.flush();
        }
        public static void main(String[] args)throws Exception {
                new SUBST1suffixarray().run();
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

        //private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        //private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }


}
