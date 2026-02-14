class Solution {
    private char[][] grid;
    private int rows;
    private int cols;
    private boolean[][] visited;

    public int numIslands(char[][] grid) {

        this.grid = grid;
        this.rows = grid.length;
        this.cols = grid[0].length;
        visited = new boolean[rows][cols];
        int islands = 0;
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == '1') {
                    if (dfs(i, j) == 1)
                        islands++;
                }
            }
        }
        return islands;

    }

    private int dfs(int row, int col) {
        if (row >= rows || col >= cols || row < 0 || col < 0 || grid[row][col] == '0')
            return 0;

        if (visited[row][col])
            return 0;

        visited[row][col] = true;

        dfs(row + 1, col);
        dfs(row - 1, col);
        dfs(row, col + 1);
        dfs(row, col - 1);

        return 1;

    }
}