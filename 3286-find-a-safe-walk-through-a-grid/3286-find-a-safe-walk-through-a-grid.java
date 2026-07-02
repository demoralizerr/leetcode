class Solution {

    int[][] dir = { {1,0}, {-1,0}, {0,1}, {0,-1} };

    class State {
        int r, c, health;

        State(int r, int c, int health) {
            this.r = r;
            this.c = c;
            this.health = health;
        }
    }

    public boolean findSafeWalk(List<List<Integer>> grid, int health) {
        int m = grid.size();
        int n = grid.get(0).size();

        // Health after standing on the starting cell
        int startHealth = health - grid.get(0).get(0);
        if (startHealth <= 0)
            return false;

        int[][] bestHealth = new int[m][n];
        for (int[] row : bestHealth)
            Arrays.fill(row, -1);

        Queue<State> q = new LinkedList<>();
        q.offer(new State(0, 0, startHealth));
        bestHealth[0][0] = startHealth;

        while (!q.isEmpty()) {
            State cur = q.poll();

            // Reached destination
            if (cur.r == m - 1 && cur.c == n - 1)
                return true;

            for (int[] d : dir) {
                int nr = cur.r + d[0];
                int nc = cur.c + d[1];

                if (nr < 0 || nr >= m || nc < 0 || nc >= n)
                    continue;

                int newHealth = cur.health - grid.get(nr).get(nc);

                if (newHealth <= 0)
                    continue;

                // Already reached this cell with equal or more health
                if (newHealth <= bestHealth[nr][nc])
                    continue;

                bestHealth[nr][nc] = newHealth;
                q.offer(new State(nr, nc, newHealth));
            }
        }

        return false;
    }
}