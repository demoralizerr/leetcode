class Solution {
    public int[] gcdValues(int[] nums, long[] queries) {
        final int n = nums.length;

        int maxVal = 0;
        for (int num : nums) {
            maxVal = Math.max(maxVal, num);
        }

        int[] divisorFreq = new int[maxVal + 1];

        // Count how many numbers are divisible by each divisor
        for (int num : nums) {
            for (int d = 1; d * d <= num; d++) {
                if (num % d == 0) {
                    divisorFreq[d]++;
                    if (d != num / d) {
                        divisorFreq[num / d]++;
                    }
                }
            }
        }

        long[] pairsWithGcd = new long[maxVal + 1];

        // Inclusion-Exclusion to count pairs with exact gcd
        for (int g = maxVal; g >= 1; g--) {
            long count = divisorFreq[g];
            pairsWithGcd[g] = count * (count - 1) / 2;

            for (int multiple = 2 * g; multiple <= maxVal; multiple += g) {
                pairsWithGcd[g] -= pairsWithGcd[multiple];
            }
        }

        // Prefix sums
        long[] prefixCountGcd = new long[maxVal + 1];
        for (int g = 1; g <= maxVal; g++) {
            prefixCountGcd[g] = prefixCountGcd[g - 1] + pairsWithGcd[g];
        }

        int[] result = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            long idx = queries[i];

            int left = 1;
            int right = maxVal;
            int answer = 1;

            while (left <= right) {
                int mid = left + (right - left) / 2;

                if (prefixCountGcd[mid] > idx) {
                    answer = mid;
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            result[i] = answer;
        }

        return result;
    }
}