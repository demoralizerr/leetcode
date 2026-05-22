class Solution {
    public int longestOnes(int[] nums, int k) {
        int n = nums.length;
        int flips = k;
        int left = 0;
        int maxlen = 0;
        int currlen = 0;
        for (int right = 0; right < n; right++) {
            if (nums[right] == 1)
                currlen++;

            int numzero = right - left + 1 - currlen;
            
            while (numzero > k) {
                if (nums[left] == 1)
                    currlen--;
                left++;
                numzero = right - left + 1 - currlen;
            }

            maxlen = Math.max(right - left + 1, maxlen);

        }
        return Math.max(n - left, maxlen);
    }
}