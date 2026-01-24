class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        int low = 0;
        int high = n - 1;
        //single pass binary search
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[low] <= nums[mid]) {  // Changed < to <=
                // we are in left sorted array
                //check if target lies in the range
                if (nums[low] <= target && target < nums[mid]) {
                    high = mid - 1;
                } else
                    low = mid + 1;
            } else {
                // we are in right sorted array
                //check if target lies in the range
                if (nums[mid] < target && target <= nums[high]) {  // Fixed both conditions
                    low = mid + 1;
                } else
                    high = mid - 1;
            }
        }
        return -1;
    }
}