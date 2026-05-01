class Solution {
    public int minCostClimbingStairs(int[] cost) {
        // min cost to reach n = cost of stepping on current i + min cost to reach (n-1 or n-2)
        // dp[i] = cost[i] + min(dp[i-1],dp[i-2]);
        int prev1 = cost[0];
        int prev2 = cost[1];

        for(int i=2;i<cost.length;i++){
            int curr = cost[i] + Math.min(prev1,prev2);
            prev1 = prev2;
            prev2= curr;
        }

        return Math.min(prev1,prev2);
        
    }
}