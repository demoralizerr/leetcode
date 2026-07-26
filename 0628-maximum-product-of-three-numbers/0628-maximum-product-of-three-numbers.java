class Solution {
    public int maximumProduct(int[] nums) {
        Arrays.sort(nums);
        int n = nums.length;
        int negcnt = 0;
        for (int num : nums) {
            if (num < 0)
                negcnt++;
        }
        int val1 = nums[n - 1] * nums[n - 2] * nums[n - 3];
        if (negcnt >= 2) {
            return Math.max(nums[n - 1] * nums[0] * nums[1], val1);
        }
        return val1;
    }
}