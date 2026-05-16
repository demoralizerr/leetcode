class Solution {
    public int deleteAndEarn(int[] nums) {
        int n = nums.length;

        int max = 0;
        for (int num : nums)
            max = Math.max(max, num);

        int[] points = new int[max + 1];
        int[] dp = new int[max + 1];
        Arrays.fill(dp, -1);

        for (int num : nums) {
            points[num] += num;
        }

        return solve(points, 0, dp);
    }

    public int solve(int[] points, int index, int[] dp) {

        if (index >= points.length)
            return 0;

        if (dp[index] != -1)
            return dp[index];

        int notrob = solve(points, index + 1, dp);

        int rob = points[index] + solve(points, index + 2, dp);
        dp[index] = Math.max(rob, notrob);
        return dp[index];
    }
}