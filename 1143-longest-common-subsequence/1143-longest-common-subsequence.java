class Solution {

    public int lcs(String text1, int idx1, String text2, int idx2, int[][] dp) {
        //base case
        if (text1.length() == idx1 || text2.length() == idx2)
            return 0;

        if (dp[idx1][idx2] != -1)
            return dp[idx1][idx2];

        int match = 0;
        int notmatch = 0;
        if (text1.charAt(idx1) == text2.charAt(idx2)) {
            match = 1 + lcs(text1, idx1 + 1, text2, idx2 + 1,dp);
        } else {
            notmatch = Math.max(lcs(text1, idx1, text2, idx2 + 1, dp), lcs(text1, idx1 + 1, text2, idx2, dp));
        }

        dp[idx1][idx2] = Math.max(match, notmatch);
        return dp[idx1][idx2];
    }

    public int longestCommonSubsequence(String text1, String text2) {
        int n = text1.length();
        int m = text2.length();
        int[][] dp = new int[n + 1][m + 1];

        for (int[] row : dp)
            Arrays.fill(row, -1);

        int ans = lcs(text1, 0, text2, 0, dp);
        return ans;
    }
}