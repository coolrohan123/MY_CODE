package Algorithm;

public class CoinChangeDP {
        public static void main(String[] args) {
                int[] a = {1,3,4}; // coin values
                // recursion formula
                // solve(x) = min(solve(x-c)+1) for all c in coins
                // solve(0) = 0, and solve(x) = INF if x<0
                CoinChangeIter(a);
        }

        private static void CoinChangeIter(int[] a) {
                int[] first = new int[101];
                int[] ans   = new int[101];
                ans[0] = 0;
                for(int x =1; x<=100; x++)
                {
                        ans[x] = Integer.MAX_VALUE;
                        for(int c:a)
                        {
                                if(x-c>=0 && ans[x-c]+1<ans[x])
                                {
                                        ans[x] = ans[x-c]+1;
                                        first[x] = c;
                                }
                        }
                }
                int n = 10;
                System.out.println(ans[10]);
                while(n>0)
                {
                        System.out.print(first[n]+" ");
                        n-=first[n];
                }
        }
}
