class Solution {
    public int pivotIndex(int[] nums) {
        int n = nums.length;
        int[] prefsum = new int[n];
        int[] suffsum = new int[n];
        int pivot = -1;
        int lsum = 0;
        int rsum = 0;
        int currsum = 0;
        
        for (int i = 0; i < n; i++) {
            currsum = currsum + nums[i];
            prefsum[i] = currsum;
        }
        currsum = 0;
        for (int i = n - 1; i >= 0; i--) {
            currsum = currsum + nums[i];
            suffsum[i] = currsum;
        }

        for (int i = 0; i < n; i++) {
            if (i - 1 >= 0) {
                lsum = prefsum[i - 1];
            } else
                lsum = 0;

            if (i + 1 < n)
                rsum = suffsum[i + 1];
            else
                rsum = 0;

            if (lsum == rsum) {
                pivot = i;
                break;
            }

        }
        return pivot;
    }
}