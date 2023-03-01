import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

/**
 * A scheduler that can run First-Come-First-Serve (FCFS) and Round-Robin (RR) scheduling algorithms
 * on a list of processes.
 */
public class Scheduler {
    
    private List<Process> processes;
    
    /**
     * Constructs a new Scheduler object with an empty list of processes.
     */
    public Scheduler() {
        processes = new ArrayList<>();
    }
    
    /**
     * Adds a new process to the list of processes.
     * 
     * @param p the process to add
     */
    public void addProcess(Process p) {
        processes.add(p);
    }
    
    /**
     * Runs the FCFS scheduling algorithm on the list of processes.
     * Prints the average wait time to the console.
     */
    public void runFCFS() {
        int currentTime = 0;
        int totalWaitTime = 0;
        
        for (Process p : processes) {
            p.setWaitTime(currentTime);
            totalWaitTime += p.getWaitTime();
            currentTime += p.getBurstTime();
        }
        
        double averageWaitTime = (double) totalWaitTime / processes.size();
        System.out.println("Average wait time (FCFS): " + averageWaitTime);
    }
    
    /**
     * Runs the Round-Robin scheduling algorithm on the list of processes with the given quantum.
     * Prints the average wait time to the console.
     * 
     * @param quantum the time quantum for each process
     */
    public void runRoundRobin(int quantum) {
        List<Process> queue = new ArrayList<>(processes);
        int currentTime = 0;
        int totalWaitTime = 0;
        
        while (!queue.isEmpty()) {
            Process p = queue.remove(0);
            p.setWaitTime(currentTime);
            
            if (p.getBurstTime() <= quantum) {
                currentTime += p.getBurstTime();
                totalWaitTime += p.getWaitTime();
            } else {
                p.setWaitTime(p.getBurstTime() - quantum);
                currentTime += quantum;
                queue.add(p);
            }
        }
        
        double averageWaitTime = (double) totalWaitTime / processes.size();
        System.out.println("Average wait time (Round Robin): " + averageWaitTime);
    }
    
    /**
     * Sorts the list of processes by their arrival time.
     */
    public void sortProcessesByArrivalTime() {
        Collections.sort(processes, new Comparator<Process>() {
            public int compare(Process p1, Process p2) {
                return p1.getArrivalTime() - p2.getArrivalTime();
            }
        });
    }
}
