class Solution {

    public int topDownDP(int[] nums, int target, int idx, int[] dp) {

        // base case
        if (idx == nums.length - 1)
            return 0;

        if (dp[idx] != -1)
            return dp[idx];

        int jumps = Integer.MIN_VALUE;
        for (int j = idx + 1; j < nums.length; j++) {
            //check valid jump
            if (Math.abs(nums[j] - nums[idx]) <= target) {

                int next = topDownDP(nums, target, j, dp);

                // only if reachable
                if (next != Integer.MIN_VALUE)
                    jumps = Math.max(jumps, 1 + next);
            }
        }
        dp[idx] = jumps;
        return jumps;

    }

    public int maximumJumps(int[] nums, int target) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        int ans = topDownDP(nums, target, 0, dp);
        return ans == Integer.MIN_VALUE ? -1 : ans;

    }
}