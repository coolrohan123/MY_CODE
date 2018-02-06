package ED36;

import FastScanner.Competitive;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.util.Arrays;
import java.util.InputMismatchException;

public class I_ImplementedD {
        String INPUT = "4 5\n" +
                "1 3\n" +
                "3 2\n" +
                "2 1\n" +
                "3 4\n" +
                "4 1";

        void solve()
        {
                int n = i(), m = i();
                int[] source = new int[m];
                int[] dest   = new int[m];
                for(int i = 0 ; i<m ;i++)
                {
                        source[i] = i()-1;
                        dest[i]   = i()-1;
                }
                int[][] graph = DirectedGraph(n, source, dest);
                if(topologicalSort(graph)!=null)
                {
                        out.println("YES");
                        return;
                }
                int[] first_cycle = null;
                for(int i = 0 ; i<n ; i++)
                {
                        first_cycle = find_cycle_using_dfs(i,0,graph,new int[n+5],new boolean[n],new boolean[n]);
                        if(first_cycle!=null)
                                break;
                }
                for(int i = 0 , j =1 ; i<first_cycle.length ; i++,j++)
                {
                        if(j== first_cycle.length)
                        {
                                j = 0;
                        }
                        if(topologicalSortWithoutIJ(graph,first_cycle[i],first_cycle[j])!= null)
                        {
                                out.println("YES");
                                return;
                        }
                }
                out.println("NO");

        }

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
        private int[] topologicalSort(int[][] graph)
        {
                int n = graph.length;
                int[] result = new int[n];
                int[] in_degree = new int[n];
                // stores the in_degree of every node
                for(int i = 0 ; i<n ; i++)
                {
                        for(int dest : graph[i]) in_degree[dest]++;
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
        private int[][] DirectedGraph(int n, int[] source, int[] dest) {
                int m = source.length;
                int[][] graph = new int[n][];
                int[] freq = new int[n];
                for(int i = 0 ; i < m ; i++) freq[source[i]]++;
                for(int i = 0 ; i < n ; i++) graph[i] = new int[freq[i]];
                for(int i = 0 ; i < m ; i++)
                {
                        graph[source[i]][--freq[source[i]]] = dest[i];
                }
                return graph;
        }

        ///////////////////////////////////////
        void run() throws Exception{
                is = oj ? System.in: new ByteArrayInputStream(INPUT.getBytes());
                //is = System.in;
                out = new PrintWriter(System.out);
                long s = System.currentTimeMillis();
                solve();
                out.flush();
                tr(System.currentTimeMillis()-s+"ms");
        }
        public static void main(String[] args)throws Exception {
                new I_ImplementedD().run();
        }
        InputStream is;
        PrintWriter out;
        private byte[] inbuf = new byte[1024];
        public int lenbuf = 0, ptrbuf = 0;

        private int readByte()
        {
                if(lenbuf == -1)throw new InputMismatchException();
                if(ptrbuf >= lenbuf){
                        ptrbuf = 0;
                        try { lenbuf = is.read(inbuf); } catch (IOException e) { throw new InputMismatchException(); }
                        if(lenbuf <= 0)return -1;
                }
                return inbuf[ptrbuf++];
        }

        private boolean isSpaceChar(int c) { return !(c >= 33 && c <= 126); }
        private int skip() { int b; while((b = readByte()) != -1 && isSpaceChar(b)); return b; }
        private int i()
        {
                int num = 0, b;
                boolean minus = false;
                while((b = readByte()) != -1 && !((b >= '0' && b <= '9') || b == '-'));
                if(b == '-'){
                        minus = true;
                        b = readByte();
                }

                while(true){
                        if(b >= '0' && b <= '9'){
                                num = num * 10 + (b - '0');
                        }else{
                                return minus ? -num : num;
                        }
                        b = readByte();
                }
        }
        private boolean oj = System.getProperty("ONLINE_JUDGE") != null;
        private void tr(Object... o) { if(!oj)System.out.println(Arrays.deepToString(o)); }

}
