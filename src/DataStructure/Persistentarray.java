package DataStructure;

import java.util.Arrays;

public class Persistentarray {
        static class PersistentArray {
                public int K = 2; // 2^K-partitioned tree
                public int[] shifts; // height*K
                public int[] vals;
                public int[][] child; // [child-number][parent]
                public int idgen;
                public int[] entries;
                public int eidgen;
                public static int I = Integer.MAX_VALUE;
                public int n;

                public PersistentArray(int[] a, int Q) {
                        n = a.length;
                        int m = 0;
                        int dep = 0;
                        for(int u = n;u > 0;u>>>=K){
                                m += u+1;
                                dep++;
                        }
                        m += Q*(dep+1);
                        vals = new int[m];
                        child = new int[1<<K][m];
                        entries = new int[Q+1];
                        shifts = new int[m];
                        eidgen = 0;
                        idgen = 0;
                        for(int i = 0;i < 1<<K;i++)Arrays.fill(child[i], -1);
                        Arrays.fill(entries, -1);

                        for(int i = 0;i < a.length;i++){
                                vals[idgen] = a[i];
                                shifts[idgen] = 0;
                                idgen++;
                        }
                        int tail = 0;
                        while(true){
                                int head = idgen;
                                if(head-tail <= 1)break;
                                int nshift = shifts[tail] + K;
                                while(tail < head){
                                        vals[idgen] = I;
                                        shifts[idgen] = nshift;
                                        for(int i = 0;i < 1<<K && tail < head;i++, tail++){
                                                child[i][idgen] = tail;
                                        }
                                        idgen++;
                                }
                                tail = head;
                        }
                        entries[eidgen++] = idgen-1;
                }

                public PersistentArray(int val, int n, int Q) {
                        this.n = n;
                        int m = 0;
                        int dep = 0;
                        for(int u = n;u > 0;u>>>=K){
                                m += u+1;
                                dep++;
                        }
                        m += Q*(dep+1);
                        m -= n-1;
                        vals = new int[m];
                        child = new int[1<<K][m];
                        entries = new int[Q+1];
                        shifts = new int[m];
                        eidgen = 0;
                        idgen = 0;
                        for(int i = 0;i < 1<<K;i++)Arrays.fill(child[i], -1);
                        Arrays.fill(entries, -1);

                        vals[idgen] = val;
                        shifts[idgen] = 0;
                        idgen++;

                        int tail = -n+1;
                        while(true){
                                int head = idgen;
                                if(head-tail <= 1)break;
                                int nshift = shifts[Math.max(0, tail)] + K;
                                while(tail < head){
                                        vals[idgen] = I;
                                        shifts[idgen] = nshift;
                                        for(int i = 0;i < 1<<K && tail < head;i++, tail++){
                                                child[i][idgen] = Math.max(0, tail);
                                        }
                                        idgen++;
                                }
                                tail = head;
                        }
                        entries[eidgen++] = idgen-1;
                }

                public int get(int eid, int pos)
                {
                        int cur = entries[eid];
                        while(shifts[cur] > 0){
                                cur = child[pos>>>shifts[cur]-K&(1<<K)-1][cur];
                        }
                        return vals[cur];
                }

                public int set(int eid, int pos, int value)
                {
                        entries[eidgen] = setdfs(entries[eid], pos, value);
                        return eidgen++;
                }

                private int clone(int cur)
                {
                        vals[idgen] = vals[cur];
                        shifts[idgen] = shifts[cur];
                        for(int i = 0;i < 1<<K;i++){
                                child[i][idgen] = child[i][cur];
                        }
                        return idgen++;
                }

                private int setdfs(int cur, int pos, int value)
                {
                        int cloned = clone(cur);
                        if(shifts[cloned] > 0){
                                int chind = pos>>>shifts[cloned]-K&(1<<K)-1;
                                child[chind][cloned] = setdfs(child[chind][cloned], pos, value);
                        }else{
                                vals[cloned] = value;
                        }
                        return cloned;
                }

                public String toString(int eid) {
                        int[] vals = new int[n];
                        for(int i = 0;i < n;i++){
                                vals[i] = get(eid, i);
                        }
                        return Arrays.toString(vals);
                }
        }
}
