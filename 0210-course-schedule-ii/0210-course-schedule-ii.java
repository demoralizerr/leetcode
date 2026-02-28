class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        int[] res = new int[numCourses];
        List<List<Integer>> adj = new ArrayList<>();
        int[] indegree = new int[numCourses];
        Queue<Integer> q = new LinkedList<>();

        for (int i = 0; i < numCourses; i++)
            adj.add(new ArrayList<>());

        for (int[] pair : prerequisites) {
            int course = pair[0];
            int pre = pair[1];
            adj.get(pre).add(course);
            indegree[course]++;
        }

        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                q.offer(i);
        }

        int idx = 0;
        while (!q.isEmpty()) {
            int node = q.poll();
            res[idx++] = node;
            for (int nei : adj.get(node)) {
                indegree[nei]--;
                if (indegree[nei] == 0)
                    q.offer(nei);
            }
        }

        return idx == numCourses ? res : new int[0];

    }
}