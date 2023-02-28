import java.util.Random;

/**
 * Process class which performs some work
 */
class Process {
    /**
    * Process class which performs some work
    */
    public void doWork(int iterations) { }
}

/**
 * RunnableProcess class which extends the Process class
 */
class RunnableProcess extends Process {
    private Random random = new Random();

    /**
     * Overrides the doWork method in the Process class
     * @param iterations number of iterations
     */
    @Override
    public void doWork(int iterations) {
        int[] array1 = new int[iterations];
        int[] array2 = new int[iterations];
        for (int i = 0; i < iterations; i++) {
            array1[i] = random.nextInt();
            array2[i] = random.nextInt();
        }

        long result = 0;
        for (int i = 0; i < iterations; i++) {
            result += array1[i] * array2[i];
        }
        System.out.println("Result: " + result);
    }
}
/**
 * week_3 class containing the main method
 */
public class week_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		 int iterations = 10000;
	        RunnableProcess p = new RunnableProcess();
	        long startTime = System.currentTimeMillis();
	        p.doWork(iterations);
	        long endTime = System.currentTimeMillis();
	        System.out.println("Time taken: " + (endTime - startTime) + " milliseconds");


	}

}
