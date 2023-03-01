/**
 * A simulation of a process scheduler using a Round-Robin algorithm.
 * Processes are run for a fixed time quantum, and then moved to the end of the ready queue.
 * If a process is waiting for an external event, it is moved to the wait queue, and moved back to the
 * ready queue after a certain amount of time.
 */

import java.util.Queue;
import java.util.LinkedList;
import java.util.Random;

/**
 * The main scheduler class
 */
public class week_5 {
    private Queue<RunnableProcess> readyQ;
    private Queue<RunnableProcess> waitQ;

    /**
     * Constructor for the Week5 class.
     * Initializes the ready queue and wait queue.
     */
    public week_5() {
        readyQ = new LinkedList<>();
        waitQ = new LinkedList<>();
    }

    /**
     * Callback function called by a process when it finishes running.
     * Adds the process back to the ready queue.
     * @param process The process that just finished running.
     */

public synchronized void processFinished(RunnableProcess process) {
    readyQ.offer(process);
}

/**
 * Runs the scheduling algorithm.
 * Runs each process in the ready queue for a fixed quantum, and moves it to the end of the queue.
 * If a process is waiting, moves it to the wait queue.
 * After a certain amount of time, moves processes back from the wait queue to the ready queue.
 */
public synchronized void schedule() {
    while (!readyQ.isEmpty()) {
        RunnableProcess process = readyQ.poll();
        process.run();
        if (process.getState() == ProcessState.WAITING) {
            waitQ.offer(process);
        }
    }
    Random rand = new Random();
    int waitTime = rand.nextInt(10) + 1; // wait for 1 to 10 time units
    for (int i = 0; i < waitTime && !waitQ.isEmpty(); i++) {
        RunnableProcess process = waitQ.poll();
        process.incrementWaitTime();
        if (process.getState() == ProcessState.READY) {
            readyQ.offer(process);
        } else {
            waitQ.offer(process);
        }
    }
    System.out.println("Ready Queue: " + readyQ);
    System.out.println("Wait Queue: " + waitQ);
}

/**
 * The main function that initializes the scheduler and runs the scheduling algorithm for a fixed number of time units.
 * @param args Command line arguments (not used).
 */
public static void main(String[] args) {
    week_5 scheduler = new week_5();
    int numProcesses = 5;
    for (int i = 1; i <= numProcesses; i++) {
        scheduler.readyQ.offer(new RunnableProcess("Process " + i, scheduler));
    }
    for (int t = 1; t <= 20; t++) {
        System.out.println("Time: " + t);
        scheduler.schedule();
    }
}
}

/**
* Enumeration of the possible states that a process can be in.
*/
enum ProcessState {
READY, WAITING
}

/**
* A class representing a single process.
* Implements the Runnable interface to allow it to be run on a separate thread.
*/
class RunnableProcess implements Runnable {
private String name;
private ProcessState state;
private int waitTime;
private week_5 scheduler;

/**
 * Constructor for the RunnableProcess class.
 * Initializes the process name, state, wait time, and scheduler.
 * @param name The name of the process.
 * @param scheduler The scheduler object that is managing the process.
 */


public RunnableProcess(String name, week_5 scheduler) {
    this.name = name;
    this.state = ProcessState.READY;
    this.waitTime = 0;
    this.scheduler = scheduler;
}

public void run() {
    System.out.println("Running process " + name);
    state = ProcessState.WAITING;
    Random rand = new Random();
    int sleepTime = rand.nextInt(5) + 1; // sleep for 1 to 5 time units
    try {
        Thread.sleep(sleepTime * 1000);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    state = ProcessState.READY;
    scheduler.processFinished(this);
}

public void incrementWaitTime() {
    waitTime++;
    if (waitTime >= 3) {
        System.out.println("Aborting process " + name);
        state = ProcessState.READY;
        scheduler.processFinished(this);
    }
}

public ProcessState getState() {
    return state;
}

public String toString() {
    return name + "(" + state + ", wait=" + waitTime + ")";
}
}