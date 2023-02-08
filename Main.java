import java.util.Random;

class Process {
    public void doWork(int iterations) { }
}

class RunnableProcess extends Process {
    private Random random = new Random();

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
public class Main {

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