class Solution {

    static final int MOD = 1_000_000_007;

    public int zigZagArrays(int n, int l, int r) {

        int m = r - l + 1;

        // down[v] -> last value = v, next comparison should be DOWN
        // up[v]   -> last value = v, next comparison should be UP
        long[] down = new long[m];
        long[] up = new long[m];

        // Initialize using the first two elements
        for (int first = 0; first < m; first++) {
            for (int second = 0; second < m; second++) {

                if (first == second)
                    continue;

                if (first < second)
                    down[second] = (down[second] + 1) % MOD;
                else
                    up[second] = (up[second] + 1) % MOD;
            }
        }

        // Already built arrays of length 2
        for (int pos = 2; pos < n; pos++) {

            long[] newDown = new long[m];
            long[] newUp = new long[m];

            // prefix of UP
            long[] prefix = new long[m];
            prefix[0] = up[0];
            for (int i = 1; i < m; i++) {
                prefix[i] = (prefix[i - 1] + up[i]) % MOD;
            }

            // suffix of DOWN
            long[] suffix = new long[m];
            suffix[m - 1] = down[m - 1];
            for (int i = m - 2; i >= 0; i--) {
                suffix[i] = (suffix[i + 1] + down[i]) % MOD;
            }

            for (int v = 0; v < m; v++) {

                // Need DOWN
                if (v > 0)
                    newDown[v] = prefix[v - 1];

                // Need UP
                if (v + 1 < m)
                    newUp[v] = suffix[v + 1];
            }

            down = newDown;
            up = newUp;
        }

        long ans = 0;

        for (long x : down)
            ans = (ans + x) % MOD;

        for (long x : up)
            ans = (ans + x) % MOD;

        return (int) ans;
    }
}