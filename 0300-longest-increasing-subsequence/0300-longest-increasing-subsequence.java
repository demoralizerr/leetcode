class Solution {
    public int lengthOfLIS(int[] nums) {
        int n = nums.length;
        if (n == 1)
            return 1;
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp)
            Arrays.fill(row, -1);
        return solve(nums, 0, -1, dp);
    }

    public int solve(int[] nums, int index, int previndex, int[][] dp) {

        if (index == nums.length)
            return 0;

        if (dp[index][previndex+1] != -1)
            return dp[index][previndex+1];

        int pick = 0;
        if (previndex == -1 || nums[index] > nums[previndex]) {
            pick = 1 + solve(nums, index + 1, index, dp);
        }

        int notpick = solve(nums, index + 1, previndex, dp);

        dp[index][previndex+1] = Math.max(pick, notpick);
        return dp[index][previndex+1];

    }
}