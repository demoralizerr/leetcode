class Solution {

    class Pair {
        int r, c;

        public Pair(int r, int c) {
            this.r = r;
            this.c = c;
        }
    }

    int[][] dirs = {
        {1, 0}, {-1, 0}, {0, 1}, {0, -1}
    };

    public int[][] colorGrid(int n, int m, int[][] sources) {

        int[][] grid = new int[n][m];
        Queue<Pair> q = new LinkedList<>();
        Map<String, Integer> mp = new HashMap<>();

        // initialize grid
        for (int[] source : sources) {
            int r = source[0], c = source[1], color = source[2];
            grid[r][c] = color;
            q.offer(new Pair(r, c));
        }

        while (!q.isEmpty()) {
            int size = q.size();

            for (int i = 0; i < size; i++) {
                Pair p = q.poll();
                int r = p.r, c = p.c;

                for (int[] dir : dirs) {
                    int nr = r + dir[0];
                    int nc = c + dir[1];

                    if (nr >= 0 && nc >= 0 && nr < n && nc < m && grid[nr][nc] == 0) {
                        String key = nr + "," + nc;
                        int color = grid[r][c];
                        mp.put(key, Math.max(mp.getOrDefault(key, 0), color));
                    }
                }
            }

            for (Map.Entry<String, Integer> entry : mp.entrySet()) {
                String key = entry.getKey();
                String[] parts = key.split(",");

                int row = Integer.parseInt(parts[0]);
                int col = Integer.parseInt(parts[1]);

                grid[row][col] = entry.getValue();
                q.offer(new Pair(row, col));
            }

            mp.clear();
        }

        return grid;
    }
}