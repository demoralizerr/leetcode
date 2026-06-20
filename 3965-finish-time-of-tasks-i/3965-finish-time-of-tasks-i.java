class Solution {
    public long finishTime(int n, int[][] edges, int[] baseTime) {
        Map<Integer, List<Integer>> graph = new HashMap<>();


        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
        }

        return solve(graph, 0, -1, baseTime);

    }

    public long solve(Map<Integer, List<Integer>> graph, int node, int parent, int[] time) {
        long best = 0;
        if (graph.get(node) == null) {
            return time[node];
        }

        long earliest = Long.MAX_VALUE;
        long latest = Long.MIN_VALUE;

        for (int nei : graph.get(node)) {
            if (nei == parent)
                continue;

            long finish = solve(graph, nei, node, time);
            latest = Math.max(latest, finish);
            earliest = Math.min(earliest, finish);

        }
        long duration = (latest - earliest) + time[node];
        return latest + duration;
    }
}