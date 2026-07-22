public class Solution {

    private void buildSegmentTree(int i, int l, int r, int[] segmentTree, int[] arr) {
        if (l == r) {
            segmentTree[i] = arr[l];
            return;
        }

        int mid = l + (r - l) / 2;
        buildSegmentTree(2 * i + 1, l, mid, segmentTree, arr);
        buildSegmentTree(2 * i + 2, mid + 1, r, segmentTree, arr);
        segmentTree[i] = Math.max(segmentTree[2 * i + 1], segmentTree[2 * i + 2]);
    }

    // construct the Segment Tree
    private int[] constructST(int[] arr, int n) {
        int[] segmentTree = new int[4 * n];
        buildSegmentTree(0, 0, n - 1, segmentTree, arr);
        return segmentTree;
    }

    // Range Maximum Query
    private int querySegmentTree(int start, int end, int i, int l, int r, int[] segmentTree) {
        if (l > end || r < start) {
            return Integer.MIN_VALUE;
        }

        if (l >= start && r <= end) {
            return segmentTree[i];
        }

        int mid = l + (r - l) / 2;
        return Math.max(querySegmentTree(start, end, 2 * i + 1, l, mid, segmentTree),
                        querySegmentTree(start, end, 2 * i + 2, mid + 1, r, segmentTree));
    }

    private int RMQ(int[] st, int n, int a, int b) {
        return querySegmentTree(a, b, 0, 0, n - 1, st);
    }

    //finds first index where element >= val
    private int lowerBound(List<Integer> list, int val) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) >= val) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    // finds first index where element > val
    private int upperBound(List<Integer> list, int val) {
        int low = 0, high = list.size();
        while (low < high) {
            int mid = low + (high - low) / 2;
            if (list.get(mid) > val) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }
        return low;
    }

    public List<Integer> maxActiveSectionsAfterTrade(String s, int[][] queries) {
        int n = s.length();
        
        // Count active elements ('1's)
        int activeCount = 0;
        for (int i = 0; i < n; i++) {
            if (s.charAt(i) == '1') activeCount++;
        }

        List<Integer> blockStart = new ArrayList<>();
        List<Integer> blockEnd = new ArrayList<>();

        int idx = 0;
        while (idx < n) {
            if (s.charAt(idx) == '0') {
                int start = idx;
                while (idx < n && s.charAt(idx) == '0') idx++;
                blockStart.add(start);
                blockEnd.add(idx - 1);
            } else {
                idx++;
            }
        }

        int m = blockStart.size();

        // If there is fewer than two blocks of zeros
        if (m < 2) {
            List<Integer> result = new ArrayList<>();
            for (int i = 0; i < queries.length; i++) {
                result.add(activeCount);
            }
            return result;
        }

        int[] blockSize = new int[m];
        for (int i = 0; i < m; i++) {
            blockSize[i] = blockEnd.get(i) - blockStart.get(i) + 1;
        }

        int N = m - 1; 
        int[] pairSum = new int[N];
        for (int i = 0; i < N; i++) {
            pairSum[i] = blockSize[i] + blockSize[i + 1];
        }

        int[] st = constructST(pairSum, N);
        List<Integer> result = new ArrayList<>();

        for (int[] q : queries) {
            int l = q[0];
            int r = q[1];

            int low = lowerBound(blockEnd, l);
            int high = upperBound(blockStart, r) - 1;

            int maxPairSum = 0;
            if (low < high) { 
                int firstLen = blockEnd.get(low) - Math.max(blockStart.get(low), l) + 1;
                int lastLen = Math.min(blockEnd.get(high), r) - blockStart.get(high) + 1;

                if (high - low == 1) { 
                    maxPairSum = firstLen + lastLen;
                } else {
                    int pair1 = firstLen + blockSize[low + 1];
                    int pair2 = blockSize[high - 1] + lastLen;
                    int RMQMaxPairSum = RMQ(st, N, low + 1, high - 2);
                    maxPairSum = Math.max(pair1, Math.max(pair2, RMQMaxPairSum));
                }
            }
            result.add(maxPairSum + activeCount);
        }

        return result;
    }
}
