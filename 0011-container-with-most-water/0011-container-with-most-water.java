class Solution {
    public int maxArea(int[] height) {
        int n = height.length;
        int l = 0;
        int r = n - 1;
        int maxarea = Integer.MIN_VALUE;

        while (l < r) {
            int width = r - l;
            if (height[l] == height[r]) {
                maxarea = Math.max(maxarea, height[l] * width);
                l++;
                r--;
            } else if (height[l] < height[r]) {
                maxarea = Math.max(maxarea, height[l] * width);
                l++;
            } else {
                maxarea = Math.max(maxarea, height[r] * width);
                r--;
            }
        }
        return maxarea;
    }
}