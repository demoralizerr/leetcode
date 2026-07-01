class Solution {
    public int ROWS;
    public int COLS;
    public boolean[][] visited;
    public int[][] directions = { {1,0},{-1,0},{0,1},{0,-1} };

    public int maximumSafenessFactor(List<List<Integer>> grid) {
        this.ROWS = grid.size();
        this.COLS = grid.get(0).size();
        this.visited = new boolean[ROWS][COLS];
        int[][] dist = new int[ROWS][COLS];

        for (int[] row : dist)
            Arrays.fill(row, -1);

        bfs(dist,grid);

        for (int i = 0; i < ROWS; i++) {
            for (int j = 0; j < COLS; j++)
                System.out.print(dist[i][j] + " ");
            System.out.println();
        }

        int low = 0;
        int high = 2 * 400 + 1;

        while (low < high) {
            int mid = low + (high - low + 1) / 2;
            if (dist[0][0] >= mid && dfs(dist, 0, 0, mid, new boolean[ROWS][COLS]))
                low = mid;
            else
                high = mid - 1;
        }

        return high;

    }

    public boolean dfs(int[][] dist, int r, int c, int mid, boolean[][] visited) {

        if (r == ROWS - 1 && c == COLS - 1)
            return true;

        if (visited[r][c])
            return false;

        visited[r][c] = true;

        for (int[] dir : directions) {
            int nr = r + dir[0];
            int nc = c + dir[1];

            if (nr < 0 || nc < 0 || nr >= ROWS || nc >= COLS)
                continue;

            if (dist[nr][nc] < mid)
                continue;

            if (dfs(dist, nr, nc, mid, visited))
                return true;

        }
        return false;
    }

    public void bfs(int[][] dist, List<List<Integer>> grid) {
        Queue<int[]> q = new ArrayDeque<>();
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                if (grid.get(r).get(c) == 1) {
                    dist[r][c] = 0;
                    q.offer(new int[] { r, c });
                }
            }
        }

        while (!q.isEmpty()) {
            int[] pos = q.poll();
            for (int[] dir : directions) {
                int nr = pos[0] + dir[0];
                int nc = pos[1] + dir[1];

                if (nr < 0 || nc < 0 || nr >= ROWS || nc >= COLS)
                    continue;

                if (dist[nr][nc] != -1)
                    continue;

                dist[nr][nc] = dist[pos[0]][pos[1]] + 1;
                q.offer(new int[] { nr, nc });
            }
        }
    }
}