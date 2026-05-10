class Solution {

    public int countUniquePaths(int m, int n, int r, int c, int[][] dp) {
        //base case
        if (r == m - 1 && c == n - 1)
            return 1;

        if (r == m || c == n)
            return 0;

        //check in dp
        if (dp[r][c] != 0)
            return dp[r][c];

        //move right
        int right_moves = countUniquePaths(m, n, r, c + 1, dp);

        //move down 
        int down_moves = countUniquePaths(m, n, r + 1, c, dp);

        dp[r][c] = right_moves + down_moves;
        return dp[r][c];
    }

    public int uniquePaths(int m, int n) {
        int[][] dp = new int[m + 1][n + 1];
        int ans = countUniquePaths(m, n, 0, 0, dp);
        return ans;
    }
}