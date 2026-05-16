class Solution {
    public long maximumSubarraySum(int[] nums, int k) {

        int n = nums.length;
        long maxsum = 0;
        int i = 0;
        long currsum =0;
        Map<Integer, Integer> mp = new HashMap<>();
        for (int j = 0; j < n; j++) {
            //first add right item into sum
            currsum += nums[j];
            mp.put(nums[j], mp.getOrDefault(nums[j], 0) + 1);

            //check window is valid or not
            if (j - i + 1 > k) {
                //shrink window
                currsum = currsum - nums[i];
                mp.put(nums[i], mp.get(nums[i]) - 1);
                if (mp.get(nums[i]) == 0)
                    mp.remove(nums[i]);

                i++;

            }

            // 3. Update max if window is exactly size k and all elements are unique
            if (j - i + 1 == k && mp.size() == k) {
                maxsum = Math.max(maxsum, currsum);
            }

        }
        return maxsum;
    }
}