/**
 * Represents a process in a CPU scheduling algorithm.
 */
public class Process {
    
    private String name;
    private int arrivalTime;
    private int burstTime;
    private int waitTime;
    
    /**
     * Creates a new process with the given name, arrival time, and burst time.
     * 
     * @param name the name of the process
     * @param arrivalTime the time at which the process arrives in the CPU queue
     * @param burstTime the amount of time required for the process to complete
     */
    public Process(String name, int arrivalTime, int burstTime) {
        this.name = name;
        this.arrivalTime = arrivalTime;
        this.burstTime = burstTime;
        this.waitTime = 0;
    }
    
    /**
     * Returns the name of the process.
     * 
     * @return the name of the process
     */
    public String getName() {
        return name;
    }
    
    /**
     * Returns the arrival time of the process.
     * 
     * @return the arrival time of the process
     */
    public int getArrivalTime() {
        return arrivalTime;
    }
    
    /**
     * Returns the burst time of the process.
     * 
     * @return the burst time of the process
     */
    public int getBurstTime() {
        return burstTime;
    }
    
    /**
     * Returns the wait time of the process.
     * 
     * @return the wait time of the process
     */
    public int getWaitTime() {
        return waitTime;
    }
    
    /**
     * Sets the wait time of the process.
     * 
     * @param waitTime the wait time to set for the process
     */
    public void setWaitTime(int waitTime) {
        this.waitTime = waitTime;
    }
}
