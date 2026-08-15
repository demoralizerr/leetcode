class Solution {
    public int longestSubsequence(int[] nums) {
        //if totalxor=0 then return n-1 length
        // if totalxor=0 and all items are 0 then return 0 length
        // if totalxor != 0 then return n length
        int n = nums.length;
        int totalxor = 0;
        boolean allzero = true;

        for (int num : nums) {
            if (num != 0)
                allzero = false;
            totalxor = totalxor ^ num;
        }

        if (allzero)
            return 0;

        return totalxor == 0 ? n - 1 : n;
    }
}