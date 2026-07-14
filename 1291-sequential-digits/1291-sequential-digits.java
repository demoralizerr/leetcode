class Solution {
    public List<Integer> sequentialDigits(int low, int high) {
        //123-> len=3 digit =1  (digit+0)*pow(10,2) + digit+1*pow(10,1) + digit+2*pow(10,0) = 100+20+3
        //next -> (digit+1)*pow(10,len-1) chekc if it is within len then continue otherwise break;

        //start from low make next valid number add it in anslist 
        // jump to next start number and do above step1 func till next start is within high number 
        // atlast check if high is valid or not and add it in ans 

        int digit = 0;
        int nextstart = low;
        List<Integer> ans = new ArrayList<>();
        while (nextstart <= high) {
            String str = String.valueOf(nextstart);
            int len = str.length();
            len--;
            digit = str.charAt(0) - '0';
            int number = 0;
            int idx = 0;
            boolean isvalid = true;
            while (len >= 0) {
                if (digit + idx >= 10) {
                    isvalid = false;
                    break;
                }
                number += (digit + idx++) * (Math.pow(10, len--));
            }

            if (isvalid && number >= low && number <= high)
                ans.add(number);
            digit++;
            nextstart = (digit) * (int) (Math.pow(10, str.length() - 1));
        }
        return ans;

    }
}