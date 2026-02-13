class Solution {

    private int[][] grid;
    private boolean[][] visited;
    private int rows;
    private int cols;

    public int islandPerimeter(int[][] grid) {
        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        this.visited = new boolean[rows][cols];
        int perim = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1)
                    return dfs(i, j);
            }
        }
        return 0;
    }

    private int dfs(int row, int col) {
        if (row >= rows || col >= cols || row < 0 || col < 0 || grid[row][col] == 0)
            return 1;

        //if visited then return
        if (visited[row][col])
            return 0;

        //otherwise add in visited
        visited[row][col] = true;

        return dfs(row + 1, col) + dfs(row - 1, col) + dfs(row, col + 1) + dfs(row, col - 1);
        // TC - 0(n*m)
    }
}