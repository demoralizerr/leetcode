class Solution {

    class pair {
        int time;
        int node;

        pair(int time, int node) {
            this.time = time;
            this.node = node;
        }
    }

    public int networkDelayTime(int[][] times, int n, int k) {
        Map<Integer, List<int[]>> graph = new HashMap<>();
        int min_time = Integer.MIN_VALUE;
        for (int[] t : times) {
            int u = t[0];
            int v = t[1];
            int w = t[2];
            graph.computeIfAbsent(u, l -> new ArrayList<>()).add(new int[] { v, w });
        }

        int[] mindist = new int[n + 1];
        Arrays.fill(mindist, Integer.MAX_VALUE);

        //minheap
        Queue<pair> pq = new PriorityQueue<>((a, b) -> Integer.compare(a.time, b.time));

        mindist[k] = 0;
        pq.offer(new pair(0, k));

        while (!pq.isEmpty()) {
            pair p = pq.poll();
            int time = p.time;
            int node = p.node;

            for (int[] nei : graph.getOrDefault(node, new ArrayList<>())) {
                int neitime = nei[1];
                int neinode = nei[0];

                if (time + neitime < mindist[neinode]) {
                    mindist[neinode] = time + neitime;
                    pq.offer(new pair(time + neitime, neinode));
                }
            }
        }

        for (int i = 1; i <= n; i++) {
            if (mindist[i] == Integer.MAX_VALUE)
                return -1;
            min_time = Math.max(min_time, mindist[i]);
        }

        return min_time;

    }
}