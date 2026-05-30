class SegmentTree {
    int[] tree;
    
    public SegmentTree(int size) {
        tree = new int[4 * size];
    }

    public void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            tree[node] = val;
            return;
        }
        int mid = start + (end - start) / 2;
        if (idx <= mid) {
            update(2 * node, start, mid, idx, val);
        } else {
            update(2 * node + 1, mid + 1, end, idx, val);
        }
        tree[node] = Math.max(tree[2 * node], tree[2 * node + 1]);
    }

    public int query(int node, int start, int end, int l, int r) {
        if (r < start || l > end) {
            return 0; 
        }
        if (l <= start && end <= r) {
            return tree[node];
        }
        int mid = start + (end - start) / 2;
        return Math.max(
            query(2 * node, start, mid, l, r),
            query(2 * node + 1, mid + 1, end, l, r)
        );
    }
}

class FenwickTree {
    int[] tree;
    int size;
    
    public FenwickTree(int size) {
        this.size = size;
        this.tree = new int[size + 2]; 
    }

    public void add(int i, int delta) {
        i += 1; 
        while (i <= size) {
            tree[i] += delta;
            i += i & (-i);
        }
    }

    public int query(int i) {
        i += 1;
        int s = 0;
        while (i > 0) {
            s += tree[i];
            i -= i & (-i);
        }
        return s;
    }
}

class Solution {
    public List<Boolean> getResults(int[][] queries) {
        // x constraints are up to 50000, 50005 gives a safe buffer
        int MAX_X = 50005; 
        
        SegmentTree st = new SegmentTree(MAX_X);
        FenwickTree bit = new FenwickTree(MAX_X);
        
        bit.add(0, 1);
        st.update(1, 0, MAX_X, 0, 0);
        
        List<Boolean> res = new ArrayList<>();
        
        for (int[] q : queries) {
            if (q[0] == 1) {
                int x = q[1];
                
                int prevObs = getPrev(bit, x);
                int nxtObs = getNxt(bit, x, MAX_X);
                
                st.update(1, 0, MAX_X, x, x - prevObs);
                
                if (nxtObs != -1) {
                    st.update(1, 0, MAX_X, nxtObs, nxtObs - x);
                }
                
                bit.add(x, 1);
                
            } else {
                int x = q[1];
                int sz = q[2];
                
                int prevObs = getPrev(bit, x);
                
                int maxGapInRange = st.query(1, 0, MAX_X, 0, prevObs);
                int maxPossible = Math.max(maxGapInRange, x - prevObs);
                
                res.add(maxPossible >= sz);
            }
        }
        
        return res;
    }
    
    private int getPrev(FenwickTree bit, int x) {
        int target = bit.query(x);
        if (target == 0) return 0;
        
        int low = 0, high = x, ans = 0;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (bit.query(mid) == target) {
                ans = mid;
                high = mid - 1; 
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
    
    private int getNxt(FenwickTree bit, int x, int maxX) {
        int target = bit.query(x) + 1;
        if (target > bit.query(maxX)) {
            return -1; 
        }
        
        int low = x + 1, high = maxX, ans = maxX;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (bit.query(mid) >= target) {
                ans = mid;
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return ans;
    }
}