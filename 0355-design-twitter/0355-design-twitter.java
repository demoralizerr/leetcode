class Twitter {

    private int time = 0;
    private Map<Integer, List<int[]>> tweetMap;
    private Map<Integer, Set<Integer>> followMap;

    public Twitter() {
        tweetMap = new HashMap<>();
        followMap = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        tweetMap.computeIfAbsent(userId, k -> new ArrayList<>()).add(new int[] { time++, tweetId });
    }

    public List<Integer> getNewsFeed(int userId) {
        PriorityQueue<int[]> maxHeap = new PriorityQueue<>((a, b) -> b[0] - a[0]);
        List<Integer> res = new ArrayList<>();
        Set<Integer> following = followMap.getOrDefault(userId, new HashSet<>());
        Set<Integer> allRelevantIds = new HashSet<>(following);
        allRelevantIds.add(userId);

        for (Integer id : allRelevantIds) {
            List<int[]> tweets = tweetMap.get(id);
            if (tweets != null && !tweets.isEmpty()) {
                int lastIdx = tweets.size() - 1;
                int[] tweet = tweets.get(lastIdx);

                maxHeap.offer(new int[] { tweet[0], tweet[1], id, lastIdx });
            }
        }
        while (!maxHeap.isEmpty() && res.size() < 10) {
            int[] curr = maxHeap.poll();
            res.add(curr[1]);//add tweeId in result
            int ownerId = curr[2];
            int idx = curr[3];
            // If this user has an older tweet (index > 0), push it into the heap
            if (idx > 0) {
                int[] olderTweet = tweetMap.get(ownerId).get(idx - 1);
                maxHeap.offer(new int[] { olderTweet[0], olderTweet[1], ownerId, idx - 1 });
            }

        }
        return res;
    }

    public void follow(int followerId, int followeeId) {
        if (followerId == followeeId)
            return;
        followMap.computeIfAbsent(followerId, k -> new HashSet<>()).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (followMap.containsKey(followerId)) {
            followMap.get(followerId).remove(followeeId);
        }
    }
}

/**
 * Your Twitter object will be instantiated and called as such:
 * Twitter obj = new Twitter();
 * obj.postTweet(userId,tweetId);
 * List<Integer> param_2 = obj.getNewsFeed(userId);
 * obj.follow(followerId,followeeId);
 * obj.unfollow(followerId,followeeId);
 */