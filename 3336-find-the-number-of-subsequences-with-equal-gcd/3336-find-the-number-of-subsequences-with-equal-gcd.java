import java.util.Arrays;

class Solution {

    public static final int MOD = 1_000_000_007;
    // Flattening the 3D array into a 1D array dramatically improves cache locality
    // State index calculation: (index * 201 * 201) + (gcd1 * 201) + gcd2
    int[] dp; 

    public int subsequencePairCount(int[] nums) {
        int n = nums.length;
        // Size: nums.length * 201 * 201
        this.dp = new int[n * 201 * 201];
        Arrays.fill(dp, -1);
        
        return solve(nums, 0, 0, 0);
    }

    public int solve(int[] nums, int index, int gcd1, int gcd2) {
        // Base Case
        if (index >= nums.length) {
            if (gcd1 != 0 && gcd2 != 0 && gcd1 == gcd2)
                return 1;
            else
                return 0;
        }

        // Calculate flat 1D index
        int flatIndex = (index * 40401) + (gcd1 * 201) + gcd2;

        if (dp[flatIndex] != -1)
            return dp[flatIndex];

        // Choice 1: Skip the current number
        long ans = solve(nums, index + 1, gcd1, gcd2);

        // Choice 2: Add to subsequence 1 (Handle initial 0 state explicitly)
        int nextGcd1 = (gcd1 == 0) ? nums[index] : findGCD(nums[index], gcd1);
        ans = (ans + solve(nums, index + 1, nextGcd1, gcd2)) % MOD;

        // Choice 3: Add to subsequence 2 (Handle initial 0 state explicitly)
        int nextGcd2 = (gcd2 == 0) ? nums[index] : findGCD(nums[index], gcd2);
        ans = (ans + solve(nums, index + 1, gcd1, nextGcd2)) % MOD;

        return dp[flatIndex] = (int) ans;
    }

    // Iterative GCD is slightly safer for deep recursion trees
    private int findGCD(int a, int b) {
        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }
        return a;
    }
}
