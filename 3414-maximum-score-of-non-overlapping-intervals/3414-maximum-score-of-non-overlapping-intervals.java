class Solution {
    public int[] maximumWeight(List<List<Integer>> intervals) {
        int n = intervals.size();

        int[] L = new int[n], R = new int[n], W = new int[n];
        for (int i = 0; i < n; i++) {
            List<Integer> iv = intervals.get(i);
            L[i] = iv.get(0);
            R[i] = iv.get(1);
            W[i] = iv.get(2);
        }

        Integer[] order = new Integer[n];
        for (int i = 0; i < n; i++) order[i] = i;
        // sort by right endpoint ascending
        Arrays.sort(order, (a, b) -> R[a] - R[b]);

        int[] sortedR = new int[n];
        for (int i = 0; i < n; i++) sortedR[i] = R[order[i]];

        // dp[k][i]: best State using at most k intervals from the first i (sorted) intervals
        State[][] dp = new State[5][n + 1];
        for (int k = 0; k <= 4; k++) dp[k][0] = new State(0L, new int[0]);
        for (int i = 0; i <= n; i++) dp[0][i] = new State(0L, new int[0]);

        for (int k = 1; k <= 4; k++) {
            for (int i = 1; i <= n; i++) {
                int origIdx = order[i - 1];
                int l = L[origIdx];
                int w = W[origIdx];

                State skip = dp[k][i - 1];

                int p = countLess(sortedR, l); // # intervals with r < l
                State prev = dp[k - 1][p];
                long takeScore = prev.score + w;
                int[] takeIdx = insertSorted(prev.idx, origIdx);
                State take = new State(takeScore, takeIdx);

                dp[k][i] = better(skip, take);
            }
        }

        return dp[4][n].idx;
    }

    // number of entries in sortedR strictly less than l (binary search, lower bound)
    private int countLess(int[] sortedR, int l) {
        int lo = 0, hi = sortedR.length;
        while (lo < hi) {
            int mid = (lo + hi) >>> 1;
            if (sortedR[mid] < l) lo = mid + 1;
            else hi = mid;
        }
        return lo;
    }

    private int[] insertSorted(int[] arr, int val) {
        int[] res = new int[arr.length + 1];
        int pos = 0;
        while (pos < arr.length && arr[pos] < val) {
            res[pos] = arr[pos];
            pos++;
        }
        res[pos] = val;
        for (int j = pos; j < arr.length; j++) res[j + 1] = arr[j];
        return res;
    }

    private State better(State a, State b) {
        if (a.score != b.score) return a.score > b.score ? a : b;
        return lexSmallerOrEqual(a.idx, b.idx) ? a : b;
    }

    // true if a <= b lexicographically (shorter array that's a prefix counts as smaller)
    private boolean lexSmallerOrEqual(int[] a, int[] b) {
        int len = Math.min(a.length, b.length);
        for (int i = 0; i < len; i++) {
            if (a[i] != b[i]) return a[i] < b[i];
        }
        return a.length <= b.length;
    }

    static class State {
        long score;
        int[] idx;
        State(long score, int[] idx) {
            this.score = score;
            this.idx = idx;
        }
    }
}