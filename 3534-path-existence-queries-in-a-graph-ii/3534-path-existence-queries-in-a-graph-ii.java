class Solution {
    private int rows;
    private int cols;
    private int[][] ancestorTable;

    private static class NodePair {
        int val;
        int id;

        NodePair(int val, int id) {
            this.val = val;
            this.id = id;
        }
    }

    private int customUpperBound(NodePair[] arr, int target) {
        int n = arr.length;
        int l = 0;
        int r = n - 1;
        int result = 0;

        while (l <= r) {
            int mid = l + (r - l) / 2;

            if (arr[mid].val <= target) {
                result = mid;
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }

        return result;
    }

    public int[] pathExistenceQueries(int n, int[] nums, int maxDiff, int[][] queries) {
        // Create and populate the pair array
        NodePair[] arr = new NodePair[n];
        for (int i = 0; i < n; i++) {
            arr[i] = new NodePair(nums[i], i);
        }

        // Sort by value. If values match, sort by original index ID
        Arrays.sort(arr, (p1, p2) -> {
            if (p1.val != p2.val) {
                return Integer.compare(p1.val, p2.val);
            }
            return Integer.compare(p1.id, p2.id);
        });

        int[] nodeToIdx = new int[n];
        for (int i = 0; i < n; i++) {
            int node = arr[i].id;
            nodeToIdx[node] = i;
        }

        rows = n;
        cols = (int) (Math.log(n) / Math.log(2)) + 1;
        ancestorTable = new int[rows][cols];

        // Fill 0th column first
        for (int node = 0; node < n; node++) {
            int farthestIdxOneHop = customUpperBound(arr, arr[node].val + maxDiff);
            ancestorTable[node][0] = farthestIdxOneHop;
        }

        // Fill remaining columns using binary lifting formula
        for (int j = 1; j < cols; j++) {
            for (int node = 0; node < n; node++) {
                ancestorTable[node][j] = ancestorTable[ancestorTable[node][j - 1]][j - 1];
            }
        }

        int[] result = new int[queries.length];
        int idx = 0;

        for (int[] query : queries) {
            int u = query[0];
            int v = query[1];

            int a = nodeToIdx[u];
            int b = nodeToIdx[v];
            
            if (a == b) {
                result[idx++] = 0;
                continue;
            }

            if (a > b) {
                int temp = a;
                a = b;
                b = temp;
            }

            int curr = a;
            int jumps = 0;

            for (int j = cols - 1; j >= 0; j--) {
                if (ancestorTable[curr][j] < b) {
                    curr = ancestorTable[curr][j];
                    jumps += (1 << j);
                }
            }

            if (ancestorTable[curr][0] >= b) {
                result[idx++] = jumps + 1;
            } else {
                result[idx++] = -1;
            }
        }

        return result;
    }
}
