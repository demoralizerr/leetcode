class Solution {
    private int k, n;
    private int[] a;
    private int[] segP;
    private int[][] segCnt;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        this.n = nums.length;
        this.a = new int[n];
        for (int i = 0; i < n; i++) a[i] = nums[i] % k;

        segP = new int[4 * n];
        segCnt = new int[4 * n][];
        build(1, 0, n - 1);

        int[] result = new int[queries.length];
        for (int i = 0; i < queries.length; i++) {
            int idx = queries[i][0], val = queries[i][1];
            int start = queries[i][2], x = queries[i][3];

            a[idx] = val % k;
            update(1, 0, n - 1, idx);

            int[] node = query(1, 0, n - 1, start, n - 1); // [0]=P, [1..k]=cnt
            result[i] = node[1 + x];
        }
        return result;
    }

    private void build(int node, int l, int r) {
        if (l == r) {
            segCnt[node] = new int[k];
            segP[node] = a[l];
            segCnt[node][a[l]] = 1;
            return;
        }
        int mid = (l + r) >>> 1;
        build(2 * node, l, mid);
        build(2 * node + 1, mid + 1, r);
        pull(node);
    }

    private void pull(int node) {
        int lp = segP[2 * node], rp = segP[2 * node + 1];
        int[] lc = segCnt[2 * node], rc = segCnt[2 * node + 1];
        int[] nc = new int[k];
        System.arraycopy(lc, 0, nc, 0, k);
        for (int q = 0; q < k; q++) {
            if (rc[q] == 0) continue;
            nc[(lp * q) % k] += rc[q];
        }
        segCnt[node] = nc;
        segP[node] = (lp * rp) % k;
    }

    private void update(int node, int l, int r, int idx) {
        if (l == r) {
            int[] nc = new int[k];
            nc[a[idx]] = 1;
            segCnt[node] = nc;
            segP[node] = a[idx];
            return;
        }
        int mid = (l + r) >>> 1;
        if (idx <= mid) update(2 * node, l, mid, idx);
        else update(2 * node + 1, mid + 1, r, idx);
        pull(node);
    }

    private int[] query(int node, int l, int r, int ql, int qr) {
        if (qr < l || r < ql) return null;
        if (ql <= l && r <= qr) {
            int[] res = new int[k + 1];
            res[0] = segP[node];
            System.arraycopy(segCnt[node], 0, res, 1, k);
            return res;
        }
        int mid = (l + r) >>> 1;
        int[] leftRes = query(2 * node, l, mid, ql, qr);
        int[] rightRes = query(2 * node + 1, mid + 1, r, ql, qr);
        if (leftRes == null) return rightRes;
        if (rightRes == null) return leftRes;
        return merge(leftRes, rightRes);
    }

    private int[] merge(int[] left, int[] right) {
        int lp = left[0], rp = right[0];
        int[] res = new int[k + 1];
        System.arraycopy(left, 1, res, 1, k);
        for (int q = 0; q < k; q++) {
            int rc = right[1 + q];
            if (rc == 0) continue;
            res[1 + (lp * q) % k] += rc;
        }
        res[0] = (lp * rp) % k;
        return res;
    }
}