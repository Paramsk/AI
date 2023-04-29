import java.util.*;

class JobScheduler {
    
    static class Job {
        int id, deadline, profit;
        
        Job(int id, int deadline, int profit) {
            this.id = id;
            this.deadline = deadline;
            this.profit = profit;
        }
    }
    
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        
        System.out.print("Enter the number of jobs: ");
        int n = scanner.nextInt();
        
        Job[] jobs = new Job[n];
        int maxDeadline = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            System.out.print("Enter the deadline and profit of job " + (i+1) + ": ");
            int deadline = scanner.nextInt();
            int profit = scanner.nextInt();
            maxDeadline = Math.max(maxDeadline, deadline);
            jobs[i] = new Job(i+1, deadline, profit);
        }
        
        // sort the jobs by profit in descending order
        Arrays.sort(jobs, new Comparator<Job>() {
            @Override
            public int compare(Job j1, Job j2) {
                return j2.profit - j1.profit;
            }
        });
        
        int[] result = new int[maxDeadline]; // array to store the sequence of jobs
        boolean[] slot = new boolean[maxDeadline]; // array to mark the slots
        for (int i = 0; i < n; i++) {
            int deadline = jobs[i].deadline;
            for (int j = deadline-1; j >= 0; j--) {
                if (!slot[j]) {
                    result[j] = jobs[i].id;
                    slot[j] = true;
                    break;
                }
            }
        }
        
        int maxProfit = 0;
        System.out.print("Max Profit Sequence of Jobs: ");
        for (int i = 0; i < maxDeadline; i++) {
            if (slot[i]) {
                System.out.print(result[i] + " ");
                maxProfit += jobs[result[i]-1].profit;
            }
        }
        System.out.println("\nMax Profit: " + maxProfit);
        
        scanner.close();
    }
}