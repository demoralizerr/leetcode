public class Solution {
    public int countServers(int[][] grid) {
        int ROWS = grid.length, COLS = grid[0].length;
        int[] row_cnt = new int[ROWS];
        int[] col_cnt = new int[COLS];

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == 1) {
                    row_cnt[i]++;
                    col_cnt[j]++;
                }
            }
        }

        int count = 0;
        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++) {
                if (grid[i][j] == 1 && Math.max(row_cnt[i], col_cnt[j]) > 1)
                    count++;
            }
        }

        return count;
    }
}