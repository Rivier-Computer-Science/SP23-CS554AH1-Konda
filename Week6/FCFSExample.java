/**
 * A class that demonstrates the First-Come-First-Serve (FCFS) CPU scheduling algorithm using the Scheduler class.
 */
public class FCFSExample {
    
    /**
     * The main method that creates a Scheduler object, adds processes to it, and runs the FCFS scheduling algorithm on them.
     * 
     * @param args the command line arguments (unused)
     */
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        scheduler.addProcess(new Process("P1", 0, 10));
        scheduler.addProcess(new Process("P2", 0, 5));
        scheduler.addProcess(new Process("P3", 0, 8));
        scheduler.runFCFS();
    }
    
}
