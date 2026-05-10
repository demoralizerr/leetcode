class Solution {

    private int[][] edges;
    boolean[] visited;
    private List<Integer>[] adj;

    public boolean validPath(int n, int[][] edges, int source, int destination) {
        this.edges = edges;
        this.visited = new boolean[n];
        this.adj = new ArrayList[n];

        for (int i = 0; i < n; i++)
            adj[i] = new ArrayList<>();

        for (int[] edge : edges) {
            adj[edge[0]].add(edge[1]);
            adj[edge[1]].add(edge[0]);
        }

        return dfs(n, source, destination);

    }

    public boolean dfs(int n, int source, int destination) {

        if (source == destination)
            return true;

        visited[source] = true;

        for (int nei : adj[source]) {
            if (!visited[nei]) {
                if (dfs(n, nei, destination))
                    return true;
            }
        }

        return false;

    }
}