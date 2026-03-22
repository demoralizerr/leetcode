class Solution {
    public int minRemovals(int[] nums, int target) {
        int n = nums.length;

        Map<Integer, Integer> dp = new HashMap<>();
        dp.put(0, 0);

        for (int num : nums) {
            Map<Integer, Integer> newDp = new HashMap<>(dp);

            for (int x : dp.keySet()) {
                int newXor = x ^ num;
                int newSize = dp.get(x) + 1;

                newDp.put(newXor, Math.max(newDp.getOrDefault(newXor, -1), newSize));
            }

            dp = newDp;
        }

        if (!dp.containsKey(target)) return -1;

        return n - dp.get(target);
    }
}