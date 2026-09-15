class Solution {
    public int maxPalindromes(String s, int k) {
        int n = s.length();
        boolean[][] isPalin = new boolean[n][n];

        // Build palindrome table: isPalin[i][j] true if s[i..j] is a palindrome
        for (int i = n - 1; i >= 0; i--) {
            isPalin[i][i] = true;
            for (int j = i + 1; j < n; j++) {
                if (s.charAt(i) == s.charAt(j)) {
                    if (j - i < 2) {
                        isPalin[i][j] = true;
                    } else {
                        isPalin[i][j] = isPalin[i + 1][j - 1];
                    }
                }
            }
        }

        int[] dp = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            dp[i] = dp[i - 1]; // skip s[i-1]
            for (int len = k; i - len >= 0; len++) {
                int start = i - len;
                if (isPalin[start][i - 1]) {
                    dp[i] = Math.max(dp[i], dp[start] + 1);
                    break; // shortest valid palindrome ending here is always optimal
                }
            }
        }

        return dp[n];
    }
}