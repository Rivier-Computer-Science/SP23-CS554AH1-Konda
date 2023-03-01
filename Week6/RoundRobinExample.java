/**
 * A class that demonstrates the Round Robin CPU scheduling algorithm using the Scheduler class.
 */
public class RoundRobinExample {
    
    /**
     * The main method that creates a Scheduler object, adds processes to it, sorts them by arrival time,
     * and runs the Round Robin scheduling algorithm on them with a time quantum of 2 units.
     * 
     * @param args the command line arguments (unused)
     */
    public static void main(String[] args) {
        Scheduler scheduler = new Scheduler();
        scheduler.addProcess(new Process("P1", 0, 10));
        scheduler.addProcess(new Process("P2", 0, 5));
        scheduler.addProcess(new Process("P3", 0, 8));
        scheduler.sortProcessesByArrivalTime();
        scheduler.runRoundRobin(2);
    }
    
}
