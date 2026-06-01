class Solution {

    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;

        int[] bonus = new int[n];

        for (int i = 0; i < n; i++) {
            int factorI = items[i][0];

            for (int j = 0; j < n; j++) {
                if (i == j) continue;

                int factorJ = items[j][0];

                if (factorJ % factorI == 0) {
                    bonus[i]++;
                }
            }
        }

        int NEG = -(int)1e9;

        int[] dp = new int[budget + 1];
        Arrays.fill(dp, NEG);
        dp[0] = 0;

        for (int i = 0; i < n; i++) {

            int price = items[i][1];

            int[] next = Arrays.copyOf(dp, budget + 1);

            int[] active = new int[budget + 1];
            Arrays.fill(active, NEG);

            // first purchase of this item
            for (int b = price; b <= budget; b++) {
                if (dp[b - price] != NEG) {
                    active[b] =
                            Math.max(active[b],
                                    dp[b - price] + 1 + bonus[i]);
                }
            }

            // additional purchases
            for (int b = price; b <= budget; b++) {
                if (active[b - price] != NEG) {
                    active[b] =
                            Math.max(active[b],
                                    active[b - price] + 1);
                }
            }

            for (int b = 0; b <= budget; b++) {
                next[b] = Math.max(next[b], active[b]);
            }

            dp = next;
        }

        int ans = 0;

        for (int x : dp) {
            ans = Math.max(ans, x);
        }

        return ans;
    }
}