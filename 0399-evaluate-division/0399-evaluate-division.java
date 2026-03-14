class Solution {
    private Map<String, Map<String, Double>> graph;

    public double[] calcEquation(List<List<String>> equations, double[] values, List<List<String>> queries) {
        this.graph = new HashMap<>();
        double[] res = new double[queries.size()];
        int idx = 0;
        for (List<String> eq : equations) {
            String a = eq.get(0);
            String b = eq.get(1);
            double value = values[idx++];

            graph.putIfAbsent(a, new HashMap<>());
            graph.putIfAbsent(b, new HashMap<>());

            graph.get(a).put(b, value);
            graph.get(b).put(a, 1 / value);
        }
        idx = 0;
        for (List<String> q : queries) {
            String start = q.get(0);
            String target = q.get(1);
            res[idx++] = dfs(start, target, new HashSet<>());
        }
        return res;
    }

    private double dfs(String start, String target, Set<String> visited) {
        if (!graph.containsKey(start) || !graph.containsKey(target))
            return -1.0;

        if (start.equals(target))
            return 1.0;

        visited.add(start);

        for (String nei : graph.get(start).keySet()) {

            if (!visited.contains(nei)) {
                double res = dfs(nei, target, visited);
                if (res != -1.0) {
                    return graph.get(start).get(nei) * res;
                }
            }

        }
        return -1.0;

    }
}