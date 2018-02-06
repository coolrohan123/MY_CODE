package DataStructure;

import java.util.Arrays;

public class PersistentdynamicSegTree {
        public static class PersistentDynamicSegmentTreeRSQSimple {
                public int[] vals;
                public int[] ls;
                public int[] rs;
                public int[] entries;
                public int gen;
                public int height;
                public int egen;

                public PersistentDynamicSegmentTreeRSQSimple(int q, int height)
                {
                        int bufsize = q * (height+1) + 1;
                        this.vals = new int[bufsize];
                        this.ls = new int[bufsize];
                        this.rs = new int[bufsize];
                        this.entries = new int[q+1];
                        Arrays.fill(this.ls, -1);
                        Arrays.fill(this.rs, -1);
                        this.height = height;
                        gen = 0;
                        egen = 0;
                        vals[gen++] = 0;
                        entries[egen++] = 0;
                }

                public void add(long x, int v)
                {
                        entries[egen] = addDfs(entries[egen-1], x, v, height-1);
                        egen++;
                }

                public void add(int eid, long x, int v)
                {
                        entries[egen] = addDfs(entries[eid], x, v, height-1);
                        egen++;
                }

                private int clone(int id)
                {
                        vals[gen] = vals[id];
                        ls[gen] = ls[id];
                        rs[gen] = rs[id];
                        return gen++;
                }

                private int addDfs(int id, long x, int v, int d)
                {
                        id = id == -1 ? gen++ : clone(id);
                        if(d == -1){
                                vals[id] += v;
                        }else{
                                if(x<<~d<0){
                                        rs[id] = addDfs(rs[id], x, v, d-1);
                                }else{
                                        ls[id] = addDfs(ls[id], x, v, d-1);
                                }
                                propagate(id);
                        }
                        return id;
                }

                private int val(int id){ return id == -1 ? 0 : vals[id]; }
                private int L(int id){ return id == -1 ? -1 : ls[id]; }
                private int R(int id){ return id == -1 ? -1 : rs[id]; }

                private void propagate(int id)
                {
                        if(id == -1)return;
                        vals[id] = val(ls[id]) + val(rs[id]);
                }

                // K:0-indexed
                public long parallelKth(int e1, int e2, int K)
                {
                        int eid1 = entries[e1], eid2 = entries[e2];
                        if(K >= val(eid2)-val(eid1))return -1;
                        long val = 0;
                        for(int d = height-1;d >= 0;d--){
                                val = val<<1;
                                if(K >= val(L(eid2)) - val(L(eid1))){
                                        K -= val(L(eid2)) - val(L(eid1));
                                        eid1 = R(eid1); eid2 = R(eid2);
                                        val |= 1L;
                                }else{
                                        eid1 = L(eid1); eid2 = L(eid2);
                                }
                        }
                        // leaf value = val
                        // leaf freq = val(eid2)-val(eid1)
                        // k in leaf = K(0-indexed)
                        return val;
                }

		public long sum(int l, int r)
		{
			return sum(l, r, height-1, 0);
		}

		private long sum(int l, int r, int d, int id)
		{
			if(l <= 0 && r >= 1<<d+1){
				return vals[id];
			}else{
				// [l,r), [0,1<<d-1)
				long ret = 0;
				if(l < 1<<d && 0 < r && L(id) != -1){
					ret += sum(l, r, d-1, L(id));
				}
				if(l < 1<<d+1 && 1<<d < r && R(id) != -1){
					ret += sum(l-(1<<d), r-(1<<d), d-1, R(id));
				}
				return ret;
			}
		}

                public long sum(int eid, long l, long r)
                {
                        return sumDfs(entries[eid], l, r, 0L, 1L<<height);
                }

                private long sumDfs(int id, long l, long r, long cl, long cr)
                {
                        if(id == -1)return 0;
                        if(l <= cl && cr <= r){
                                return vals[id];
                        }else{
                                long ret = 0;
                                long mid = cl+cr>>>1;
                                if(cl < r && l < mid){
                                        ret += sumDfs(ls[id], l, r, cl, mid);
                                }
                                if(mid < r && l < cr){
                                        ret += sumDfs(rs[id], l, r, mid, cr);
                                }
                                return ret;
                        }
                }
        }
}
