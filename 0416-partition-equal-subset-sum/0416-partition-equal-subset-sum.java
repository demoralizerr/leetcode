class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums)
            sum += num;

        if (sum % 2 != 0)
            return false;

        Boolean[][] dp = new Boolean[n + 1][sum / 2 + 1];

        // return canPartition(nums, sum / 2, 0, dp);
        return canPartitionTabulation(nums, sum / 2);
    }

    public boolean canPartitionTabulation(int[] nums, int sum) {
        int n = nums.length;
        boolean[][] dp = new boolean[n + 1][sum + 1];

        for (int i = 0; i < n; i++) {
            dp[i][0] = true;
        }

        for (int index = n - 1; index >= 0; index--) {
            for (int summ = 1; summ <= sum; summ++) {
                boolean notpick = dp[index + 1][summ];
                boolean pick = false;
                if (summ - nums[index] >= 0) {
                    pick = dp[index+1][summ-nums[index]];
                }

                dp[index][summ] = pick || notpick;
            }
        }

        return dp[0][sum];

    }

    public boolean canPartition(int[] nums, int sum, int index, Boolean[][] dp) {
        if (index == nums.length || sum < 0)
            return false;

        if (sum == 0)
            return true;

        if (dp[index][sum] != null)
            return dp[index][sum];

        boolean pick = canPartition(nums, sum - nums[index], index + 1, dp);
        boolean notpick = canPartition(nums, sum, index + 1, dp);

        dp[index][sum] = pick || notpick;
        return dp[index][sum];
    }
}