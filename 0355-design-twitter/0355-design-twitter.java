class Twitter {

    class Data {
        int userId;
        int tweetId;
        int time;

        public Data(int userId, int tweetId, int time) {
            this.userId = userId;
            this.tweetId = tweetId;
            this.time = time;
        }
    }

    private Queue<Data> maxheap;
    private List<Data> allTweets;
    private Map<Integer, List<Integer>> post;
    private Map<Integer, List<Integer>> follow;
    private int time = 0;

    public Twitter() {
        maxheap = new PriorityQueue<>((a, b) -> b.time - a.time);
        allTweets = new ArrayList<>();
        post = new HashMap<>();
        follow = new HashMap<>();
    }

    public void postTweet(int userId, int tweetId) {
        time++;

        if (!post.containsKey(userId)) {
            post.put(userId, new ArrayList<>());
        }
        post.get(userId).add(tweetId);
        allTweets.add(new Data(userId, tweetId, time));
    }

    public List<Integer> getNewsFeed(int userId) {
        List<Integer> topFeeds = new ArrayList<>();
        List<Integer> following = follow.getOrDefault(userId, Collections.emptyList());

        for (int i = allTweets.size() - 1; i >= 0 && topFeeds.size() < 10; i--) {
            Data d = allTweets.get(i);
            if (d.userId == userId || following.contains(d.userId))
                topFeeds.add(d.tweetId);
        }
        return topFeeds;

    }

    public void follow(int followerId, int followeeId) {
        if (!follow.containsKey(followerId)) {
            follow.put(followerId, new ArrayList<>());
        }
        follow.get(followerId).add(followeeId);
    }

    public void unfollow(int followerId, int followeeId) {
        if (follow.containsKey(followerId) && followerId != followeeId) {
            follow.get(followerId).remove(Integer.valueOf(followeeId));
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