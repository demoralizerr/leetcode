class Solution {
    int[][] anc;
    int[] depth;
    int rows;
    int cols;
    public static final int MOD = 1_000_000_007;

    public int[] assignEdgeWeights(int[][] edges, int[][] queries) {
        int n = edges.length + 1;
        this.rows = n + 1;
        this.cols = Integer.SIZE - 1 - Integer.numberOfLeadingZeros(rows) + 1;

        this.anc = new int[rows][cols];
        this.depth = new int[rows];

        for (int[] row : anc) {
            Arrays.fill(row, -1);
        }

        Map<Integer, List<Integer>> graph = new HashMap<>();
        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            graph.computeIfAbsent(u, k -> new ArrayList<>()).add(v);
            graph.computeIfAbsent(v, k -> new ArrayList<>()).add(u);
        }

        // 3. Kick off DFS from Node 1 (the problem specification root)
        dfs(graph, 1, -1);

        buildAncestorTable(rows, cols);

        int[] pow2 = new int[rows + 2];
        pow2[0] = 1;
        for (int i = 1; i <= rows + 1; i++) {
            pow2[i] = (2 * pow2[i - 1]) % MOD;
        }

        int[] ans = new int[queries.length];
        for (int q = 0; q < queries.length; q++) {
            int u = queries[q][0];
            int v = queries[q][1];

            int lca = findLca(u, v);
            int dist = depth[u] + depth[v] - 2 * depth[lca];

            if (dist == 0) {
                ans[q] = 0;
            } else {
                ans[q] = pow2[dist - 1];
            }
        }

        return ans;
    }

    public int findLca(int u, int v) {
        if (depth[u] < depth[v]) {
            int tmp = u;
            u = v;
            v = tmp;
        }

        int k = depth[u] - depth[v];

        for (int power = 0; power < cols; power++) {
            if ((k & (1 << power)) != 0) {
                u = anc[u][power];
                if (u == -1)
                    return -1;
            }
        }

        if (u == v)
            return u;

        for (int power = cols - 1; power >= 0; power--) {
            if (anc[u][power] == -1 || anc[v][power] == -1)
                continue;

            if (anc[u][power] != anc[v][power]) {
                u = anc[u][power];
                v = anc[v][power];
            }
        }
        return anc[u][0];
    }

    public void buildAncestorTable(int rows, int cols) {
        for (int power = 1; power < cols; power++) {
            for (int node = 1; node < rows; node++) {
                if (anc[node][power - 1] != -1) {
                    anc[node][power] = anc[anc[node][power - 1]][power - 1];
                } else {
                    anc[node][power] = -1;
                }
            }
        }
    }

    public void dfs(Map<Integer, List<Integer>> graph, int node, int parent) {
        anc[node][0] = parent;

        for (int nei : graph.getOrDefault(node, Collections.emptyList())) {
            if (nei == parent)
                continue;

            depth[nei] = depth[node] + 1;
            dfs(graph, nei, node);
        }
    }
}
