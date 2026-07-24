class Solution {
    public int uniqueXorTriplets(int[] nums) {
        int n = nums.length;
        Set<Integer> s = new HashSet<>();

        for (int i = 0; i < n; i++) {
            for (int j = i; j < n; j++) {
                s.add(nums[i] ^ nums[j]);
            }
        }

        Set<Integer> s1 = new HashSet<>();
        for (int pairxor : s) {
            for (int num : nums) {
                s1.add(pairxor ^ num);
            }
        }

        return s1.size();

    }
}