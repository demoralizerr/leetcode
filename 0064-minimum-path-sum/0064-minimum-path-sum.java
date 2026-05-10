class Solution {

    public int topDownDP(int[][] grid, int r, int c, int[][] dp) {

        //base case
        if (r == grid.length - 1 && c == grid[0].length - 1)
            return grid[r][c];

        if (r == grid.length || c == grid[0].length)
            return Integer.MAX_VALUE;

        if (dp[r][c] != 0)
            return dp[r][c];

        int minsum = grid[r][c] + Math.min(topDownDP(grid, r, c + 1, dp), topDownDP(grid, r + 1, c, dp));
        dp[r][c] = minsum;
        return dp[r][c];
    }

    public int minPathSum(int[][] grid) {
        int r = grid.length;
        int c = grid[0].length;
        int[][] dp = new int[r + 1][c + 1];
        int ans = topDownDP(grid, 0, 0, dp);
        return ans;
    }
}