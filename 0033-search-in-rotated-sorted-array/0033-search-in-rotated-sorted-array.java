class Solution {
    public int search(int[] nums, int target) {
        int n = nums.length;
        //find pivot index
        int pivot = pivot(nums, 0, n - 1);

        //do binary search on right side of pivot
        int rans = binarysearch(nums, pivot, n - 1, target);
        if (rans != -1)
            return rans;

        //do binary search on left side of pivot
        int lans = binarysearch(nums, 0, pivot-1, target);
        if (lans != -1)
            return lans;

        return -1;
    }

    private int binarysearch(int[] nums, int low, int high, int target) {
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

    //finds the index of element which is highest in rotated array(peak)
    private int pivot(int[] nums, int low, int high) {
        while (low < high) {
            int mid = low + (high - low) / 2;
            // If mid element is greater than last element,
            // minimum must be in right half
            if (nums[mid] > nums[high])
                low = mid+1;
            else
                high = mid; // Otherwise, minimum is in left half (including mid)
        }
        return low; // or high both are equal
    }
}