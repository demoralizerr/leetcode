class Solution {
    public int countValidSubarrays(int[] nums, int x) {
        int count = 0;
        int n = nums.length;

        for (int i = 0; i < n; i++) {
            long currsum = 0;
            for (int j = i; j < n; j++) {
                currsum += nums[j];

                String str = String.valueOf(currsum);
                int first = str.charAt(0) - '0';
                int last = str.charAt(str.length() - 1) - '0';

                if (first == x && last == x)
                    count++;
            }

        }
        return count;
    }
}