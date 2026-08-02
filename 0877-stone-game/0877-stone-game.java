class Solution {
    public boolean stoneGame(int[] piles) {
        int n = piles.length;
        int[][] dp = new int[501][501];
        for (int[] num : dp)
            Arrays.fill(num, -1);
        int totalscore = 0;
        for (int p : piles) {
            totalscore += p;
        }

        int alice_score = solve(piles, 0, n - 1, dp);
        return alice_score > totalscore / 2;
    }

    public int solve(int[] piles, int i, int j, int[][] dp) {
        if (i > j)
            return 0;

        if (dp[i][j] != -1)
            return dp[i][j];

        //alice moves
        int takei = piles[i] + Math.min(solve(piles, i + 2, j, dp), solve(piles, i + 1, j - 1, dp));
        int takej = piles[j] + Math.min(solve(piles, i, j - 2, dp), solve(piles, i + 1, j - 1, dp));

        return dp[i][j] = Math.max(takei, takej);

    }
}