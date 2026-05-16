class Solution {
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int r = obstacleGrid.length;
        int c = obstacleGrid[0].length;

        int[][] dp = new int[r + 1][c + 1];
        return uniquePaths(obstacleGrid, 0, 0, dp);
    }

    public int uniquePaths(int[][] grid, int r, int c, int[][] dp) {

        //base case
        if (r == grid.length || c == grid[0].length || grid[r][c] == 1)
            return 0;

        if (r == grid.length - 1 && c == grid[0].length - 1)
            return 1;

        if (dp[r][c] != 0)
            return dp[r][c];

        int topmoves = uniquePaths(grid, r, c + 1, dp);
        int downmoves = uniquePaths(grid, r + 1, c, dp);

        dp[r][c] = topmoves + downmoves;
        return dp[r][c];
    }
}