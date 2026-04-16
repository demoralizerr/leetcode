class Solution {
    public int minAbsoluteDifference(int[] nums) {
        int mindiff = Integer.MAX_VALUE;
        int n = nums.length;
        for (int i = 0; i < n ; i++) {
            for (int j = 0; j < n; j++) {
                if ( i!=j && nums[i] == 1 && nums[j] == 2) {
                    int diff = Math.abs(i - j);
                    mindiff = Math.min(mindiff, diff);
                }

            }
        }
        return mindiff == Integer.MAX_VALUE ? -1 : mindiff;
    }
}