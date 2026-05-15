class Solution {
    public int minCapability(int[] nums, int k) {
        //return solve(nums, k, 0, 0, 0);
        int n = nums.length;

        int l = Integer.MAX_VALUE;
        int r = Integer.MIN_VALUE;
        for (int x : nums) {
            l = Math.min(x, l);
            r = Math.max(x, r);
        }
        while (l < r) {
            int guess = l + (r - l) / 2;
            if (isPossible(nums, guess, k)) {
                r = guess;
            } else
                l = guess + 1;
        }
        return r;

    }

    public boolean isPossible(int[] nums, int guess, int k) {
        int n = nums.length;
        int count = 0;
        for (int i = 0; i < n; i++) {
            if (nums[i] <= guess) {
                count++;
                i++;
            }
        }
        return count >= k;
    }

    public int solve(int[] nums, int k, int currindex, int robbed, int lastrobmax) {

        if (robbed >= k)
            return lastrobmax;

        if (currindex >= nums.length)
            return Integer.MAX_VALUE;

        int rob = solve(nums, k, currindex + 2, robbed + 1, Math.max(lastrobmax, nums[currindex]));

        int notrob = solve(nums, k, currindex + 1, robbed, lastrobmax);

        return Math.min(rob, notrob);

    }
}