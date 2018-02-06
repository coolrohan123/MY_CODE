package DataStructure;

public class ZAlgorithm
{
        private int[] calculateZ(char[] input_array)
        {
                int z[] = new int[input_array.length];
                int left = 0 , right = 0;
                for(int k = 1; k<input_array.length ; k++)
                {
                        if(k>right)
                        {
                                left = right = k;
                                while(right<input_array.length && input_array[right]==input_array[right-left])
                                        right++;
                                z[k] = right-left;
                                right--;
                        }
                        else
                        {
                                int k1 = k-left;
                                if(z[k1]<right-k+1)
                                {
                                        z[k] = z[k1];
                                }
                                else
                                {
                                        left = k;
                                        while(right<input_array.length && input_array[right] == input_array[right-left])
                                                right++;
                                        z[k]  = right-left;
                                        right--;
                                }
                        }

                }
                return z;
        }
}