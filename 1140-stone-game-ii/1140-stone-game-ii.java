class Solution {
    int[][][] dp;

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        this.dp = new int[2][n + 1][n + 1];

        for (int i = 0; i < 2; i++) {
            for (int j = 0; j <= n; j++) {
                Arrays.fill(this.dp[i][j], -1);
            }
        }

        return solveForAlice(1, 0, 1, piles);
    }

    public int solveForAlice(int person, int index, int m, int[] piles) {
        int n = piles.length;
        if (index >= n)
            return 0;

        if (dp[person][index][m] != -1)
            return dp[person][index][m];

        int stones = 0;
        int result = person == 1 ? -1 : Integer.MAX_VALUE;
        for (int x = 1; x <= Math.min(2 * m, n - index); x++) {
            stones += piles[index + x - 1];
            if (person == 1) {
                result = Math.max(result, stones + solveForAlice(0, index + x, Math.max(m, x), piles));
            } else {
                result = Math.min(result, solveForAlice(1, index + x, Math.max(m, x), piles));
            }
        }

        return dp[person][index][m] = result;
    }
}