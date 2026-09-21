class Solution {
    public long[] resultArray(int[] nums, int k) {
        long[] result = new long[k];
        int[] cnt = new int[k];

        for (int num : nums) {
            int r = num % k;
            int[] newCnt = new int[k];

            for (int rem = 0; rem < k; rem++) {
                if (cnt[rem] > 0) {
                    newCnt[(rem * r) % k] += cnt[rem];
                }
            }
            newCnt[r] += 1; // the single-element subarray starting here

            cnt = newCnt;
            for (int x = 0; x < k; x++) {
                result[x] += cnt[x];
            }
        }

        return result;
    }
}