class Solution {
    public List<Boolean> checkIfPrerequisite(int numCourses, int[][] prerequisites, int[][] queries) {
        List<Set<Integer>> adj = new ArrayList<>();
        List<Set<Integer>> prereqmap = new ArrayList<>();
        int[] indegree = new int[numCourses];

        for (int i = 0; i < numCourses; i++) {
            adj.add(new HashSet<>());
            prereqmap.add(new HashSet<>());
        }

        for (int[] pair : prerequisites) {
            int course = pair[0];
            int pre = pair[1];
            adj.get(course).add(pre);
            indegree[pre]++;
        }
        Queue<Integer> queue = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0)
                queue.offer(i);
        }

        while (!queue.isEmpty()) {
            int node = queue.poll();
            for (int nei : adj.get(node)) {
                prereqmap.get(nei).add(node);
                prereqmap.get(nei).addAll(prereqmap.get(node));
                indegree[nei]--;
                if(indegree[nei]==0) queue.offer(nei);
            }
        }
        List<Boolean> res = new ArrayList<>();
        for (int[] q : queries) {
            int ui = q[0];
            int vi = q[1];
            if (prereqmap.get(vi).contains(ui))
                res.add(true);
            else
                res.add(false);
        }

        return res;
    }
}