package DataStructure;

import java.util.*;
//stores the cummulative frequency of a given array
public class Fenwick
{
        public static int sumFenwick(int[] ft, int i)
        {
                int sum = 0;
                for(i++;i > 0;i -= i&-i){sum += ft[i];}

                return sum;
        }
        public static int sumRange(int[] ft,int l ,int r)
        {
                return sumFenwick(ft,r)-(l==1?0:sumFenwick(ft,l-1));
        }

        public static void addFenwick(int[] ft, int i, int v)
        {
                if(v == 0 || i < 0)return;
                int n = ft.length;
                for(i++;i < n;i += i&-i)ft[i] += v;
        }


        public static long sumFenwick(long[] ft, int i)
        {
                long sum = 0;
                for(i++;i > 0;i -= i&-i)sum += ft[i];
                return sum;
        }

        public static void addFenwick(long[] ft, int i, long v)
        {
                if(v == 0)return;
                int n = ft.length;
                for(i++;i < n;i += i&-i)ft[i] += v;
        }

        public static int indexWithGivenCummFreq(int[] ft, int v)
        {
                int i = 0, n = ft.length;
                for(int b = Integer.highestOneBit(n);b != 0;b >>= 1){
                        if((i|b) < n && ft[i|b] < v){
                                i |= b;
                                v -= ft[i];
                        }
                }
                return i;
        }

        public static int indexWithGivenCummFreq(long[] ft, long v)
        {
                int i = 0, n = ft.length;
                for(int b = Integer.highestOneBit(n);b != 0;b >>= 1){
                        if((i|b) < n && ft[i|b] < v){
                                i |= b;
                                v -= ft[i];
                        }
                }
                return i;
        }
        public static void main(String[] args) {
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                int[] a = new int[n+1];
                for(int i = 0 ; i<n ; i++)
                {
                        addFenwick(a,i,sc.nextInt());
                }
                //index is 0 based
                System.out.println(indexWithGivenCummFreq(a,19));
        }
}
