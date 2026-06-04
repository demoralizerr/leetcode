class Solution {

    static class State {
        long ways;
        long waviness;

        State(long ways, long waviness) {
            this.ways = ways;
            this.waviness = waviness;
        }
    }

    private String s;
    private Map<String, State> memo;

    public long totalWaviness(long num1, long num2) {
        return solve(num2) - solve(num1 - 1);
    }

    private long solve(long n) {
        if (n < 0) {
            return 0;
        }

        s = String.valueOf(n);
        memo = new HashMap<>();

        return dfs(0, 10, 10, false, true).waviness;
    }

    private State dfs(int pos,
                      int prev2,
                      int prev1,
                      boolean started,
                      boolean tight) {

        if (pos == s.length()) {
            return new State(1, 0);
        }

        String key = pos + "|" + prev2 + "|" + prev1 + "|" + started;

        if (!tight && memo.containsKey(key)) {
            return memo.get(key);
        }

        int limit = tight ? s.charAt(pos) - '0' : 9;

        long totalWays = 0;
        long totalWaviness = 0;

        for (int d = 0; d <= limit; d++) {

            boolean nextTight = tight && (d == limit);

            if (!started && d == 0) {

                State child = dfs(
                        pos + 1,
                        10,
                        10,
                        false,
                        nextTight
                );

                totalWays += child.ways;
                totalWaviness += child.waviness;

            } else {

                int add = 0;

                if (prev2 != 10) {
                    if ((prev1 > prev2 && prev1 > d)
                            || (prev1 < prev2 && prev1 < d)) {
                        add = 1;
                    }
                }

                int newPrev2;
                int newPrev1;

                if (prev2 == 10) {
                    newPrev2 = prev1;
                    newPrev1 = d;
                } else {
                    newPrev2 = prev1;
                    newPrev1 = d;
                }

                State child = dfs(
                        pos + 1,
                        newPrev2,
                        newPrev1,
                        true,
                        nextTight
                );

                totalWays += child.ways;

                totalWaviness += child.waviness;

                totalWaviness += (long) add * child.ways;
            }
        }

        State ans = new State(totalWays, totalWaviness);

        if (!tight) {
            memo.put(key, ans);
        }

        return ans;
    }
}