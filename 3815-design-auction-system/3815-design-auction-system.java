class AuctionSystem {
    // Track all bids: itemId -> (userId -> bidAmount)
    private Map<Integer, Map<Integer, Integer>> itemBids;
    
    // Track sorted bids for quick max lookup: itemId -> TreeSet of [bidAmount, userId]
    private Map<Integer, TreeSet<int[]>> itemSortedBids;
    
    public AuctionSystem() {
        itemBids = new HashMap<>();
        itemSortedBids = new HashMap<>();
    }
    
    public void addBid(int userId, int itemId, int bidAmount) {
        // Initialize structures for this item if not exists
        itemBids.putIfAbsent(itemId, new HashMap<>());
        itemSortedBids.putIfAbsent(itemId, new TreeSet<>((a, b) -> {
            if (a[0] != b[0]) return Integer.compare(b[0], a[0]); // bidAmount DESC
            return Integer.compare(b[1], a[1]); // userId DESC (tiebreaker)
        }));
        
        // If user already has a bid, remove the old one from TreeSet
        if (itemBids.get(itemId).containsKey(userId)) {
            int oldAmount = itemBids.get(itemId).get(userId);
            itemSortedBids.get(itemId).remove(new int[]{oldAmount, userId});
        }
        
        // Add new bid to both structures
        itemBids.get(itemId).put(userId, bidAmount);
        itemSortedBids.get(itemId).add(new int[]{bidAmount, userId});
    }
    
    public void updateBid(int userId, int itemId, int newAmount) {
        // Same logic as addBid - it handles replacement
        addBid(userId, itemId, newAmount);
    }
    
    public void removeBid(int userId, int itemId) {
        // Get the bid amount before removing
        int bidAmount = itemBids.get(itemId).get(userId);
        
        // Remove from both structures
        itemBids.get(itemId).remove(userId);
        itemSortedBids.get(itemId).remove(new int[]{bidAmount, userId});
    }
    
    public int getHighestBidder(int itemId) {
        // Check if item exists and has bids
        if (!itemSortedBids.containsKey(itemId) || itemSortedBids.get(itemId).isEmpty()) {
            return -1;
        }
        
        // Get the first element (highest bid, highest userId)
        int[] winner = itemSortedBids.get(itemId).first();
        return winner[1]; // Return userId
    }
}