class Solution {
    public void moveZeroes(int[] nums) {
        if(nums.length==1) return;
        int left=0; int right=1;
        while(right<nums.length)
        {
                if(nums[left]==0 && nums[right]!=0){
                    int tmp = nums[left];
                    nums[left]=nums[right];
                    nums[right]= tmp;
                    left++;
                    right++;
                }
                else if(nums[left]==0 && nums[right]==0){
                    right++;
                }
                else{
                    left++; right++;
                }
        }
    }
}