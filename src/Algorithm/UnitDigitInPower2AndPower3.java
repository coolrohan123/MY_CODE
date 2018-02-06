package Algorithm;

public class UnitDigitInPower2AndPower3 {
        public static void main(String[] args) {
                String s = "1234567777";
                int[] two = {6,2,4,8};
                int[] three = {1,3,9,7};
                int[] four = {6,4};
                int x = s.length()==1?Integer.parseInt(s):Integer.parseInt(s.substring(s.length()-2));
                int r1 = x%4;
                int r2 = x%2;
                //two[r1] = contains the unit digit of power of 2^s
                //three[r1] = contains the unit digit of power of 3^s
        }
}
