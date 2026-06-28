class Solution {
    public int maximumElementAfterDecrementingAndRearranging(int[] arr) {
        Arrays.sort(arr);

        int max = Integer.MIN_VALUE;

        int curr = 1;

        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > curr)
                curr++;
            else
                curr = arr[i];

            max = Math.max(max, curr);
        }
        max = Math.max(max,curr);
        return max;
    }
}