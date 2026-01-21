class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;

        // Find pivot index (index of minimum element)
        int pivot = findPivot(nums);

        // Search in right sorted portion
        int result = binarySearch(nums, pivot, n - 1, target);
        if (result != -1)
            return result;

        // Search in left sorted portion
        return binarySearch(nums, 0, pivot - 1, target);
    }

    private int binarySearch(int[] nums, int low, int high, int target) {
        while (low <= high) {
            int mid = low + (high - low) / 2;
            if (nums[mid] == target)
                return mid;
            else if (nums[mid] > target)
                high = mid - 1;
            else
                low = mid + 1;
        }
        return -1;
    }

    // Find index of minimum element (rotation point)
    private int findPivot(int[] nums) {
        int low = 0, high = nums.length - 1;

        while (low < high) {
            int mid = low + (high - low) / 2;

            // If mid element is greater than last element,
            // minimum must be in right half
            if (nums[mid] > nums[high]) {
                low = mid + 1;
            } else {
                // Otherwise, minimum is in left half (including mid)
                high = mid;
            }
        }
        return low; // or high, they're equal
    }
}