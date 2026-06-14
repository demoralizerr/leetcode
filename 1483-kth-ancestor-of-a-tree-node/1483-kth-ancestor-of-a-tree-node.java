class TreeAncestor {
    public int[][] ancestor;
    int rows;
    int cols;

    public TreeAncestor(int n, int[] parent) {
        this.rows = n;
        this.cols = (int) (Math.log(n) / Math.log(2)) + 1;
        this.ancestor = new int[rows][cols];

        for (int[] row : ancestor)
            Arrays.fill(row, -1);

        //prefill first column
        for (int node = 0; node < n; node++) {
            ancestor[node][0] = parent[node];
        }

        for (int power = 1; power < cols; power++) {
            for (int node = 0; node < n; node++) {
                if (ancestor[node][power - 1] != -1)
                    ancestor[node][power] = ancestor[ancestor[node][power - 1]][power - 1];
            }
        }

    }

    public int getKthAncestor(int node, int k) {
        for (int j = 0; j < cols; j++) {
            if ((k & (1 << j)) != 0) {
                node = ancestor[node][j];

                if (node == -1)
                    return -1;
            }
        }
        return node;
    }
}

/**
 * Your TreeAncestor object will be instantiated and called as such:
 * TreeAncestor obj = new TreeAncestor(n, parent);
 * int param_1 = obj.getKthAncestor(node,k);
 */