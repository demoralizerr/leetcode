class NumArray {

    int[] segtree;
    int numlen;

    public NumArray(int[] nums) {
        int n = nums.length;
        this.numlen = n;
        segtree = new int[4 * n];
        buildSegTree(0, 0, n - 1, nums);

    }

    public void update(int index, int val) {
         updateSegTree(0,index,val,0,numlen-1);
    }

    public int sumRange(int left, int right) {
        return query(0, 0, numlen - 1, left, right);
    }

    public int query(int i, int l, int r, int start, int end) {
        //case 1 : out of bound 
        if (start > r || l > end)
            return 0;

        //case 2: with in boundaries
        else if (l >= start && r <= end)
            return segtree[i];

        //case 3: overlapping 
        else {
            int mid = (l + r) / 2;
            return query(2 * i + 1, l, mid, start, end) + query(2 * i + 2, mid + 1, r, start, end);
        }

    }

    public void buildSegTree(int i, int l, int r, int[] nums) {
        //base case 
        if (l == r) {
            segtree[i] = nums[l];
            return;
        }

        int mid = (l + r) / 2;
        buildSegTree(2 * i + 1, l, mid, nums);
        buildSegTree(2 * i + 2, mid + 1, r, nums);
        segtree[i] = segtree[2 * i + 1] + segtree[2 * i + 2];
    }

    public void updateSegTree(int i, int index, int val, int l, int r) {
        //base case 
        if (l == r) {
            segtree[i] = val;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid) {
            //check in left subtree
            updateSegTree(2 * i + 1, index, val, l, mid);
        } else {
            // check in right subtree
            updateSegTree(2 * i + 2, index, val, mid + 1, r);
        }

        segtree[i] = segtree[2 * i + 1] + segtree[2 * i + 2];
    }
}

/**
 * Your NumArray object will be instantiated and called as such:
 * NumArray obj = new NumArray(nums);
 * obj.update(index,val);
 * int param_2 = obj.sumRange(left,right);
 */