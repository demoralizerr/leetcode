class Solution {
    public int minEatingSpeed(int[] piles, int h) {
        // Find the maximum pile size to set upper bound for binary search
        int maxPile = Integer.MIN_VALUE;
        int n = piles.length;
        for (int pile : piles) {
            maxPile = Math.max(maxPile, pile);
        }

        // Binary search between 1 (minimum speed) and maxPile (maximum speed)
        int low = 1;
        int high = maxPile;
        int k = 0;
        
        while (low <= high) {
            // Calculate mid-point as current eating speed to test
            k = low + (high - low) / 2;
            int hourLeft = h;
            int idx = 0;
            
            // Try eating all piles at speed k
            while (idx < n) {
                // If no hours left, stop early
                if (hourLeft == 0)
                    break;
                    
                int pile = piles[idx];
                
                // Calculate hours needed for current pile
                // If pile divides evenly, use pile/k, otherwise use ceiling (1 + pile/k)
                if (pile % k == 0)
                    hourLeft = hourLeft - (pile / k);
                else
                    hourLeft = hourLeft - (1 + (pile / k));
                    
                idx++;
            }

            // If we couldn't finish all piles OR used more hours than allowed,
            // speed k is too slow, search higher speeds
            if (idx < n || hourLeft < 0)
                low = k + 1;
            // Otherwise, k works, but try to find a slower speed
            else
                high = k - 1;
        }
        
        // low points to the minimum valid eating speed
        return low;
    }
}