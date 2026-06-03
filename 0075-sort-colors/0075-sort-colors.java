class Solution {
    public void sortColors(int[] nums) {
        // move 2 at the end of array and 0 at start of array , 1 will automatic come at middle.
        //TC - 0(N) where N is the length of nums array
        moveTwoesAtEnd(nums);
        moveZeroesAtStart(nums);

    }

    public void moveTwoesAtEnd(int[] nums) {
        int n = nums.length;
        int l = 0;

        for (int r = 1; r < n; r++) {
            if (nums[r] != 2 && nums[l] == 2) {
                nums[l] = nums[r];
                nums[r] = 2;
                l++;
            } else if (nums[r] == 2 && nums[l] != 2) {
                l = r;
            }
        }

    }

    public void moveZeroesAtStart(int[] nums) {
        int n = nums.length;
        int l = 0;

        for (int r = 1; r < n; r++) {
            if (nums[r] == 0 && nums[l] != 0) {
                nums[r] = nums[l];
                nums[l] = 0;
                l++;
            } else if (nums[r] != 0 && nums[l] == 0) {
                l = r;
            }
        }

    }
}