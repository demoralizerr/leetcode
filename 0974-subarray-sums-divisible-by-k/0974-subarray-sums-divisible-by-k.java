class Solution {
    public int subarraysDivByK(int[] nums, int k) {
        //( prefsum[r] - prefsum[l-1] ) % k == 0
        // prefsum[r] % k == prefsum[l-1]%k

        int prefsum = 0;
        int count = 0;
        int n = nums.length;
        Map<Integer, Integer> mp = new HashMap<>(); // store item:count
        mp.put(0, 1);
        for (int i = 0; i < n; i++) {
            prefsum += nums[i];
            int rem = ((prefsum % k) + k) % k;

            if (mp.containsKey(rem)) {
                count += mp.get(rem);
                mp.put(rem, mp.get(rem) + 1);
            } else
                mp.put(rem, mp.getOrDefault(rem, 0) + 1);
        }

        return count;
    }
}