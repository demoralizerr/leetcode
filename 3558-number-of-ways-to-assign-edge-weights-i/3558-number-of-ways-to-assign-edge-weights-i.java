class Solution {

    Set<Integer> visited;

    public int assignEdgeWeights(int[][] edges) {
        int rows = edges.length;
        int cols = edges[0].length;
        this.visited = new HashSet<>();
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        long depth = maxdepth(graph, 1, -1);
        return (int) modPow(2, depth - 1, 1_000_000_007);

    }

    private long modPow(long base, long exp, long mod) {
        long res = 1;

        while (exp > 0) {
            if ((exp & 1) == 1)
                res = (res * base) % mod;

            base = (base * base) % mod;
            exp >>= 1;
        }

        return res;
    }

    public long maxdepth(Map<Integer, List<Integer>> graph, int node, int parent) {

        if (visited.contains(node))
            return 0;

        visited.add(node);
        long depth = 0;
        for (int nei : graph.getOrDefault(node, Collections.emptyList())) {
            if (nei == parent)
                continue;

            depth = Math.max(depth, 1 + maxdepth(graph, nei, node));
        }
        return depth;
    }
}