class Solution {
    public int maximizeSum(int[] nums, int k) {
        Arrays.sort(nums);
        int n = nums.length;
        int lastnum = nums[n - 1];
        int maxsum = 0;
        for (int i = 1; i <= k; i++) {
            maxsum += lastnum;
            lastnum++;
        }

        return maxsum;

    }
}