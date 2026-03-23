class Solution {
    int[][] dirs = {{1,0}, {-1,0}, {0,1}, {0,-1}};
    int rows, cols;

    public int minimumEffortPath(int[][] heights) {
        rows = heights.length;
        cols = heights[0].length;

        int low = 0, high = 1_000_000, ans = high;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            boolean[][] visited = new boolean[rows][cols];

            if (dfs(0, 0, heights, visited, mid)) {
                ans = mid;
                high = mid - 1; // try smaller effort
            } else {
                low = mid + 1; // need bigger effort
            }
        }

        return ans;
    }

    private boolean dfs(int r, int c, int[][] heights, boolean[][] visited, int maxEffort) {
        if (r == rows - 1 && c == cols - 1) return true;

        visited[r][c] = true;

        for (int[] d : dirs) {
            int nr = r + d[0];
            int nc = c + d[1];

            if (nr >= 0 && nc >= 0 && nr < rows && nc < cols && !visited[nr][nc]) {
                int diff = Math.abs(heights[nr][nc] - heights[r][c]);

                if (diff <= maxEffort && dfs(nr, nc, heights, visited, maxEffort)) {
                    return true;
                }
            }
        }

        return false;
    }
}