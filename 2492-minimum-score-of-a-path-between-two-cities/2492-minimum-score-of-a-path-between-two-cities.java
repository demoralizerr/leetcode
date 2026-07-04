class Solution {
    boolean[] visited;
    int minscore = Integer.MAX_VALUE;

    public int minScore(int n, int[][] roads) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        this.visited = new boolean[n + 1];
        for (int[] road : roads) {
            int a = road[0];
            int b = road[1];
            int dist = road[2];
            graph.computeIfAbsent(a, k -> new ArrayList<>()).add(new int[] { b, dist });
            graph.computeIfAbsent(b, k -> new ArrayList<>()).add(new int[] { a, dist });
        }
        dfs(graph, 1);
        return minscore;

    }

    public void dfs(Map<Integer, List<int[]>> graph, int node) {

        visited[node] = true;
        for (int[] nei : graph.get(node)) {
            int neinode = nei[0];
            int neidist = nei[1];
            minscore = Math.min(minscore, neidist);
            if (!visited[neinode])
                dfs(graph, neinode);
        }
    }
}