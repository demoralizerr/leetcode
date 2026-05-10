class Solution {

    public int lps(String s, int l, int r, int[][] dp) {
        //base case
        if (s.length() == 0 || l>r)
            return 0;

        if (s.length() == 1 || l==r)
            return 1;

        if (dp[l][r] != -1)
            return dp[l][r];

        int match = 0;
        int notmatch = 0;
        if (s.charAt(l) == s.charAt(r)) {
            match = 2 + lps(s, l + 1, r - 1, dp);
        } else {
            notmatch = Math.max(lps(s, l, r - 1, dp), lps(s, l + 1, r, dp));
        }

        dp[l][r] = Math.max(match, notmatch);
        return dp[l][r];

    }

    public int longestPalindromeSubseq(String s) {
        int n = s.length();
        int[][] dp = new int[n + 1][n + 1];
        for (int[] row : dp) {
            Arrays.fill(row, -1);
        }

        int ans = lps(s, 0, n - 1, dp);
        return ans;
    }
}