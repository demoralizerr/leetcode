class Solution {
    public long maximumSum(int[] nums, int m, int l, int r) {
        int n = nums.length;

        long[] pref = new long[n + 1];
        for (int i = 0; i < n; i++) {
            pref[i + 1] = pref[i] + nums[i];
        }

        long NEG = -(long) 1e18;

        long[][] dp = new long[m + 1][n + 1];

        for (int j = 0; j <= m; j++) {
            Arrays.fill(dp[j], NEG);
        }

        for (int i = 0; i <= n; i++) {
            dp[0][i] = 0;
        }

        long ans = NEG;

        for (int j = 1; j <= m; j++) {

            Deque<Integer> dq = new ArrayDeque<>();

            for (int i = l; i <= n; i++) {

                int candidate = i - l;

                long candidateValue =
                        dp[j - 1][candidate] - pref[candidate];

                while (!dq.isEmpty()) {
                    int last = dq.peekLast();

                    long lastValue =
                            dp[j - 1][last] - pref[last];

                    if (lastValue <= candidateValue) {
                        dq.pollLast();
                    } else {
                        break;
                    }
                }

                dq.offerLast(candidate);

                while (!dq.isEmpty() && dq.peekFirst() < i - r) {
                    dq.pollFirst();
                }

                dp[j][i] = dp[j][i - 1];

                if (!dq.isEmpty()) {

                    int best = dq.peekFirst();

                    dp[j][i] = Math.max(
                            dp[j][i],
                            pref[i]
                                    + dp[j - 1][best]
                                    - pref[best]
                    );
                }

                ans = Math.max(ans, dp[j][i]);
            }
        }

        return ans;
    }
}