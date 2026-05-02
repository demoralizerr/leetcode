class Solution {

    //DP + memoization
    public int solve(int[] nums, int idx, int[] dp) {
        //base case
        if (idx >= nums.length)
            return 0;

        if (dp[idx] != -1)
            return dp[idx];

        //pick
        int pick = nums[idx] + solve(nums, idx + 2, dp);
        //notpick
        int notpick = solve(nums, idx + 1, dp);

        dp[idx] = Math.max(pick, notpick);
        return dp[idx];
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        return solve(nums, 0, dp);
    }
}