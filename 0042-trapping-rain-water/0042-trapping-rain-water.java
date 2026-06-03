class Solution {
    public int trap(int[] height) {
        //track continuos increasing items 
        int n = height.length;
        int left = 0;
        int right = n - 1;
        int leftmax = height[left];
        int rightmax = height[right];
        int totalWater = 0;

        while (left < right) {
            if (leftmax < rightmax) {
                left++;
                leftmax = Math.max(leftmax, height[left]);
                totalWater += leftmax - height[left];
            } else {
                right--;
                rightmax = Math.max(rightmax, height[right]);
                totalWater += rightmax - height[right];
            }
        }
        return totalWater;

    }
}