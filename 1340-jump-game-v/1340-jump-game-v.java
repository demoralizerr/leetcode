class Solution {
    public int maxJumps(int[] arr, int d) {

        int n = arr.length;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);

        int max = Integer.MIN_VALUE;
        int res = 0;
        for (int i = 0; i < n; i++) {
            res = solve(arr, d, i, dp);
            max = Math.max(max, res);

        }
        return max;

    }

    public int solve(int[] arr, int d, int currindex, int[] dp) {
        if (currindex < 0 || currindex >= arr.length)
            return 0;

        if (dp[currindex] != -1)
            return dp[currindex];

        int right = 0;
        int left = 0;

        for (int i = 1; i <= d; i++) {
            if (currindex + i < arr.length && arr[currindex] > arr[currindex + i]) {
                right = Math.max(right, solve(arr, d, currindex + i, dp));
            } else
                break;

        }

        for (int i = 1; i <= d; i++) {
            if (currindex - i >= 0 && arr[currindex] > arr[currindex - i]) {
                left = Math.max(left, solve(arr, d, currindex - i, dp));
            } else
                break;
        }

        dp[currindex] = 1 + Math.max(right, left);
        return dp[currindex];

    }
}