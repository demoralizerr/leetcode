class Solution {
    public int longestCommonPrefix(int[] arr1, int[] arr2) {
        Set<String> s1 = new HashSet<>();
          int max = 0;

        for (int arr : arr1) {
            String str = String.valueOf(arr);
            int n = str.length();
            for (int i = 0; i < n; i++) {
                String prefix = str.substring(0, i + 1);
                s1.add(prefix);
            }
        }

        for (int arr : arr2) {
            String str = String.valueOf(arr);
            int n = str.length();
            for (int i = 0; i < n; i++) {
                String prefix = str.substring(0, i + 1);
                if (s1.contains(prefix)) {
                    max = Math.max(max, prefix.length());
                }
            }
        }

        return max;
    }
}