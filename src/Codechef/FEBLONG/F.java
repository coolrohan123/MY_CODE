package Codechef.FEBLONG;
import java.util.*;
import java.io.*;
public class F {
        String INPUT = "1\n" +
                "11\n" +
                "0 0\n" +
                "1 1\n" +
                "2 3\n" +
                "2 5\n" +
                "0 10\n" +
                "-2 10\n" +
                "-5 9\n" +
                "-8 7\n" +
                "-8 4\n" +
                "-6 1\n" +
                "-2 0";
        double error = 1e-19;
        int[] dx = {1,1,0,-1,-1,-1,0,1};
        int[] dy = {0,1,1,1,0,-1,-1,-1};
        void solve()
        {
                int n = i();
                Point[] point = new Point[n];
                for (int i = 0; i < n; i++) {
                        point[i] = new Point(i(),i());
                }
                Point p1 = new Point(0,1);
                int ans_size = (int)Math.floor(n/10);
                Queue<Point> q = new LinkedList<>();
                for (int i = 0; i < n; i++) {
                        q.add(point[i]);
                }
                int ind = 0;
                Point[] ans = new Point[ans_size];
                while(!q.isEmpty())
                {
                        Point p = q.poll();
                        if(contins(point,p)==2)
                        {
                                ans[ind++] = p;
                                if(ind == ans_size)
                                        break;
                        }
                        boolean flag = false;
                        for (int i = 0; i < 8; i++) {
                                Point x = new Point(p.x+dx[i],p.y+dy[i]);
                                if(contins(point,x)==2)
                                {
                                        ans[ind++] = x;
                                        if(ind==ans_size)
                                        {
                                                flag = true;
                                                break;
                                        }
                                        q.add(x);
                                }
                        }
                        if(flag)
                                break;
                }
                for (int i = 0; i < ans_size; i++) {
                        out.println((int)(ans[i].x)+" "+(int)ans[i].y);
                }
        }
        int ccw = 1;
        int cw = -1;
        int onb = 2;
        int onf = -2;
        int ons = 0;
        int ou = 0;
        int on = 1;
        int in = 2;
        int ccw(Point p,Point p1,Point p2){
                Point a = Point.sub(p1,p);
                Point b = Point.sub(p2,p);
                if(Point.cross(a,b) > error) return ccw;
                if(Point.cross(a,b) < -error) return cw;
                if(Point.dot(a,b) < -error) return onb;
                if(Point.norm(a) < Point.norm(b)) return onf;
                return ons;
        }
        int contins(Point[]  point, Point p){
                int length = point.length;
                int v1 = ccw(point[length-1],point[0],p);
                int v2 = ccw(point[0],point[1],p);
                if(v1 == cw || v2 == cw) return ou;
                if(v1 == ons || v2 == ons) return on;
                int r = length-1, l = 1;
                while(l+1!=r)
                {
                        int m = (l+r)>>1;
                        v1 = ccw(point[0],point[m],p);
                        if(v1==ccw) l = m;
                        else r = m;
                }
                v1 = ccw(point[l],point[r],p);
                if(v1==ccw) return in;
                if(v1 == ons) return on;
                return ou;
        }
        static class Point{
                double x,y;
                static double eps = 1e-19;
                Point(){};
                Point(double x,double y){
                        this.x = x; this.y = y;
                }
                public static double X(Point p){ return p.x;}
                public static double Y(Point p){ return p.y;}
                public static Point add(Point a,Point b){
                        return new Point(a.x+b.x,a.y+b.y);
                }
                public static  Point sub(Point a,Point b){
                        return new Point(a.x-b.x,a.y-b.y);
                }
                public static Point scale(double factor, Point a){
                        return new Point(a.x*factor,a.y*factor);
                }

                /////operation
                public static double norm(Point a){return a.x*a.x+a.y*a.y;}
                public static double abs(Point a){return Math.sqrt(norm(a));}
                public static double dist(Point a,Point b){
                        return abs(sub(b,a));
                }
                public static boolean same(double a,double b){
                        return a+eps>b&&b+eps>a;
                }
                public static double dot(Point a,Point b){
                        return a.x*b.x+a.y*b.y;
                }
                public static double cross(Point a,Point b){
                        return a.x*b.y-a.y*b.x;
                }
                public static int cmp(double a,double b){
                        return (a+eps<b? -1 : (b+eps<a?1:0));
                }
        }
        void run() throws Exception{
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                int t = i();
                while(t-->0)
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
        }
        public static void main(String[] args)throws Exception {
                new F().run();
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
