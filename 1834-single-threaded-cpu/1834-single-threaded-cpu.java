class Solution {
    public int[] getOrder(int[][] tasks) {
        int n = tasks.length;
        int[][] indexedTasks = new int[n][3];

        // fill indexedTasks with {enqeuetime , processingtime and index }
        for (int i = 0; i < n; i++) {
            indexedTasks[i][0] = tasks[i][0];
            indexedTasks[i][1] = tasks[i][1];
            indexedTasks[i][2] = i;
        }

        //sort by enqeue time in asc order
        Arrays.sort(indexedTasks, (a, b) -> Integer.compare(a[0], b[0]));

        //define minheap based on processing time , store {processingtime, index}
        Queue<int[]> minheap = new PriorityQueue<>((a, b) -> {
            if (a[0] != b[0])
                return Integer.compare(a[0], b[0]); // compare by processing time 

            return Integer.compare(a[1], b[1]); //tie breaker compare by index
        });

        long time = 0;
        int taskidx = 0;
        int[] result = new int[n];
        int resultidx = 0;

        while (taskidx < n || !minheap.isEmpty()) {

            if (minheap.isEmpty()) {
                time = Math.max(time, indexedTasks[taskidx][0]);
            }
            //add all tasks matching with current time or less than it 
            while (taskidx < n && indexedTasks[taskidx][0] <= time) {
                minheap.offer(new int[] { indexedTasks[taskidx][1], indexedTasks[taskidx][2] });
                taskidx++;
            }

            //process the task 
            int[] process = minheap.poll();
            int currtime = process[0];
            int index = process[1];

            time = time + currtime;
            result[resultidx++] = index;
        }
        return result;

    }
}