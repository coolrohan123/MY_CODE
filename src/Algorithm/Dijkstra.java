package Algorithm;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Dijkstra {
        int N = (int)1e6;
        long[] dist = new long[N];
        ArrayList<Pair>[] al = new ArrayList[N+1];
        void dijkstra(int s)
        {
                dist[s] = 0;
                PriorityQueue<Pair> pq = new PriorityQueue<>();
                pq.add(new Pair(s,0));
                while(pq.size()>0)
                {
                        Pair curr = pq.remove();
                        int c_dest = curr.d;
                        Iterator<Pair> it = al[c_dest].iterator();
                        while(it.hasNext())
                        {
                                Pair temp = it.next();
                                {
                                        if (dist[temp.d] > dist[c_dest] + temp.w) {
                                                pq.add(new Pair(temp.d, dist[c_dest] + temp.w));
                                                dist[temp.d] = dist[c_dest] + temp.w;
                                        }
                                }

                        }
                }
        }
        public Dijkstra() {
                for(int i =0 ; i<=N ; i++)
                {
                        al[i] = new ArrayList<>();
                }
        }
        static class Pair implements Comparable<Pair>
        {
                int d; long w;

                public Pair(int d, long w) {
                        this.d = d;
                        this.w = w;
                }

                @Override
                public int compareTo(Pair o) {
                        return 0;
                }
        }
}
