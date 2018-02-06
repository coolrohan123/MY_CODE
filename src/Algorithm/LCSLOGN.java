package Algorithm;

public class LCSLOGN {
        static int LCS(int[] a,int n)
        {
                int[] tail = new int[n];
                int len = 1;
                tail[0] = a[0];
                for(int i =1 ; i<n ; i++)
                {
                        if(a[i]<tail[0])
                                tail[0] =a[i];
                        else if(a[i]>tail[len-1])
                                tail[len++] = a[i];
                        else
                                tail[lower_bound(tail,a[i],len)] = a[i];
                }

                return len;

        }
        static int lower_bound(int[] a,int val,int n)
        {
                int l = 0;
                int h = n;
                while(l<h)
                {
                        int m = (l+h)>>1;
                        if(val<=a[m]) h = m;
                        else l = m+1;
                }
                return l;
        }
}
