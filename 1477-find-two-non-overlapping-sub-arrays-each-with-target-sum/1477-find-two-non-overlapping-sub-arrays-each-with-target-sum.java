class Solution {
    public int minSumOfLengths(int[] arr, int target) {
        int n = arr.length;
        final int INF = Integer.MAX_VALUE / 2; // avoid overflow when adding

        // dp[i] = min length of a valid (sum == target) subarray ending at or before index i
        int[] dp = new int[n];
        Arrays.fill(dp, INF);

        int left = 0, sum = 0;
        int ans = INF;

        for (int right = 0; right < n; right++) {
            sum += arr[right];

            // shrink window from the left while sum exceeds target
            while (sum > target) {
                sum -= arr[left];
                left++;
            }

            // carry forward the best-so-far length
            dp[right] = (right > 0) ? dp[right - 1] : INF;

            if (sum == target) {
                int curLen = right - left + 1;

                // try to pair with the best window strictly before 'left'
                int prevBest = (left > 0) ? dp[left - 1] : INF;
                if (prevBest < INF) {
                    ans = Math.min(ans, prevBest + curLen);
                }

                // update dp[right] with this window's length too
                dp[right] = Math.min(dp[right], curLen);
            }
        }

        return ans >= INF ? -1 : ans;
    }
}