import java.util.*;

public class Solution {
    public long countSubarrays(int[] nums, int k, int m) {
        int n = nums.length;
        long totalCount = 0;

        // Frequencies for the two windows (at most k and at most k-1)
        Map<Integer, Integer> freq1 = new HashMap<>();
        Map<Integer, Integer> freq2 = new HashMap<>();
        int dist1 = 0, dist2 = 0;

        // Tracks the indices of the last m occurrences of each number
        Map<Integer, Deque<Integer>> lastMIndices = new HashMap<>();
        // Tracks the m-th index of each active distinct element to find the minimum easily
        TreeMap<Integer, Integer> mThPosValues = new TreeMap<>();

        int l1 = 0, l2 = 0;

        for (int r = 0; r < n; r++) {
            int val = nums[r];

            // Update frequency maps and distinct counts
            if (freq1.getOrDefault(val, 0) == 0) dist1++;
            freq1.put(val, freq1.getOrDefault(val, 0) + 1);

            if (freq2.getOrDefault(val, 0) == 0) dist2++;
            freq2.put(val, freq2.getOrDefault(val, 0) + 1);

            // Update the m-th occurrence tracking
            lastMIndices.putIfAbsent(val, new ArrayDeque<>());
            Deque<Integer> deq = lastMIndices.get(val);
        
            if (deq.size() == m) {
                mThPosValues.remove(deq.peekFirst());
            }
            
            deq.addLast(r);
            if (deq.size() > m) {
                deq.removeFirst();
            }
            
            if (deq.size() == m) {
                mThPosValues.put(deq.peekFirst(), val);
            }

            // Maintain l1: window has at most k distinct
            while (dist1 > k) {
                int out = nums[l1];
                freq1.put(out, freq1.get(out) - 1);
                if (freq1.get(out) == 0) {
                    dist1--;
                    Deque<Integer> d = lastMIndices.get(out);
                    if (d.size() == m) {
                        mThPosValues.remove(d.peekFirst());
                    }
                }
                l1++;
            }

            // Maintain l2: window has at most k-1 distinct
            while (dist2 > k - 1) {
                int out = nums[l2];
                freq2.put(out, freq2.get(out) - 1);
                if (freq2.get(out) == 0) dist2--;
                l2++;
            }

            // If we have exactly k distinct elements
            if (dist1 == k) {
                if (mThPosValues.size() == k) {
                    int minMThIndex = mThPosValues.firstKey();
                    int upperBound = Math.min(l2 - 1, minMThIndex);
                    if (upperBound >= l1) {
                        totalCount += (upperBound - l1 + 1);
                    }
                }
            }
        }

        return totalCount;
    }
}