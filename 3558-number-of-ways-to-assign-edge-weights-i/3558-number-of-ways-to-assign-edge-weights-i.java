class Solution {

    public static final int MOD = 1_000_000_007;

    public int assignEdgeWeights(int[][] edges) {
        Map<Integer, List<Integer>> graph = new HashMap<>();

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        int maxdepth = getMaxDepth(graph, 1, -1);

        return power(2, maxdepth - 1) % MOD;
    }

    public int getMaxDepth(Map<Integer, List<Integer>> graph, int node, int parent) {
        int depth = 0;
        for (int nei : graph.getOrDefault(node, Collections.emptyList())) {
            if (nei == parent)
                continue;

            depth = Math.max(depth, 1 + getMaxDepth(graph, nei, node));
        }
        return depth;
    }

    public int power(int a, int b) {
        if (b == 0)
            return 1;

        int half = power(a, b / 2);
        int result = (int) (((long) half * half) % MOD);

        if (b % 2 == 1)
            result = (int) (((long) result * a) % MOD);

        return result % MOD;
    }
}
