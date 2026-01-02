class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(nums);               // required
        solve(0, nums, new ArrayList<>(), result);
        return result;
    }

    public void solve(int index, int[] nums,
                      List<Integer> current,
                      List<List<Integer>> result) {

        // add current subset (every node is a valid subset)
        result.add(new ArrayList<>(current));

        for (int i = index; i < nums.length; i++) {

            // skip duplicates at the same recursion level
            if (i > index && nums[i] == nums[i - 1]) continue;

            current.add(nums[i]);
            solve(i + 1, nums, current, result);
            current.remove(current.size() - 1); // backtrack
        }
    }
}
