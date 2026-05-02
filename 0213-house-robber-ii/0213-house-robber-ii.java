class Solution {

    public int solve(int[] nums, int[] dp, int idx, int n) {
        if (idx > n) return 0;

        if (dp[idx] != -1) return dp[idx];

        int pick = nums[idx] + solve(nums, dp, idx + 2, n);
        int notpick = solve(nums, dp, idx + 1, n);

        return dp[idx] = Math.max(pick, notpick);
    }

    public int rob(int[] nums) {
        int n = nums.length;

        if (n == 1) return nums[0];

        int[] dp1 = new int[n];
        int[] dp2 = new int[n];
        Arrays.fill(dp1, -1);
        Arrays.fill(dp2, -1);

        return Math.max(
            solve(nums, dp1, 0, n - 2), // exclude last
            solve(nums, dp2, 1, n - 1)  // exclude first
        );
    }
}