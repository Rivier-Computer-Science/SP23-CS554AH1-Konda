
import java.util.LinkedList;
import java.util.Queue;

/**
 * An abstract class for a scheduler that contains a queue of processes and an abstract schedule method.
 */
abstract class Scheduler {
    ProcessesQueue ProcessesQueue;

    /**
     * The schedule method that will be implemented in the subclasses.
     */
    abstract void schedule();
}

/**
 * A class for processes that contains a name and a burst time.
 */
class Processes {
    String name;
    int burstTime;

    /**
     * Constructor for Processes class.
     *
     * @param name      The name of the process.
     * @param burstTime The burst time of the process.
     */
    public Processes(String name, int burstTime) {
        this.name = name;
        this.burstTime = burstTime;
    }
}

/**
 * A class for the processes queue that contains a queue of processes.
 */
class ProcessesQueue {
    Queue<Processes> queue = new LinkedList<>();

    /**
     * Add a process to the queue.
     *
     * @param Processes The process to be added to the queue.
     */
    public void addProcesses(Processes Processes) {
        queue.add(Processes);
    }

    /**
     * Remove and return the first process in the queue.
     *
     * @return The first process in the queue.
     */
    public Processes removeProcesses() {
        return queue.poll();
    }

    /**
     * Check if the queue is not empty.
     *
     * @return True if the queue is not empty, false otherwise.
     */
    public boolean hasProcesses() {
        return !queue.isEmpty();
    }
}

/**
 * A class for the First-Come-First-Served scheduler that inherits from the Scheduler class.
 */
class FirstComeFirstServedScheduler extends Scheduler {
    /**
     * Constructor for the FirstComeFirstServedScheduler class that initializes the processes queue.
     */
    public FirstComeFirstServedScheduler() {
        ProcessesQueue = new ProcessesQueue();
    }

    /**
     * Add a process to the processes queue.
     *
     * @param Processes The process to be added to the processes queue
     */
    public void addProcesses(Processes Processes) {
        ProcessesQueue.addProcesses(Processes);
    }

    /**
     * Schedule the processes in the processes queue in a first-come-first-served manner.
     * Print the name of the process being run and the time it takes to run the process.
     */
    public void schedule() {
        while (ProcessesQueue.hasProcesses()) {
            Processes Processes = ProcessesQueue.removeProcesses();
            System.out.println("Running Processes: " + Processes.name);
            try {
                Thread.sleep(Processes.burstTime);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Finished Processes: " + Processes.name);
        }
    }
}

/**
 * A class for a runnable set of processes that implements the Runnable interface.
 * This class adds processes to the First-Come-First-Served scheduler and schedules them.
 */
class RunnableProcesses implements Runnable {
    FirstComeFirstServedScheduler scheduler;

    /**
     * Constructor for the RunnableProcesses class that takes a First-Come-First-Served scheduler.
     *
     * @param scheduler The First-Come-First-Served scheduler to add processes to and schedule.
     */
    public RunnableProcesses(FirstComeFirstServedScheduler scheduler) {
        this.scheduler = scheduler;
    }

    /**
     * Add processes to the First-Come-First-Served scheduler and schedule them.
     */
       
    public void run() {
        System.out.println("Adding Processeses to readyQ...");
        scheduler.addProcesses(new Processes("Processes A", 2000));
        scheduler.addProcesses(new Processes("Processes B", 3000));
        scheduler.addProcesses(new Processes("Processes C", 1000));
        scheduler.addProcesses(new Processes("Processes D", 5000));
        scheduler.schedule();
    }
}

public class week_4 {
    public static void main(String[] args) {
        FirstComeFirstServedScheduler scheduler = new FirstComeFirstServedScheduler();
        Thread t = new Thread(new RunnableProcesses(scheduler));
        t.start();
    }
}