class Solution {

    // recursive solution using reccurrence relation
    public int solve(int[] cost, int n) {
        //base case
        if (n == 0)
            return cost[0];
        if (n == 1)
            return cost[1];

        int ans = cost[n] + Math.min(solve(cost, n - 1), solve(cost, n - 2));
        return ans;
    }

    // dp + memoization solution
    public int solve2(int[] cost, int n,int[] dp){
        //base case
        if (n == 0)
            return cost[0];
        if (n == 1)
            return cost[1];

         if(dp[n]!=-1)
            return dp[n];   

         dp[n] = cost[n] + Math.min(solve2(cost, n - 1,dp), solve2(cost, n - 2,dp));  
         return dp[n]; 

    }

    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        return Math.min(solve2(cost,n-1,dp),solve2(cost,n-2,dp));
    }
}