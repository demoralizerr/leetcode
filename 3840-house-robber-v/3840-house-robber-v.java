class Solution {
    public long rob(int[] nums, int[] colors) {
        int n=nums.length;
        if(n==0) return 0;
        if(n==1) return (long)nums[0];

        long[] dp = new long[n];
        dp[0] = nums[0];

        //second house
        if(colors[0]!=colors[1]){
            dp[1] = (long) nums[0]+nums[1];
        }
        else{
            dp[1] = Math.max(nums[0],nums[1]);
        }
        //DP
        for(int i=2;i<n;i++){
            long skipcurr = dp[i-1];
            long robcurr;

            if(colors[i]!=colors[i-1]){
                robcurr = nums[i] + dp[i-1]; 
            }
            else{
                robcurr = nums[i] + dp[i-2];
            }
            dp[i] = Math.max(skipcurr , robcurr);
        }
        return dp[n-1];
    }
}