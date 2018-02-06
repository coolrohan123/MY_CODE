package Algorithm;

import java.util.ArrayList;
import java.util.Arrays;

public class Seive{
        int N =(int)1e6+10;
        ArrayList<Integer> primes = new ArrayList<>();
        boolean[] composite = new boolean[N];
        long[] totient = new long[N];
        int[] mobius = new int[N];
        int[] store = new int[N];
        // stores the smallest prime that divides number i
        private void linearSieveStoresFirstPrime()
        {
                Arrays.fill(store,0);
                store[1] = 1;
                for(int i = 2 ; i<N ;i++)
                {
                        if(store[i]==0){
                                primes.add(i);
                                store[i] = i;
                        }
                        for(int j = 0 ; j<primes.size() && i*primes.get(j) <N && primes.get(j)<=store[i] ; j++)
                        {
                                store[i*primes.get(j)] = primes.get(j);
                                if(i%primes.get(j)==0) break;
                        }
                }
        }
        private void mobius()
        {
                mobius[1] = 1;
                Arrays.fill(composite,false);
                for(int i = 2 ; i<N ;i++)
                {
                        if(!composite[i])
                        {
                                primes.add(i);
                                mobius[i] = 1;
                        }
                        for(int  j = 0 ; j < primes.size() && i*primes.get(j) <N  ; j++)
                        {
                                composite[i*primes.get(j)] = true;
                                if( i%primes.get(j) == 0)
                                {
                                        mobius[i*primes.get(j)] = 0;
                                        break;
                                }
                                else
                                {
                                        mobius[i*primes.get(j)] = mobius[i] * mobius[primes.get(j)];
                                }
                        }
                }
        }
        private void linearSieve()
        {
                Arrays.fill(composite,false);
                for(int i = 2 ; i<N ;i++)
                {
                        if(!composite[i]) primes.add(i);
                        for(int j = 0 ; j<primes.size() && i*primes.get(j) <N ; j++)
                        {
                                composite[i*primes.get(j)] = true;
                                if(i%primes.get(j)==0) break;
                        }
                }
        }
        private void totientFunction()
        {
                Arrays.fill(composite,false);
                for(int i = 2 ; i<N ;i++)
                {
                        if(!composite[i])
                        {
                                primes.add(i);
                                totient[i] = i-1;
                        }
                        for(int  j = 0 ; j < primes.size() && i*primes.get(j) <N ; j++)
                        {
                                composite[i*primes.get(j)] = true;
                                if( i%primes.get(j) == 0)
                                {
                                        totient[i*primes.get(j)] = totient[i]*primes.get(j);
                                        break;
                                }
                                else
                                {
                                        totient[i*primes.get(j)] = totient[i] * totient[primes.get(j)];
                                }
                        }
                }
        }
}