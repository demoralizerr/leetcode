class Solution {
    class UnionFind {
        private int[] parent;

        public UnionFind(int n) {
            parent = new int[n + 1];
            for (int i = 0; i <= n; i++) {
                parent[i] = i;
            }
        }

        public int find(int x) {
            if (parent[x] == x)
                return x;

            return find(parent[x]);
        }

        public void union(int x, int y) {
            int bossx = find(x);
            int bossy = find(y);
            parent[bossx] = bossy;
        }

        public boolean connected(int x, int y) {
            return find(x) == find(y);
        }

    }

    public int[] findRedundantConnection(int[][] edges) {
        UnionFind uf = new UnionFind(edges.length);
        for (int[] edge : edges) {
            if (uf.connected(edge[0], edge[1]))
                return edge;
            uf.union(edge[0], edge[1]);
        }

        return new int[0];
    }
}