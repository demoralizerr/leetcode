class Solution {
    public int removeDuplicates(int[] nums) {
        int n = nums.length;
        if(n==1) return n;
        int idx=1;
        int l =0; int r=1;
       while(r<n){
        
         if(nums[r]==nums[l]){r++;}
         else {
            nums[idx++] = nums[r];
            l = r;
            r++;
         }
       }
        return idx;
    }
}