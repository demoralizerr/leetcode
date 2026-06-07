class Solution {
    public int[] productExceptSelf(int[] nums) {
        int totalmul = 1;
        int n = nums.length;
        int[] res = new int[n];
        int[] prefmul = new int[n];
        int[] suffmul = new int[n];

        prefmul[0] = nums[0];
        suffmul[n - 1] = nums[n - 1];
        
        for (int i = 1; i < n; i++) {
            prefmul[i] = nums[i] * prefmul[i - 1];
        }

        for (int i = n - 2; i >= 0; i--) {
            suffmul[i] = nums[i] * suffmul[i + 1];
        }

        for (int i = 0; i < n; i++) {
            int lmul = 1;
            int rmul = 1;
            if (i - 1 >= 0)
                lmul = prefmul[i - 1];
            if (i + 1 < n)
                rmul = suffmul[i + 1];

            res[i] = lmul * rmul;
        }

        return res;

    }
}