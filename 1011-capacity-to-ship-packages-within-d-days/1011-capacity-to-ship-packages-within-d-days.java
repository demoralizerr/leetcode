class Solution {
    public int shipWithinDays(int[] weights, int days) {
        int maxCap = 0;
        int low = 0;
        for (int weight : weights) {
            maxCap += weight;
            low = Math.max(low, weight); // low should be max single weight
        }

        int high = maxCap;
        while (low < high) {
            int mid = low + (high - low) / 2;
            int daysUsed = 1;
            int cap = 0;
            for (int weight : weights) {
                if (cap + weight > mid) {
                    daysUsed++;
                    cap = weight;
                } else {
                    cap += weight;
                }
            }

            if (daysUsed > days)
                low = mid + 1;
            else {
                high = mid;
            }

        }
        return high;
    }
}