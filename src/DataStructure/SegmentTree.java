package DataStructure;

import java.util.Scanner;

public class SegmentTree {
        int N = (int)1e5;
        int[] t = new int[2*N];
        int n ;//array size;
        public void build() //builds the segment tree
        {
                for(int i = n-1; i>0 ; i--)
                        t[i] = t[i<<1]+t[i<<1|1];
        }
        public void modify(int p,int value)// update the value at index p with value
        {
                for(t[p+=n] = value;p>1;p>>=1)
                        t[p>>1] = t[p]+t[p^1];
        }
        public void modify(int l,int r,int value)//range update
        {
                for(l+=n,r+=n;l<r;l>>=1,r>>=1)
                {
                        if((l&1)==1) t[l++] += value;
                        if((r&1)==1) t[--r] += value;
                }
        }
        public int query(int p) // compute a element at index p
        {
                int res = 0;
                for(p+=n ; p>1 ; p>>=1) res+=t[p];
                return res;
        }
        public int query(int l,int r) // sum of interval [l,r) { array is 0 based}
        {
                int res = 0;
                for(l+=n ,r+=n; l<r; l>>=1,r>>=1)
                {
                        if((l&1)==1) res+=t[l++];
                        if((r&1)==1) res+=t[--r];
                }
                return res;
        }
        private void run()
        {
                Scanner sc = new Scanner(System.in);
                n = sc.nextInt();
                for(int i = 0 ; i<n ;i++)
                {
                        t[n+i] = sc.nextInt();
                }
                System.out.println();
                build();
                /*
                 sum of the whole array is stored at index 1, i.e t[1] = sum of all elements
                        5
                        2 1 8 3 7
                        0 3 // l included (0 based) and r excluded
                        1 4
                        0 6
                        answer:
                        11
                        12
                        21
                 */
                modify(0,3,3);
                for(int i = 0; i<n ; i++)
                        System.out.print(t[n+i]+" ");
                System.out.println();
               // System.out.println(t[1]);
        }
        public static void main(String[] args)
        {
                new SegmentTree().run();
        }


}
