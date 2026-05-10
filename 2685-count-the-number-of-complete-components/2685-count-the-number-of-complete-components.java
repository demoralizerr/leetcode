class Solution {

    private int nodecount = 0;
    private int edgecount = 0;

    public int countCompleteComponents(int n, int[][] edges) {
        List<List<Integer>> adj = new ArrayList<>();
        boolean[] visited = new boolean[n];
        int components = 0;
        for (int i = 0; i < n; i++)
            adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        for (int i = 0; i < n; i++) {
            if (!visited[i]) {
                nodecount = 0;
                edgecount = 0;
                countBydfs(n, adj, i, visited);
                if (edgecount == nodecount * (nodecount - 1))
                    components++;
            }
        }

        return components;
    }

    public boolean countBydfs(int n, List<List<Integer>> adj, int curr, boolean[] visited) {

        visited[curr] = true;

        nodecount++;
        edgecount += adj.get(curr).size();

        for (int nei : adj.get(curr)) {
            if (!visited[nei])
                countBydfs(n, adj, nei, visited);
        }

        return true;
    }
}