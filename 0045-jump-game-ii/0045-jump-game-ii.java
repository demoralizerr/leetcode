class Solution {
    public int jump(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
      //  return solve(nums, 0, dp);
      return solveTabulation(nums);
    }

    public int solveTabulation(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];

        if (n == 1)
            return 0;

        dp[n - 1] = 0;
        for (int index = n - 2; index >= 0; index--) {
            if (nums[index] == 0) {
                dp[index] = Integer.MAX_VALUE;
                continue;
            }
            int minjump = Integer.MAX_VALUE;
            for (int j = 1; j <= nums[index] && index + j < n; j++) {
                int totalways = dp[index + j];
                if (totalways != Integer.MAX_VALUE)
                    minjump = Math.min(minjump, 1 + totalways);
            }
            dp[index] = minjump;
        }
        return dp[0];

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