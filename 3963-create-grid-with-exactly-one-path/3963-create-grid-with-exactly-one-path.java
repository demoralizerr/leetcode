class Solution {
    public String[] createGrid(int m, int n) {
        char[][] grid = new char[m][n];

        for (char[] g : grid)
            Arrays.fill(g, '#');

        for (int j = 0; j < n; j++) {
            grid[0][j] = '.';
        }

        for (int j = 0; j < m; j++) {
            grid[j][n - 1] = '.';
        }

        String[] res = new String[m];
        for (int i = 0; i < m; i++) {
            res[i] = new String(grid[i]);
        }

        return res;
    }
}