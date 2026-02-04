class Solution {
    public int minDays(int[] bloomDay, int m, int k) {
        int n = bloomDay.length;
        if ((long)m * k > n)
            return -1;

        int low = 1;
        int high = Integer.MIN_VALUE;
        for (int day : bloomDay) {
            high = Math.max(high, day);
        }

        while (low < high) {
            int mid = low + (high - low) / 2;
            int bq = 0;
            int flowers = 0;
            for (int i = 0; i < n; i++) {
                if (flowers == k) {
                    bq++;
                    flowers = 0;
                }
                if (mid >= bloomDay[i])
                    flowers++;
                else
                    flowers = 0;
            }
            if (flowers == k)
                bq++;
            if (bq >= m) {
                high = mid;
            } else
                low = mid + 1;
        }
        return low;
    }
}