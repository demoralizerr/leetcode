class Solution {

    public boolean startJumps(int[] nums, int index, Boolean[] dp) {
        //base case;
        if (index == nums.length - 1)
            return true;

        if (index >= nums.length || nums[index] == 0)
            return false;

        if (dp[index] != null)
            return dp[index];

        boolean result = false;
        for (int i = 1; i <= nums[index]; i++) {
            result = startJumps(nums, index + i, dp);
            if (result)
                break;
        }

        dp[index] = result;
        return dp[index];
    }

    public boolean canJump(int[] nums) {
        int n = nums.length;
        Boolean[] dp = new Boolean[n + 1];

        boolean ans = startJumps(nums, 0, dp);
        return ans;
    }
}