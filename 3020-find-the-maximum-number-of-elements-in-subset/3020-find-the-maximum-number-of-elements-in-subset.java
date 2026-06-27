class Solution {
    public int maximumLength(int[] nums) {
        Map<Integer, Integer> mp = new HashMap<>();

        for (int num : nums)
            mp.put(num, mp.getOrDefault(num, 0) + 1);

        int maxlen = Integer.MIN_VALUE;
        long sq = 1;

        for (int key : mp.keySet()) {
            long start = key;
            long curr = start;
            int currlen = 1;

            if (key == 1) {
                int ones = mp.get(key);
                if (ones % 2 == 0) {
                    currlen = ones - 1;
                } else {
                    currlen = ones;
                }
                maxlen = Math.max(maxlen, currlen);
                continue;
            }

            while (true) {

                int currfreq = mp.getOrDefault((int) curr, 0);
                if (currfreq == 1)
                    break;

                long next = curr * curr;
                if (next > 1_000_000_000L) {
                    break;
                }
                int freq = mp.getOrDefault((int) next, 0);

                if (freq >= 2) {
                    currlen += 2;
                    curr = next;
                } else if (freq == 0) {
                    break;
                } else if (freq == 1) {
                    currlen += 2;
                    break;
                }

            }
            maxlen = Math.max(maxlen, currlen);

        }

        return maxlen;

    }
}