class Solution {
    private int[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;
    private int maxFish = Integer.MIN_VALUE;

    public int findMaxFish(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] > 0) {
                    maxFish = Math.max(maxFish, dfs(i, j));
                }
            }
        }
        return maxFish == Integer.MIN_VALUE ? 0 : maxFish;

    }

    private int dfs(int row, int col) {
        if (row < 0 || col < 0 || row == rows || col == cols || grid[row][col] == 0)
            return 0;

        if (visited[row][col])
            return 0;

        visited[row][col] = true;

        return grid[row][col] + dfs(row + 1, col) + dfs(row - 1, col) + dfs(row, col + 1) + dfs(row, col - 1);

    }
}