class Solution {
    public boolean search(int[] nums, int target) {
        int low=0; int high = nums.length-1;
        while(low<=high){
            int mid= low+(high-low)/2;
            if(nums[mid]==target) return true;
            else if(nums[low]<nums[mid]){ //left sorted array
                  if(nums[low]<=target && target<nums[mid])
                     high= mid-1;
                  else
                     low = mid+1;
            }
            else if(nums[low]>nums[mid]){ //right sorted array
                 if(nums[mid]<target && target<=nums[high])
                    low = mid + 1;
                  else
                   high = mid - 1;     
            }
            else if(nums[low]==nums[mid]){
                  low++; 
    //move low by 1 place because if nums[mid] is not equal to target means nums[low] cannot be equal to target
            }
        }
        return false;
    }
}