class Solution {
    public int orangesRotting(int[][] grid) {
        //for each position check in 4 directions for fresh orange and rot them
        int rows = grid.length;
        int cols = grid[0].length;
        int time = 0;
        Queue<int[]> q = new ArrayDeque<>();
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 2) {
                    q.offer(new int[] { i, j });
                }
            }
        }

        if (q.size() > 0)
            time = bfs(q, grid, rows, cols);

        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (grid[i][j] == 1) {
                    time = -1;
                    break;
                }
            }
        }
        return time;
    }

    private int bfs(Queue<int[]> q, int[][] grid, int rows, int cols) {
        int time = -1;
        while (!q.isEmpty()) {
            int len = q.size();
            for (int i = 0; i < len; i++) {
                int[] curr = q.poll();
                int row = curr[0];
                int col = curr[1];
                if (row + 1 < rows && grid[row + 1][col] == 1) {
                    grid[row + 1][col] = 2;
                    q.offer(new int[] { row + 1, col });
                }

                if (row - 1 >= 0 && grid[row - 1][col] == 1) {
                    grid[row - 1][col] = 2;
                    q.offer(new int[] { row - 1, col });
                }

                if (col + 1 < cols && grid[row][col + 1] == 1) {
                    grid[row][col + 1] = 2;
                    q.offer(new int[] { row, col + 1 });
                }

                if (col - 1 >= 0 && grid[row][col - 1] == 1) {
                    grid[row][col - 1] = 2;
                    q.offer(new int[] { row, col - 1 });
                }

            }
            time++;
        }
        return time;
    }
}