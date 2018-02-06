package DataStructure;

public class RMQLazy {
        int N = (int)1e5;
        int[] t = new int[2*N];
        int[] d = new int[N];
        int n  ; // size of array
        int h; // height of the tree;
        private void apply(int p,int value)
        {
                t[p] += value;
                if(p<n) d[p] += value;// p<n condition checks that p is non leaf
        }
        private void build(int p)
        {
                while(p>1)
                {
                        p>>=1;
                        t[p] = Math.max(t[p<<1],t[p<<1|1])+d[p];
                }
        }
        private void push(int p)
        {
                for(int s = h; s>0; --s)
                {
                        int i = p>>s;
                        if(d[i]!=0)
                        {
                                apply(i<<1,d[i]);
                                apply(i<<1|1,d[i]);
                                d[i] =0;
                        }
                }
        }
        private void inc(int l,int r,int value)
        {
                l+=n; r+=n;
                int l0=l,r0=r;
                for(;l<r;l>>=1,r>>=1)
                {
                        if((l&1)==1) apply(l++,value);
                        if((r&1)==1) apply(--r,value);
                }
                build(l0);
                build(r0-1);


        }
        private int query(int l,int r)
        {
                l+=n; r+=n;
                push(l);
                push(r-1);
                int res = Integer.MIN_VALUE;
                for(;l<r; l>>=1, r>>=1)
                {
                        if((l&1)==1) res = Math.max(res,t[l++]);
                        if((r&1)==1) res = Math.max(t[--r],res);
                }
                return res;
        }
}
