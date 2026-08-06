class Solution {
    public int maximumWidth(int[] planks) {
        Map<Integer, Integer> freq = new HashMap<>();
        for (int p : planks) freq.merge(p, 1, Integer::sum);

        int d = freq.size();
        int[] val = new int[d];
        int[] cnt = new int[d];
        int idx = 0;
        for (Map.Entry<Integer, Integer> e : freq.entrySet()) {
            val[idx] = e.getKey();
            cnt[idx] = e.getValue();
            idx++;
        }

        Map<Long, Integer> width = new HashMap<>();

        // Singles: a plank of value v used as-is contributes to H = v
        for (int i = 0; i < d; i++) {
            width.merge((long) val[i], cnt[i], Integer::sum);
        }

        // Pairs: two planks summing to H
        for (int i = 0; i < d; i++) {
            for (int j = i; j < d; j++) {
                long s = (long) val[i] + (long) val[j];
                int contrib = (i == j) ? cnt[i] / 2 : Math.min(cnt[i], cnt[j]);
                if (contrib > 0) {
                    width.merge(s, contrib, Integer::sum);
                }
            }
        }

        int ans = 0;
        for (int w : width.values()) {
            ans = Math.max(ans, w);
        }
        return ans;
    }
}