class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);
         solve(0,nums,new ArrayList<>(), result);
        return result;
    }

    public void solve(int index,int[] nums, List<Integer> current,List<List<Integer>> result ){
              
              //base case
              if(index>=nums.length){
                //check current set already present in result or not 
                if(!result.contains(current)) result.add(new ArrayList<>(current));
                return;
              }

              //pick current num 
              current.add(nums[index]);
              solve(index+1,nums,current,result);

              //backtrack remove last picked num
              current.remove(current.size()-1);
              //not pick current num
              solve(index+1,nums,current,result);

    }
}