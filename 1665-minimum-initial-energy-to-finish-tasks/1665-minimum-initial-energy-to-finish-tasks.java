class Solution {
    public int minimumEffort(int[][] tasks) {
        // for tasks[1] >= current energy to start the task
        // example [10,12] - > curenergy = 11 = 12>=11 -> false , but if curenergy = 13>=11 true rem energy will be curenergy-task[0];
        // binary search on answer 
        // what is max energy so all takscan be done 
        // [[1,2],[2,4],[4,8]] - > 2+4+8 = 14-4=10-2=8-1=7
        // mid = 7 => we can not start 3rd task 
        //[[1,3],[2,4],[10,11],[10,12],[8,9]] - max = 3+4+11+12+9 = 39

        int l = 1;
        int r = 0;
        for (int[] task : tasks) {
            r += task[1];
        }
        
        int ans = 0;
        Arrays.sort(tasks, (a, b) -> (b[1] - b[0]) - (a[1] - a[0]));
        while (l <= r) {
            int mid_energy = (l + (r - l) / 2);

            //try mid_energy
            if (completeTasks(mid_energy, tasks)) {
                ans = mid_energy;
                r = mid_energy-1;
            } else {
                l = mid_energy + 1;
            }
        }
        return ans;

    }

    public boolean completeTasks(int energy, int[][] tasks) {
        for (int[] task : tasks) {
            int actual = task[0];
            int minimum = task[1];

            if (energy < minimum) {
                return false;
            } else
               energy = energy - actual;
        }
       
        return true;
    }
}