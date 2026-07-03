class Solution {
    public int findMaxPathScore(int[][] edges, boolean[] online, long k) {
        int n = online.length;
        Map<Integer, List<int[]>> graph = new HashMap<>();

        int low = Integer.MAX_VALUE;
        int high = Integer.MIN_VALUE;

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            int cost = edge[2];

            if (!online[u] || !online[v])
                continue;
            graph.computeIfAbsent(u, l -> new ArrayList<>()).add(new int[] { cost, v });
            low = Math.min(low, cost);
            high = Math.max(high, cost);

        }
        int answer = -1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (dijkstra(graph, mid, k, n)) {
                answer = mid;
                low = mid + 1;
            } else
                high = mid - 1;
        }

        return answer;

    }

    public boolean dijkstra(Map<Integer, List<int[]>> graph, int mid, long k, int n) {
        int[] result = new int[n];
        Arrays.fill(result, Integer.MAX_VALUE);
        result[0] = 0;

        //{cost,node}
        Queue<int[]> pq = new PriorityQueue<>((a, b) -> Integer.compare(a[0], b[0]));
        pq.offer(new int[] { 0, 0 });

        while (!pq.isEmpty()) {
            int[] pos = pq.poll();
            int cost = pos[0];
            int node = pos[1];

            if (cost > k)
                return false;

            if (node == n - 1)
                return true;

            if (result[node] < cost)
                continue;

            for (int[] nei : graph.getOrDefault(node, new ArrayList<>())) {
                int neicost = nei[0];
                int neinode = nei[1];

                if (neicost < mid)
                    continue;

                if (neicost + cost < result[neinode]) {
                    result[neinode] = neicost + cost;
                    pq.offer(new int[] { cost + neicost, neinode });
                }

            }
        }
        return false;
    }
}