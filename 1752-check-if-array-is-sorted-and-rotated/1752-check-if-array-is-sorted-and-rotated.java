class Solution {
    public boolean check(int[] nums) {
        int n = nums.length;
        int[] sortedOriginal = nums.clone();
        Arrays.sort(sortedOriginal);

        boolean res = false;

        for (int k = 1; k <= n; k++) {
            int[] b = sortedOriginal.clone();
            rotate(b, k);

            res = true;
            for (int i = 0; i < n; i++) {
                if (nums[i] != b[i]) {
                    res = false;
                    break;
                }

            }
            if (res)
                return true;
        }
        return false;
    }

    public void rotate(int[] nums, int k) {
        if (nums == null || nums.length == 0)
            return;

        int n = nums.length;
        k = k % n;

        reverse(nums, n - k, n - 1); // Reverse last k elements
        reverse(nums, 0, n - k - 1); //Reverse remaining elements
        reverse(nums, 0, n - 1); //Reverse the entire array
    }

    private void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--;
        }
    }
}