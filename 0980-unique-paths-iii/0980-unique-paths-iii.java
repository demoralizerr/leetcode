class Solution {

    int[][] moves = {
        {0,1},
        {0,-1},
        {1,0},
        {-1,0}
    };

    boolean[][] visited;

    public int uniquePathsIII(int[][] grid) {

        int rows = grid.length;
        int cols = grid[0].length;

        visited = new boolean[rows][cols];

        int startRow = 0;
        int startCol = 0;

        int emptyCells = 0;

        // count cells to visit
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {

                if (grid[r][c] != -1)
                    emptyCells++;

                if (grid[r][c] == 1) {
                    startRow = r;
                    startCol = c;
                }
            }
        }

        return dfs(grid, startRow, startCol, emptyCells);
    }

    public int dfs(int[][] grid, int r, int c, int remaining) {

        // invalid
        if (r < 0 || c < 0 ||
            r >= grid.length || c >= grid[0].length ||
            grid[r][c] == -1 ||
            visited[r][c]) {

            return 0;
        }

        // reached end
        if (grid[r][c] == 2) {

            // valid only if all cells visited
            return remaining == 1 ? 1 : 0;
        }

        visited[r][c] = true;

        int totalPaths = 0;

        for (int[] move : moves) {

            int nr = r + move[0];
            int nc = c + move[1];

            totalPaths += dfs(grid, nr, nc, remaining - 1);
        }

        // BACKTRACK
        visited[r][c] = false;

        return totalPaths;
    }
}