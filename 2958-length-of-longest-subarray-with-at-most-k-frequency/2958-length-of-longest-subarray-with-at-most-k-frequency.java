class Solution {
    public int maxSubarrayLength(int[] nums, int k) {
        Map<Integer, Integer> count = new HashMap<>();
        int left = 0;
        int best = 0;

        for (int right = 0; right < nums.length; right++) {
            int val = nums[right];
            count.merge(val, 1, Integer::sum);

            while (count.get(val) > k) {
                int leftVal = nums[left];
                count.merge(leftVal, -1, Integer::sum);
                left++;
            }

            best = Math.max(best, right - left + 1);
        }

        return best;
    }
}