class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        solve(0,candidates,target,new ArrayList<>(),result);
        return result;
    }

    public void solve(int index, int[] candidates,int target,List<Integer> current, List<List<Integer>> result){

            //base case
            if(index>=candidates.length || target<0) return;

            if(target==0){
                //found subset , save result & return
                result.add(new ArrayList<>(current)); //creating new copy as current keeps changing
                return;
            }

        //take candidate
        current.add(candidates[index]);
        solve(index,candidates,target-candidates[index],current,result);

        //backtrack remove last picked item
        current.remove(current.size()-1);
        //not take candidate
        solve(index+1,candidates,target,current,result);
    }
}