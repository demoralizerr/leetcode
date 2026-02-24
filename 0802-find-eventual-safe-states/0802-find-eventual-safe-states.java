class Solution {
    private Boolean[] safe;

    public List<Integer> eventualSafeNodes(int[][] graph) {
        int n = graph.length;
        this.safe = new Boolean[n];
        List<Integer> res = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            if (dfs(i,graph))
                res.add(i);
        }
        return res;
    }

    private boolean dfs(int i, int[][] graph) {
        if (safe[i] != null)
            return safe[i];

        safe[i] = false;
        for (int nei : graph[i]) {
            if (!dfs(nei,graph))
                return false;
        }
        safe[i] = true;
        return true;
    }
}