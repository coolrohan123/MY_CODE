public class Math {
        private long fib(long n)
        {
                if(n==0) return 0;
                long a=0, b=1, c=1, d=1, e=n-2;
                long a1, b1, c1, d1, a2=0, b2=1, c2=1, d2=1;

                while(e>0){
                        if(e%2==1){
                                a1 = (a*a2+b*c2)%mod;
                                c1 = (c*a2+d*c2)%mod;
                                b1 = (a*b2+ b*d2)%mod;
                                d1 = (b2*c+ d*d2)%mod;
                                a=a1; b=b1; c=c1; d= d1;
                        }
                        a1 = (a2*a2+b2*c2)%mod;
                        c1 = (c2*a2+d2*c2)%mod;
                        b1 = (a2*b2+ b2*d2)%mod;
                        d1 = (b2*c2+ d2*d2)%mod;
                        a2=a1; b2=b1; c2=c1; d2= d1;
                        e /= 2;
                }
                return d;
        }

        private int gcd(int a,int b)
        {
                return a==0?b:gcd(b,a%b);
        }
        private long gcd(long a,long b)
        {
                return a==0?b:gcd(b,a%b);
        }
        private long gcdExtended(long a,long b,long[] x)
        {
                if(a==0)
                {
                        x[0] = 0;
                        x[1] = 1;
                        return b;
                }
                long[] y = new long[2];
                long gcd = gcdExtended(b%a,a,y);
                x[0] = y[1] -(b/a)*y[0];
                x[1] = y[0];
                return gcd;
        }
        private boolean findSum(int[] set,int n,long sum)
        {
                if(sum==0)
                        return true;
                if(n==0 && sum!=0)
                        return false;
                if(set[n-1]>sum)
                        return findSum(set,n-1,sum);
                return findSum(set,n-1,sum)||findSum(set,n-1,sum-set[n-1]);
        }
        private int StringExpo(int x,String s,int mod)
        {
                x%=mod;
                int res = 1%mod;
                int now = x;
                for(int i = s.length()-1; i>=0 ; i--)
                {
                        res = (int) ((res*modPow(now,s.charAt(i)-'0',mod))%mod);
                        now = (int)modPow(now,10,mod);
                }
                return res;
        }
        private long modPow(long base,long exp,long mod)
        {
                long res = 1L;
                while(exp>0)
                {
                        if(exp%2==1)
                                res = (res*base)%mod;
                        base = (base*base)%mod;
                        exp>>=1;
                }
                return res;
        }
        private long inv(long a, long b){
                return 1<a ? b - inv(b%a,a)*b/a : 1;
        }
        long[] fac ;
        long[] inv;
        long mod = (long)1e9+7;
        private void cal()
        {
                fac = new long[1000005];
                inv = new long[1000005];
                fac[0] = 1;
                inv[0] = 1;
                for(int i = 1; i<=1000000; i++)
                {
                        fac[i] = (fac[i-1]*i)%mod;
                        inv[i] = (inv[i-1]*modPow(i,mod-2,mod))%mod;
                }
        }
        private long ncr(int n,int r)
        {
                return (((fac[n]*inv[r])%mod)*inv[n-r])%mod;
        }
}
