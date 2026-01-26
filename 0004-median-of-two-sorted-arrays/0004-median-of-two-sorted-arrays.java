class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] merged = new int[m + n];
        int l = 0;
        int r = 0;
        int idx = 0;
        while (l < m && r < n) {
            if (nums1[l] <= nums2[r]) {
                merged[idx] = nums1[l];
                l++;
            } else {
                merged[idx] = nums2[r];
                r++;
            }

            idx++;
        }

        while (l < m) {
            merged[idx] = nums1[l];
            l++;
            idx++;
        }

        while (r < n) {
            merged[idx] = nums2[r];
            r++;
            idx++;
        }
        int mid = merged.length / 2;
        if (merged.length % 2 != 0)
            return merged[mid];

        return (merged[mid] + merged[mid - 1]) / 2.0;

    }
}