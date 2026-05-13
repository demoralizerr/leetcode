class Solution {

    public int minMoves(int[] nums, int limit) {

        int n = nums.length;

        // difference array
        int[] diff = new int[2 * limit + 2];

        for (int i = 0; i < n / 2; i++) {

            int a = nums[i];
            int b = nums[n - 1 - i];

            int x = Math.min(a, b);
            int y = Math.max(a, b);


            diff[2] += 2;
            diff[2 * limit + 1] -= 2;

        
            diff[x + 1] -= 1;
            diff[y + limit + 1] += 1;

        
            diff[x + y] -= 1;
            diff[x + y + 1] += 1;
        }

        int ans = Integer.MAX_VALUE;

        int curr = 0;

        // rebuild actual costs using prefix sum
        for (int sum = 2; sum <= 2 * limit; sum++) {

            curr += diff[sum];

            ans = Math.min(ans, curr);
        }

        return ans;
    }
}