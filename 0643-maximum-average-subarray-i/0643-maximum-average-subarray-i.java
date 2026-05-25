class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int left = 0;
        double ans = 0;
        int n = nums.length;
        double currsum = 0;
        double curravg = 0;
        double maxavg = 0;
        for (int i = 0; i < k; i++) {
            currsum += nums[i];
        }
        maxavg = currsum/k;

        for (int right = k; right < n; right++) {
            currsum += nums[right];
            currsum = currsum - nums[left];
            left++;

            maxavg = Math.max(currsum / k, maxavg);

        }
        return maxavg;

    }
}