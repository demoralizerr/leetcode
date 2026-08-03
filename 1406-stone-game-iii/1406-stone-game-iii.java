class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        Integer[] dp = new Integer[n + 1];

        int diff = solve(0, stoneValue, dp);

        if (diff > 0) return "Alice";
        if (diff < 0) return "Bob";
        return "Tie";
    }

    private int solve(int i, int[] stoneValue, Integer[] dp) {
        if (i >= stoneValue.length) return 0;

        if (dp[i] != null) return dp[i];

        int take = 0;
        int best = Integer.MIN_VALUE;

        for (int k = 0; k < 3 && i + k < stoneValue.length; k++) {
            take += stoneValue[i + k];
            best = Math.max(best, take - solve(i + k + 1, stoneValue, dp));
        }

        return dp[i] = best;
    }
}