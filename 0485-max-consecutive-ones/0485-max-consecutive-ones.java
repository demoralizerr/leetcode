class Solution {
    public int findMaxConsecutiveOnes(int[] nums) {
        int n = nums.length;
        int l = 0;
        int maxlen = 0;
        int currlen = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] == 1)
                currlen++;

            if (nums[right] == 0) {
                maxlen = Math.max(maxlen, currlen);
                currlen = 0;
                l = right;
            }

        }
        return Math.max(currlen, maxlen);
    }
}