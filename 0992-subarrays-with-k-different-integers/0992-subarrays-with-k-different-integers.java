class Solution {
    public int subarraysWithKDistinct(int[] nums, int k) {
        int atmostk = subarrays(nums, k);
        int atmostkminusone = subarrays(nums, k - 1);
        return atmostk - atmostkminusone;
    }

    public int subarrays(int[] nums, int k) {
        int n = nums.length;
        int left = 0;
        int count = 0;

        Map<Integer, Integer> mp = new HashMap<>();
        for (int right = 0; right < n; right++) {
            mp.put(nums[right], mp.getOrDefault(nums[right], 0) + 1);

            while (mp.size() > k) {
                //shrink from left
                int item = nums[left];
                mp.put(item, mp.get(item) - 1);
                if (mp.get(item) == 0)
                    mp.remove(item);
                left++;
            }

            count += right - left + 1;
        }
        return count;
    }
}