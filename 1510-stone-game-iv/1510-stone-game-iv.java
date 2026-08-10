class Solution {
    Boolean[] dp;

    public boolean winnerSquareGame(int n) {
        this.dp = new Boolean[n + 1];
        return solve(n);
    }

    public boolean solve(int n) {

        if (n == 0)
            return false;

        if (dp[n] != null)
            return dp[n];

        for (int k = 1; k * k <= n; k++) {
            if (solve(n - (k * k)) == false) // call for bob if false means bob lose
                return dp[n] = true;
        }

        return dp[n] = false; // otherwise alice could never win
    }
}