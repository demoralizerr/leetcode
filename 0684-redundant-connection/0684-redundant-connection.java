class Solution {
    public int[] findRedundantConnection(int[][] edges) {
        //topological sort
        int rows = edges.length;
        List<List<Integer>> adj = new ArrayList<>(rows+1);
        int[] indeg = new int[rows+1];

        for (int i = 0; i <= rows; i++) 
            adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
            indeg[u]++;
            indeg[v]++;
        }

        Queue<Integer> q = new LinkedList<>();
        for (int i = 1; i <= rows; i++) {
            if (indeg[i] == 1)
                q.offer(i);
        }

        while (!q.isEmpty()) {
            int node = q.poll();
            indeg[node]--;
            for (int nei : adj.get(node)) {
                indeg[nei]--;
                if (indeg[nei] == 1)
                    q.offer(nei);
            }
        }

        for(int i=rows-1; i>=0;i--){
            int u = edges[i][0]; int v=edges[i][1];
            if(indeg[u]==2 && indeg[v]>0)
               return new int[]{u,v};
        }
        return new int[0];
    }
}