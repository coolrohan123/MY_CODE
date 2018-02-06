package Algorithm;

import java.util.Scanner;
/*
problem : Educational round 36 Problem D
 */
public class TopologicalSort {
        // This method finds topological ordering with the edge from sou to des
        private int[]  topologicalSortWithoutIJ(int[][] graph, int sou, int des) {

                int n = graph.length;
                int[] result = new int[n];
                int[] in_degree = new int[n];
                // stores the in_degree of every node
                for(int i = 0 ; i<n ; i++)
                {
                        for(int dest : graph[i])
                        {
                                if(i == sou && dest == des) continue;
                                in_degree[dest]++;
                        }
                }
                int index = 0;
                // stores the source first in the sort result
                for(int i = 0 ; i<n ; i++)
                {
                        if(in_degree[i] == 0) result[index++] = i;
                }
                // for each source store the child nodes
                for(int i = 0 ; i<index ; i++)
                {
                        for(int dest: graph[result[i]])
                        {
                                if(result[i] == sou && i == des) continue;
                                if(--in_degree[dest] == 0)
                                        result[index++] = dest;
                        }
                }
                // if in degree of any node after all the step is not zero then there is a cycle
                for(int i = 0 ; i<n ; i++)
                {
                        if(in_degree[i]>0)
                                return null;
                }
                return result;
        }

        private static int[] sortTopologically(int[][] g)
        {
                int n = g.length;
                int[] ec = new int[n];
                //ec stores in degree of every vertex
                for(int i = 0; i < n; i++){
                        for(int to : g[i])ec[to]++;
                }

                int[] ret = new int[n];
                int q = 0;
                // sources
                for(int i = 0;i < n;i++){
                        if(ec[i] == 0)ret[q++] = i;
                }

                for(int p = 0;p < q;p++){
                        for(int to : g[ret[p]]){
                                if(--ec[to] == 0)ret[q++] = to;
                        }
                }
                // loop
                for(int i = 0;i < n;i++){
                        if(ec[i] > 0)return null;
                }
                return ret;
        }
        private static int[][] arrayGraphD(int n, int[] from, int[] to){ return packD(n, from, to, from.length);}
        private static int[][] packD(int n, int[] from, int[] to, int sup)
        {
                int[][] g = new int[n][];
                int[] p = new int[n];
                for(int i = 0;i < sup;i++)p[from[i]]++;
                for(int i = 0;i < n;i++)g[i] = new int[p[i]];
                for(int i = 0;i < sup;i++){
                        g[from[i]][--p[from[i]]] = to[i];
                }
                return g;
        }
        public static void main(String[] args)
        {
                Scanner sc = new Scanner(System.in);
                int n = sc.nextInt();
                int q = sc.nextInt();
                int[] from = new int[n];
                int[] to   = new int[n];
                for (int i = 0; i < to.length; i++) {
                        from[i] = sc.nextInt()-1;
                        to[i] = sc.nextInt()-1;
                }
                int[][] g = arrayGraphD(n,from,to);
                int[] x = sortTopologically(g);
                for (int i = 0; i < x.length; i++) {
                       // int x1 = x[i];
                        System.out.print(x[i]+1+" ");
                }
        }
}
