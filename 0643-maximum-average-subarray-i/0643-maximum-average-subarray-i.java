class Solution {
    public double findMaxAverage(int[] nums, int k) {
        int n = nums.length;
        int sum = 0;

        // First window
        for (int i = 0; i < k; i++)
            sum += nums[i];

        int maxSum = sum;

        // Slide the window
        for (int i = k; i < n; i++) {
            sum += nums[i] - nums[i - k];
            maxSum = Math.max(maxSum, sum);
        }

        return maxSum * 1.0 / k;
    }
}
