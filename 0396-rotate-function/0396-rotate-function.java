class Solution {
    public int maxRotateFunction(int[] nums) {
        int n = nums.length;

        int totalSum = 0;
        int F = 0;

        // F(0) and total sum
        for (int i = 0; i < n; i++) {
            totalSum += nums[i];
            F += i * nums[i];
        }

        int max = F;

        //F(k) = F(k-1) + totalSum - n * nums[n-k]
        for (int k = 1; k < n; k++) {
            F = F + totalSum - n * nums[n - k];
            max = Math.max(max, F);
        }

        return max;
    }
}