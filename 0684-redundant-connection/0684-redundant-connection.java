class Solution {
    class UnionFind {
    int[] parent;
    int[] rank;

    UnionFind(int n) {
        parent = new int[n + 1];
        rank = new int[n + 1];
        for (int i = 0; i <= n; i++) {
            parent[i] = i;
            rank[i] = 0;
        }
    }

    int find(int x) {
        if (parent[x] != x)
            parent[x] = find(parent[x]);  // PATH COMPRESSION
        return parent[x];
    }

    boolean union(int x, int y) {
        int bossX = find(x);
        int bossY = find(y);

        if (bossX == bossY) return false;  // already connected = cycle!

        // UNION BY RANK → attach smaller under bigger
        if (rank[bossX] < rank[bossY])
            parent[bossX] = bossY;
        else if (rank[bossX] > rank[bossY])
            parent[bossY] = bossX;
        else {
            parent[bossY] = bossX;
            rank[bossX]++;  // same rank → one grows taller
        }

        return true;  // successfully merged
    }

    boolean connected(int x, int y) {
        return find(x) == find(y);
    }
}
    public int[] findRedundantConnection(int[][] edges) {
          UnionFind uf = new UnionFind(edges.length);

    for (int[] edge : edges) {
        if (!uf.union(edge[0], edge[1]))  // union returns false = cycle!
            return edge;
    }
    return new int[0];
    }
}