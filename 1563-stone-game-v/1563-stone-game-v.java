class Solution {
    public int stoneGameV(int[] stoneValue) {
        int n = stoneValue.length;
        long[] prefix = new long[n + 1];
        for (int i = 0; i < n; i++) {
            prefix[i + 1] = prefix[i] + stoneValue[i];
        }

        long[][] dp = new long[n][n];

        for (int len = 2; len <= n; len++) {
            for (int i = 0; i + len - 1 < n; i++) {
                int j = i + len - 1;
                long best = 0;
                for (int k = i; k < j; k++) {
                    long left = prefix[k + 1] - prefix[i];
                    long right = prefix[j + 1] - prefix[k + 1];

                    if (left < right) {
                        best = Math.max(best, dp[i][k] + left);
                    } else if (left > right) {
                        best = Math.max(best, dp[k + 1][j] + right);
                    } else {
                        best = Math.max(best, Math.max(dp[i][k], dp[k + 1][j]) + left);
                    }
                }
                dp[i][j] = best;
            }
        }

        return (int) dp[0][n - 1];
    }
}