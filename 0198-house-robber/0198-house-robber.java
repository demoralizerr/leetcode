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
    
    //Bottom up DP
    public int solveBottomUp(int[] nums){
    //let dp[i] be max money we can rob till house i
    
       int n = nums.length;

       if (n == 1) return nums[0];
       int[] dp = new int[n+1];
       dp[0] = nums[0];
       dp[1] = Math.max(nums[0], nums[1]);

       for(int i=2;i<n;i++){
          dp[i] = Math.max(dp[i-1], nums[i] + dp[i-2]);
       }
         return dp[n-1];
    }

    public int rob(int[] nums) {
        int[] dp = new int[nums.length + 1];
        Arrays.fill(dp, -1);
        return solveBottomUp(nums);
    }
}