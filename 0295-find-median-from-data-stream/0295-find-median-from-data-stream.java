class MedianFinder {
    Queue<Integer> small;
    Queue<Integer> large;

    public MedianFinder() {
        small = new PriorityQueue<>((a, b) -> Integer.compare(b, a)); // max heap
        large = new PriorityQueue<>(); //min heap
    }

    public void addNum(int num) {
        small.offer(num);
        //check for size length & left half should be <= right half
        if (small.size() > large.size() + 1 || !large.isEmpty() && small.peek() > large.peek())
            large.offer(small.poll());

        if (large.size() > small.size() + 1)
            small.offer(large.poll());
    }

    public double findMedian() {
        if (small.size() == large.size()) {
            //even
            return (double) (small.peek() + large.peek()) / 2;
        } else if (small.size() > large.size()) {
            return (double) small.peek();
        } else
            return (double) large.peek();
    }
}

/**
 * Your MedianFinder object will be instantiated and called as such:
 * MedianFinder obj = new MedianFinder();
 * obj.addNum(num);
 * double param_2 = obj.findMedian();
 */