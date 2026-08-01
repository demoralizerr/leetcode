class Solution {
    Integer[][] dp;

    public boolean predictTheWinner(int[] nums) {
        int n = nums.length;
        dp = new Integer[n][n];
        return solve(nums, 0, n - 1) >= 0;
    }

    private int solve(int[] nums, int low, int high) {
        if (low == high) {
            return nums[low];
        }

        if (dp[low][high] != null) {
            return dp[low][high];
        }

        int takeLeft = nums[low] - solve(nums, low + 1, high);
        int takeRight = nums[high] - solve(nums, low, high - 1);

        return dp[low][high] = Math.max(takeLeft, takeRight);
    }
}