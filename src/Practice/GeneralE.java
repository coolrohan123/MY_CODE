package Practice;

import java.util.Arrays;
import java.util.Scanner;

public class GeneralE {
        private int[] array;
        Scanner sc ;
        void solve()
        {
               sc = new Scanner(System.in);
               int n = sc.nextInt();
               int m = sc.nextInt();
               array = new int[n];
               Arrays.fill(array,-1);
               for(int i = 0 ; i<m ; i++)
               {
                       int first = sc.nextInt()-1;
                       int second = sc.nextInt()-1;
                       union(first,second);
               }
               int[] strength = new int[n];
               for(int i = 0 ; i< n ; i++)
               {
                       strength[i] = sc.nextInt();
               }

               int ans = Integer.MIN_VALUE;
               int[] size = new int[n];
               for(int i = 0 ; i<n ; i++)
               {
                       if(array[i]<0)
                               size[i]+=strength[i];
                       else
                       {
                               int root = root(i);
                               size[root]+=strength[i];
                       }
               }
               for(int i = 0 ; i<n ; i++)
               {
                       ans = Math.max(ans,size[i]);
               }
               System.out.println(ans);
        }
        public static void main(String[] args)
        {
                new GeneralE().solve();
        }

        private int root(int x) {
                return array[x] < 0 ? x : (array[x] = root(array[x]));
        }

        private boolean equal(int x, int y) {
                return root(x) == root(y);
        }

        private boolean union(int x, int y) {
                x = root(x);
                y = root(y);
                if (x != y) {
                        if (array[y] < array[x]) {
                                int d = x;
                                x = y;
                                y = d;
                        }
                        array[x] += array[y];
                        array[y] = x;
                }
                return x == y;
        }
        private int count()
        {
                int count = 0;
                for(int i = 0 ; i<array.length; i++)
                {
                        if(array[i]<0)
                                count++;
                }
                return count;
        }
}
