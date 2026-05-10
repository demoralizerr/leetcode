class Solution {

    public int topDownDP(int[] coins, int amount, int[] dp) {
        if (amount < 0)
            return Integer.MAX_VALUE;

        //base case
        if (amount == 0)
            return 0;

        //check in dp
        if (dp[amount] != -1)
            return dp[amount];

        int mincoins = Integer.MAX_VALUE;
        for (int coin : coins) {
            int numcoins = topDownDP(coins, amount - coin, dp);
            if (numcoins != Integer.MAX_VALUE)
                mincoins = Math.min(mincoins, 1 + numcoins);
        }

        dp[amount] = mincoins;
        return mincoins;

    }

    public int coinChange(int[] coins, int amount) {
        int n = coins.length;
        int[] dp = new int[amount + 1];
        Arrays.fill(dp, -1);
        int ans = topDownDP(coins, amount, dp);
        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}