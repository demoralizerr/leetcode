class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(nums, 0, dp);
    }

    public int solve(int[] nums, int index, int[] dp) {

        if (index == nums.length - 1)
            return 0;

        if (index >= nums.length || nums[index] == 0)
            return Integer.MAX_VALUE;

        if (dp[index] != -1)
            return dp[index];

        int totalways = 0;
        int minjump = Integer.MAX_VALUE;
        for (int i = 1; i <= nums[index]; i++) {
            totalways = solve(nums, index + i, dp);
            if (totalways != Integer.MAX_VALUE)
                minjump = Math.min(minjump, 1 + totalways);
        }
        dp[index] = minjump;
        return dp[index];
    }
}