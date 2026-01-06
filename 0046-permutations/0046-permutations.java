class Solution {
    public List<List<Integer>> permute(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        int[] selected = new int[nums.length];
        backtrack(new ArrayList<>(), nums, res,selected);
        return res;
    }


    private void backtrack(List<Integer> state, int[] nums, List<List<Integer>> res,int[] selected){
        
         if(state.size()==nums.length){
              res.add(new ArrayList<>(state));
              return;
         }

         for(int i =0; i<nums.length;i++){
             //Pruning
             if(selected[i]==0){
                selected[i] = 1;
                state.add(nums[i]);
            
                backtrack(state,nums,res,selected);
               
               // Backtrack
                selected[i] = 0;
                state.remove(state.size()-1);
             }

         }


    }
}