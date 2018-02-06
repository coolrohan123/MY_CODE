package DataStructure;

import java.util.*;
import java.io.*;

public class SuffixArray {
        private Suffix[] suff;
        public SuffixArray(){

        }
        public SuffixArray(String t)
        {
                int n = t.length();
                this.suff = new Suffix[n];
                for (int i = 0; i < n; i++) {
                        suff[i] = new Suffix(t,i);
                }
                Arrays.sort(suff);
        }
        private class Suffix implements Comparable<Suffix>
        {
                private final String t;
                private final int ind;
                private Suffix(String t,int ind)
                {
                        this.t = t;
                        this.ind = ind;
                }
                private int length()
                {
                        return t.length()-ind;
                }
                private char charAt(int i)
                {
                        return t.charAt(ind+i);
                }

                @Override
                public int compareTo(Suffix that) {
                        if(this==that) return 0;
                        int n = Math.min(this.length(),that.length());
                        for (int i = 0; i < n; i++) {
                                if(this.charAt(i)<that.charAt(i)) return -1;
                                if(this.charAt(i)>that.charAt(i)) return 1;
                        }
                        return this.length()-that.length();
                }
                public String toString()
                {
                        return t.substring(ind);
                }
        }
        public int length()
        {
                return suff.length;
        }


        public int index(int i){
                if(i<0 || i>= suff.length) throw new IllegalArgumentException();
                return suff[i].ind;
        }

        public int lcp(int i)
        {
                if(i<1 || i>=suff.length) throw new IllegalArgumentException();
                return lcpSuffix(suff[i],suff[i-1]);
        }
        private int lcpSuffix(Suffix s,Suffix t)
        {
                int n = Math.min(s.length(),t.length());
                for (int i = 0; i < n; i++) {
                        if(s.charAt(i)!=t.charAt(i)) return i;
                }
                return n;
        }

        private static int compare(String query,Suffix suff)
        {
                int n = Math.min(query.length(),suff.length());
                for (int i = 0; i < n; i++) {
                        if(query.charAt(i)<suff.charAt(i)) return -1;

                        if(query.charAt(i)>suff.charAt(i)) return +1;
                }
                return query.length()-suff.length();
        }
}
