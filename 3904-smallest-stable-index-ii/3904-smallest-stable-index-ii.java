class Solution {
    public int firstStableIndex(int[] nums, int k) {
        int n = nums.length;
        int[] maxarr = precomputemax(nums);
        int[] minarr = precomputemin(nums);
        for (int i = 0; i < n; i++) {
            int score = maxarr[i] - minarr[i];
            if (score <= k)
                return i;
        }
        return -1;
    }

    public int[] precomputemax(int[] nums) {
        int[] maxarr = new int[nums.length];
        int max = Integer.MIN_VALUE;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] > max)
                max = nums[i];
            maxarr[i] = max;
        }
        return maxarr;
    }

    public int[] precomputemin(int[] nums) {
        int[] minarr = new int[nums.length];
        int min = Integer.MAX_VALUE;
        for (int i = nums.length - 1; i >= 0; i--) {
            if (nums[i] < min)
                min = nums[i];
            minarr[i] = min;
        }

        return minarr;
    }
}