class Solution {
    public boolean validPath(int n, int[][] edges, int source, int destination) {
        List<List<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[n];

        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        Queue<Integer> q = new ArrayDeque<>();
        q.offer(source);
        visited[source] = true;

        while (!q.isEmpty()) {
            int curr = q.poll();

            if (curr == destination)
                return true;

            for (int nei : adj.get(curr)) {
                if (!visited[nei]) {
                    q.offer(nei);
                    visited[nei] = true;
                }
            }
        }

        return false;
    }
}