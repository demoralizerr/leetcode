class Solution {
    private int[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;

    public int maxAreaOfIsland(int[][] grid) {

        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        visited = new boolean[rows][cols];
        int maxarea = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    maxarea = Math.max(maxarea, dfs(i, j));
                }
            }
        }
        return maxarea;

    }

    private int dfs(int row, int col) {
        if (row >= rows || col >= cols || row < 0 || col < 0 || grid[row][col] == 0)
            return 0;

        //already visited
        if (visited[row][col])
            return 0;

        visited[row][col] = true;

        return 1 + dfs(row + 1, col) + dfs(row - 1, col) + dfs(row, col + 1) + dfs(row, col - 1);
    }
}