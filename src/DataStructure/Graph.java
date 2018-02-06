package DataStructure;

import java.util.ArrayList;
import java.util.Arrays;

public class Graph {
        //// Detects the first cycle it encounters if any in the graph
        // and returns all the nodes in the cycle in the form of an array
        // index is 0 based
        private int[] find_cycle_using_dfs(int v,int dep, int[][] graph,int[] h,boolean[] back,boolean[] vis)
        {
                h[dep] = v;
                back[v] = true;
                vis[v] = true;
                for(int node: graph[v])
                {
                        if(back[node])
                        {
                                for(int i = 0 ; i<=dep ; i++)
                                {
                                        return Arrays.copyOfRange(h,i,dep+1);
                                }
                        }
                }
                for(int node: graph[v])
                {
                        if(!vis[node])
                        {
                                int[] result = find_cycle_using_dfs(node,dep+1,graph,h,back,vis);
                                if(result!=null)
                                        return result;
                        }
                }
                back[v] = false;
                return null;
        }
        //////////////Undirected Graph represetion
        private int[][] arrayGraphU(int[] f, int[] t, int n) {
                int[][] graph = new int[n][];
                int[] p = new int[n];
                for (int from : f) {
                        p[from]++;
                }
                for (int to : t) {
                        p[to]++;
                }
                for (int i = 0; i < n; i++) {
                        graph[i] = new int[p[i]];
                }
                for (int i = 0; i < f.length; i++) {
                        graph[f[i]][--p[f[i]]] = t[i];
                        graph[t[i]][--p[t[i]]] = f[i];
                }
                return graph;

        }
        // Directed Graph representation
         private static int[][] DirectedGraph(int n, int[] from, int[] to, int m)
        {
                int[][] g = new int[n][];
                int[] p = new int[n];
                for(int i = 0;i < m;i++)p[from[i]]++;
                for(int i = 0;i < n;i++)g[i] = new int[p[i]];
                for(int i = 0;i < m;i++){
                        g[from[i]][--p[from[i]]] = to[i];
                }
                return g;
        }
        // finds the distance from vertex v to all nodes in the graph
        // this is stored in the array dis
        // where dis[i]  = distance from v to i
        private void dist(int[][] g, int v, int d, int[] dis, int p) {
                dis[v] = d;
                for (int to : g[v]) {
                        if (to != p) {
                                dist(g, to, d + 1, dis, v);
                        }
                }

        }
        private int findMax(int[] a)
        {
                int index = -1;
                for(int i = 0 ; i < a.length ; i++)
                {
                        if(a[i]!= 1000 &&(index==-1 || a[index]<a[i]))
                                index = i;
                }
                return index;
        }
        private boolean dfsPath(int[][] g , int v, int to, int p, ArrayList<Integer> path)
        {
                if(v == to)
                {
                        path.add(to);
                        return true;
                }
                path.add(v);
                for(int u: g[v])
                {
                        if(p!=u && dfsPath(g,u,to,v,path))
                                return true;
                }
                path.remove(path.size()-1);
                return false;
        }
        public static int[][] parents3(int[][] g, int root) {
                int n = g.length;
                int[] par = new int[n];
                Arrays.fill(par, -1);

                int[] depth = new int[n];
                depth[0] = 0;

                int[] q = new int[n];
                q[0] = root;
                for (int p = 0, r = 1; p < r; p++) {
                        int cur = q[p];
                        for (int nex : g[cur]) {
                                if (par[cur] != nex) {
                                        q[r++] = nex;
                                        par[nex] = cur;
                                        depth[nex] = depth[cur] + 1;
                                }
                        }
                }
                return new int[][] { par, q, depth };
        }
        private int[] findCenters(int v,int[] dis)
        {
                Arrays.fill(dis,1000);
               // dfsDist(g,v,0,dis,-1);
                v = findMax(dis);
                //dfsDist(g,v,0,dis,-1);
                int to = findMax(dis);
                //path.clear();
                //dfsPath(g,v,to,-1,path);
                //if path.size is even returnn new int[]{two path mid elements}
                //else return new int[]{path mid elemen}
                return new int[]{0,1};


        }
        private int dfsSize(int v,int p,int[][] g)
        {
                int ans = 1;
                for(int to:g[v])
                {
                        if(to==p)
                                continue;
                        ans+=dfsSize(to,v,g);
                }
                return  ans;
        }
        public static  void main(String[] args)
        {
                Graph g = new Graph();
                int[][] a = g.DirectedGraph(7,new int[]{0,0,0,1,1,2},new int[]{1,2,6,3,4,5},6);
                for(int i = 0 ; i<7; i++)
                {
                        System.out.print(i+"     ");
                        for(int j=0 ; j<a[i].length; j++)
                        {
                                System.out.print(a[i][j]+ " ");
                        }
                        System.out.println();
                }
                int[] dis = new int[7];
                Arrays.fill(dis,7+3/* we should use n+3*/);
                g.dist(a,0,0,dis,-1);
                Arrays.stream(dis)
                        .forEach((n)->System.out.print(n+" "));
        }
}
