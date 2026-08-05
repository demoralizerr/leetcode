import java.util.*;

class Solution {
    public List<Integer> remainingMethods(int n, int k, int[][] invocations) {
        Set<Integer> visited = new HashSet<>();
        List<Integer> res = new ArrayList<>();

        Map<Integer, List<Integer>> graph = new HashMap<>();

        //Build the adjacency list graph
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            graph.computeIfAbsent(u, j -> new ArrayList<>()).add(v);
        }

        // DFS to mark all suspicious methods reachable from k
        dfs(graph, visited, k, n);

        // Check for any "outside invocations" pointing into the suspicious group
        for (int[] inv : invocations) {
            int u = inv[0];
            int v = inv[1];
            if (!visited.contains(u) && visited.contains(v)) {
                //Cannot remove anything!
                List<Integer> allMethods = new ArrayList<>();
                for (int i = 0; i < n; i++) {
                    allMethods.add(i);
                }
                return allMethods;
            }
        }

        // No external invocations found, safe to remove. Return only non-visited methods
        for (int u = 0; u < n; u++) {
            if (!visited.contains(u)) {
                res.add(u);
            }
        }
        return res;
    }

    public void dfs(Map<Integer, List<Integer>> graph, Set<Integer> visited, int start, int n) {
        if (start >= n)
            return;

        visited.add(start);

        for (int nei : graph.getOrDefault(start, new ArrayList<>())) {
            if (!visited.contains(nei)) {
                dfs(graph, visited, nei, n);
            }
        }
    }
}
