class Solution {
    public boolean canPartition(int[] nums) {
        int n = nums.length;
        int sum = 0;
        for (int num : nums)
            sum += num;

        if (sum % 2 != 0)
            return false;

        Boolean[][] dp = new Boolean[n + 1][sum / 2 + 1];

        return canPartition(nums, sum / 2, 0, dp);
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