class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;

        int[] a = m <= n ? nums1 : nums2;
        int[] b = m <= n ? nums2 : nums1;

        int total = m + n;
        int half = total / 2;

        int low = 0, high = a.length;

        while (true) {
            int i = (low + high) / 2;
            int j = half - i;

            int aleft = i > 0 ? a[i - 1] : Integer.MIN_VALUE;
            int aright = i < a.length ? a[i] : Integer.MAX_VALUE;

            int bleft = j > 0 ? b[j - 1] : Integer.MIN_VALUE;
            int bright = j < b.length ? b[j] : Integer.MAX_VALUE;

            if (aleft <= bright && bleft <= aright) {
                if (total % 2 == 0) {
                    return (Math.max(aleft, bleft) + Math.min(aright, bright)) / 2.0;
                } else {
                    return Math.min(aright, bright);
                }
            } else if (aleft > bright) {
                high = i - 1;
            } else {
                low = i + 1;
            }
        }
    }
}
