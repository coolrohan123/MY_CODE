package Geometry;

public class Point {
        double x,y;
        double eps = 1e-19;
        Point(){};
        Point(double x,double y){
                this.x = x; this.y = y;
        }
        public double X(Point p){ return p.x;}
        public double Y(Point p){ return p.y;}
        public Point add(Point a,Point b){
                return new Point(a.x+b.x,a.y+b.y);
        }
        public Point sub(Point a,Point b){
                return new Point(a.x-b.x,a.y-b.y);
        }
        public Point scale(double factor, Point a){
                return new Point(a.x*factor,a.y*factor);
        }

        /////operation
        public double norm(Point a){return a.x*a.x+a.y*a.y;}
        public double abs(Point a){return Math.sqrt(norm(a));}
        public double dist(Point a,Point b){
                return abs(sub(b,a));
        }
        public boolean same(double a,double b){
                return a+eps>b&&b+eps>a;
        }
        public double dot(Point a,Point b){
                return a.x*b.x+a.y*b.y;
        }
        public double cross(Point a,Point b){
                return a.x*b.y-a.y*b.x;
        }
        public int cmp(double a,double b){
                return (a+eps<b? -1 : (b+eps<a?1:0));
        }
        public double distSq(Point a,Point b){
                return norm(sub(b,a));
        }
        public Point midPoint(Point a,Point b){
                return scale(0.5,add(a,b));
        }
        public Point linePointAt(Point a,Point b,double x){
                return add(a,scale(x,sub(b,a)));
        }
        public Point rotate90CCW(Point a){
                return new Point(-a.y,a.x);
        }
        public Point rotate90CW(Point a){
                return new Point(a.y,-a.x);
        }
        public Point unit(Point a){return scale(1.0/abs(a),a);}
        ////////////////
        public double triangleArea(Point a,Point b,Point c){
                return Math.abs(cross(sub(b,a),sub(c,a)))/2.0;
        }
        public Point bisector(Point a,Point b){
                if(cross(a,b)==0) return rotate90CW(b);
                return midPoint(unit(a),unit(b));
        }
        public Point[] triangleBisector(Point a,Point b,Point c){
                Point p1 = a;
                Point p2 = add(a,midPoint(unit(sub(b,a)),unit(sub(c,a))));
                return new Point[]{p1,p2};
        }

        public boolean pointInTriangle(Point a,Point b,Point c,Point p){
                return cmp(Math.abs(cross(sub(b,a),sub(c,a))),
                        Math.abs(cross(sub(a,p),sub(b,p)))+
                           Math.abs(cross(sub(b,p),sub(c,p)))+
                           Math.abs(cross(sub(c,p),sub(a,p)))) == 0;
        }

        //////////////////////
        public static void main(String[] args) {
                new Point().run();
        }
        void run(){
                Point p  = new Point(0,0);
                Point p1 = new Point(3,4);
                System.out.println(dist(p,p1));
        }

}
