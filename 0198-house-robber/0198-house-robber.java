class Solution {
    public int rob(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return solve(nums, 0,dp);
    }

    public int solve(int[] nums, int index, int[] dp) {

        if (index >= nums.length)
            return 0;

         if(dp[index]!=-1)
            return dp[index];   

        int rob = nums[index] + solve(nums, index + 2,dp);
        int notrob = solve(nums, index + 1,dp);

        dp[index] =  Math.max(rob, notrob);
        return dp[index];
    }
}