import java.util.*;

class Sample {
    // Time Complexity : O(N) where N is length of tasks
    // Space Complexity : O(1)
    // Did this code successfully run on Leetcode : Yes
    // Any problem you faced while coding this : No


    /**
     * Approach:
     * 1. If we want to separate the tasks by n size partition, we need to find task with max frequency.
     * 2. Number of partitions (after separating max freq task by n size partiton) = maxFreq - 1;
     * 3. Number of empty places between the maximum freq taks = n * (Number of partitions)
     * 4. After placing the max freq task pending tasks = total tasks - max freq;
     * 5. Now after placing all the tasks, idle spaces with no task = number of empty places - pending taks
     * 6. And finally total length after scheduling tasks = total tasks + idle spaces with no task 
     */

    public int leastInterval(char[] tasks, int n) {
         if(tasks == null || tasks.length == 0)
             return 0;
        
        int maxCount = 0;
        int maxFreq = 0;
        Map<Character, Integer> map = new HashMap<>();
        
        for(char c : tasks) {
            map.put(c, map.getOrDefault(c, 0)+1);
            if(maxFreq < map.get(c)) {
                maxFreq = map.get(c);
            }
        }
        
        for(char c:map.keySet()) {
            if(maxFreq == map.get(c)){
                maxCount++;
            }
        }
        
        int partition = maxFreq - 1;
        int empty = (n - maxCount + 1) * partition;
        int pending = tasks.length - maxFreq * maxCount;
        int idle = Math.max(0, empty - pending);
        
        return tasks.length + idle;
    }

    // Time Complexity : O(N) where N is length of ratings
    // Space Complexity : O(N)
    // Did this code successfully run on Leetcode : Yes
    // Any problem you faced while coding this : No


    /**
     * Approach:
     * 1. Calculate from left to right based on the ratings.
     * 2. Then calculate from right to left.
     * */

    public int candy(int[] ratings) {
        
        if(ratings == null || ratings.length == 0)
            return 0;
        
        int[] result = new int[ratings.length];
        Arrays.fill(result, 1);
        for(int i=1; i<ratings.length; i++) {
            if(ratings[i] > ratings[i-1]) {
                result[i] = result[i-1] + 1;
            }
        }
        
        for(int i=ratings.length-2; i>=0; i--) {
            if(ratings[i] > ratings[i+1]) {
                result[i] = Math.max(result[i], result[i+1]+1);
            }
        }
        
        int res = 0;
        for(int n: result) {
            res += n;
        }
        
        return res;
    }
}
