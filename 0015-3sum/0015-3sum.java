class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        int n= nums.length;
        Set<List<Integer>> set = new HashSet<>();
        Arrays.sort(nums);

        for(int i=0;i<n-2;i++){
         int l=i+1; int r=n-1;
         while(l<r){
             int sum = nums[l]+nums[r]+nums[i];

             if(sum==0){
                set.add(Arrays.asList(nums[i],nums[l],nums[r]));
                l++;
                r--;
             }
             else if(sum<0){
                l++;
             }
             else
             r--;


         }


        }
        return new ArrayList<>(set);
    }
}